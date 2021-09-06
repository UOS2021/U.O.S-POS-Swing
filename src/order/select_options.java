package order;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import main.Main;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class select_options {

	// my
	private POSServer module1;
	private ArrayList<String> orderList;
	private ArrayList<Entry> entryList;
	public JTable jtable;
	public JScrollPane scrollpane;
	public DefaultTableModel model;

	private JFrame frame;
	private JPanel panel;
	String comp_type;
	private Main main;
	private JPanel this_panel;
	private JButton exitButton;

	public select_options(Main main) {
		this.main = main;
		frame = this.main.getFrame();
		panel = this.main.getPanel();
		this_panel = new JPanel();
		
		panel.add(this_panel);
		// initialize();
	}

	public void initialize() {

		this_panel.setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this_panel.setBounds((int)screenSize.getWidth()/2-407, (int)screenSize.getHeight()/2-266, 814, 532);
		this_panel.setBackground(new Color(255, 200, 79));

		JButton btn_qr = new JButton("QR코드 관리");
		btn_qr.setFont(new Font("맑은 고딕", Font.BOLD, 36));
		btn_qr.setBounds(501, 105, 272, 136);
		this_panel.add(btn_qr);
		
		exitButton = new JButton("X");
		exitButton.setBackground(new Color(255,1,51));
		exitButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(814-50, 0, 50, 50);
		this_panel.add(exitButton);

		// 주문 목록

		orderList = new ArrayList<>();
		entryList = new ArrayList<>();

		module1 = new POSServer(main.getCompanyType(),this, orderList, entryList);
		module1.start();

		String[] columnNames = { "순번", "메뉴", "접수시간" };
		model = new DefaultTableModel(null, columnNames) {
			@Override
			public Class<?> getColumnClass(int column) {
				return column == 1 ? Integer.class : String.class;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		jtable = new JTable(model);

		jtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable t = (JTable) e.getSource();
				 if(e.getButton() == MouseEvent.BUTTON3) {
					 	System.out.println("우클릭");
						// fcm
						TableModel m = t.getModel();
						Point pt = e.getPoint();
						int i = t.rowAtPoint(pt);
						int row = t.convertRowIndexToModel(i);
						try {
							Fcm.push(main.getCompanyName(), Integer.toString(row), entryList.get(row).getMyOrder().getFcmToken());
						} catch (Exception e1) {
							System.out.println("앙 오류띠");
							e1.printStackTrace();
						}
					}
				else if (e.getClickCount() == 2) {
					TableModel m = t.getModel();
					Point pt = e.getPoint();
					int i = t.rowAtPoint(pt);
					if (i >= 0) {
						int row = t.convertRowIndexToModel(i);
						String title = String.format("%s %s", m.getValueAt(row, 0), m.getValueAt(row, 1));
						int index = Integer.parseInt((String.format("%s", m.getValueAt(row, 0))).replace(".", "")) - 1;

						String order_menu = "";

						// System.out.println("size : " + entryList.size());
						// System.out.println(
						// ((((entryList.get(0)).getMyOrder()).getOrderMenues()).get(0)).getMainMenu()
						// );

						// System.out.println("index" + index);

						ArrayList<menu> myOrder = ((entryList.get(index)).getMyOrder()).getOrderMenues();
						for (menu myMenu : myOrder) {
							// System.out.println(myMenu.getCount());
							order_menu += "\n" + myMenu.getCount() + "x " + myMenu.getMainMenu() + ", "
									+ myMenu.getSubMenu() + " → " + (myMenu.getCount() * myMenu.getPrice()) + "원";

						}
						order_menu += "\n\n" + "결제금액 : " + ((entryList.get(index)).getMyOrder()).getTotalPrice() + "원\n"
								+ "[" + ((entryList.get(index)).getMyOrder()).getID() + "] 카드번호: "
								+ ((entryList.get(index)).getMyOrder()).getCardNum();

						String content = "[주문내역]" + order_menu;
						JOptionPane.showMessageDialog(t, content, title, JOptionPane.INFORMATION_MESSAGE);

					}
				} 
				
				
			}
		});
		jtable.getColumn("메뉴").setPreferredWidth(500);
		scrollpane = new JScrollPane(jtable);
		scrollpane.setPreferredSize(new Dimension(300, 300));
		// scrollpane.setLayout(null);
		scrollpane.setBounds(25, 105, 458, 324);
		this_panel.add(scrollpane);
		// JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// tabbedPane.setBounds(28, 196, 762, 287);
		
		/*
		jtable = new JTable();
		jtable.setBounds(25, 105, 458, 324);
		this_panel.add(jtable);
		*/

		JLabel lblNewLabel = new JLabel("실시간 주문내역");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 36));
		lblNewLabel.setBounds(115, 27, 326, 68);
		this_panel.add(lblNewLabel);

		comp_type = main.getCompanyType();
//		comp_type = "패스트푸드/프랜차이즈";
//		comp_type = "영화관";
//		comp_type = "피시방";
		switch (comp_type) {
		case "패스트푸드/프랜차이즈":
			comp_type = "음식점관리";
			break;

		case "영화관":
			comp_type = "영화관관리";
			break;

		case "피시방":
			comp_type = "피시방관리";
			break;
		}
		JButton btn_option = new JButton("");
		btn_option.setText(comp_type);
		btn_option.setFont(new Font("맑은 고딕", Font.BOLD, 36));
		btn_option.setBounds(501, 287, 272, 136);
		this_panel.add(btn_option);
		btn_qr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				this_panel.setVisible(false);
				main.getQRPanel().getPanel().setVisible(true);
			}
		});

		btn_option.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 이부분에 어떤 frame 띄울지 정하면 됨
				this_panel.setVisible(false);

				switch (comp_type) {
				case "음식점관리":
					main.getMenuEditRestaurantPanel().getPanel().setVisible(true);
					break;
				case "영화관관리":
					main.getMenuEditTheater().getPanel().setVisible(true);
					break;

				case "피시방관리":
					main.getMenuEditPCRoomPanel().getPanel().setVisible(true);
					break;

				default:
					break;
				}

			}
		});
	}

	public JPanel getPanel() {
		return this_panel;
	}

	public void refreshScrollList() {
		Date now = new Date();
		String now_string = ((now.toString()).split(" "))[3];
		String num = ((orderList.get(orderList.size() - 1)).split(" ")[0]).replace(".", "");
		System.out.println((orderList.get(orderList.size() - 1)));
		String remove_string = ((orderList.get(orderList.size() - 1)).split(" ")[0]);
		String menu = (orderList.get(orderList.size() - 1)).replace(remove_string, "");
		String[] row = { num, menu, now_string };

		jtable.setAutoCreateRowSorter(true);

		TableRowSorter tablesorter = new TableRowSorter(jtable.getModel());

		jtable.setRowSorter(tablesorter);

		model.addRow(row);

		int index = 0;
		for (String o : orderList) {
			System.out.println("[" + index + "]:" + o);
			index++;
		}

	}

	public void whetherDialog(JSONObject obj) {
		String content = "주문이 들어왔습니다!\n";
		Date now = new Date();
		String now_string = now.toString();
		int total_price = 0;

		JSONObject message = (JSONObject) obj.get("message");
		JSONObject card = (JSONObject) message.get("card");
		String card_num = (String) card.get("num");
		JSONArray order = (JSONArray) message.get("order");
		String id = (String) message.get("id");

		for (Object o : order) {
			JSONObject or = (JSONObject) o;
			int count = Integer.parseInt(String.valueOf(or.get("count")));
			String menu = (String) or.get("menu");
			String submenu = (String) or.get("submenu");
			int price = Integer.parseInt(String.valueOf(or.get("price")));

			content += "\n" + count + "x " + menu + ", " + submenu + " → " + price + "원";
			total_price += count * price;
		}

		content += "\n\n총 결제금액 : " + total_price + "원\n[" + id + "]" + " 카드번호: " + card_num;

		String[] yesORno = { "수락", "거절" };
		// int result = JOptionPane.showConfirmDialog(null, content, (now_string + "
		// 주문"), JOptionPane.YES_NO_OPTION, yesORno);
		int result = JOptionPane.showOptionDialog(null, content, (now_string + " 주문"), JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, yesORno, "수락");
		System.out.println("result : " + result);
		if (result == 0) {
			System.out.println("수락");
			System.out.println("accept!");
			(module1.getPOSThread()).setCheckWhether(true, true);
		}
		if (result == 2) {
			System.out.println("거절");
			System.out.println("reject!");
			(module1.getPOSThread()).setCheckWhether(true, false);
		}
	}
}
