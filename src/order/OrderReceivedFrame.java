package order;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class OrderReceivedFrame extends JFrame {

	//private OrderReceived module1;
	private ArrayList<String> orderList;
	private ArrayList<Entry> entryList; 
	public JTable jtable;
	public JScrollPane scrollpane;
	public JPanel orderReceivedPanel;
	public DefaultTableModel model;

	private OrderReceivedFrame() {
		
		
		setTitle("OrderReceivedFrame Test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		orderReceivedPanel = new JPanel();
		
		orderReceivedPanel.setBackground(Color.ORANGE);
		orderReceivedPanel.setLayout(new FlowLayout());
		
		
		JButton accept =  new JButton("Accept");
		JButton reject = new JButton("reject");
		
		accept.addActionListener(new AcceptEventListener());
		reject.addActionListener(new RejectEventListener());
		
		
		orderReceivedPanel.add(accept);
		orderReceivedPanel.add(reject);
		
		
		
		add(orderReceivedPanel);
		
		
		
		orderList = new ArrayList<>();
		entryList = new ArrayList<>();
		
		//module1 = new OrderReceived(this, orderList, entryList);
		//module1.start();
		
		String[] columnNames = {"순번", "메뉴", "접수시간"};
		model = new DefaultTableModel(null, columnNames) {
	        @Override public Class<?> getColumnClass(int column) {
	            return column==1?Integer.class:String.class;
	        }
	        @Override public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };

		jtable = new JTable(model);
		
		jtable.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                JTable t = (JTable)e.getSource();
                if(e.getClickCount()==2) {
                    TableModel m = t.getModel();
                    Point pt = e.getPoint();
                    int i = t.rowAtPoint(pt);
                    if(i>=0) {
                        int row = t.convertRowIndexToModel(i);
                        String title = String.format("%s %s", m.getValueAt(row, 0), m.getValueAt(row, 1));
                        int index = Integer.parseInt((String.format("%s", m.getValueAt(row, 0))).replace(".", ""))-1;
                        
                        String order_menu = "";
                        
                       // System.out.println("size : " + entryList.size());
                       // System.out.println( ((((entryList.get(0)).getMyOrder()).getOrderMenues()).get(0)).getMainMenu()  );
                        
                        
                        //System.out.println("index" + index);
                        
                        ArrayList<menu> myOrder = ((entryList.get(index)).getMyOrder()).getOrderMenues();
                        for(menu myMenu : myOrder) {
                        	//System.out.println(myMenu.getCount());
                        	order_menu += "\n" + myMenu.getCount() + "x " + myMenu.getMainMenu() + " " + myMenu.getSubMenu() + " - " + (myMenu.getCount()*myMenu.getPrice());
                        	
                        }
                        order_menu += "\n\n" + "결제금액 : " + ((entryList.get(index)).getMyOrder()).getTotalPrice() + "\n" + "[" + ((entryList.get(index)).getMyOrder()).getID() + "] 카드번호: " + ((entryList.get(index)).getMyOrder()).getCardNum();
                        
                        String content = "[주문내역]" + order_menu;
                        JOptionPane.showMessageDialog(t, content, title, JOptionPane.INFORMATION_MESSAGE);
                        
                    }
                }
            }
		});
		scrollpane = new JScrollPane(jtable);
		orderReceivedPanel.add(scrollpane);
		
		
		
		
		
		setSize(500, 500);
		setVisible(true);
	}

	public void refreshScrollList() {
		Date now = new Date();
		String now_string = ((now.toString()).split(" "))[3];
		String num = (orderList.get(orderList.size() - 1)).split(" ")[0];
		System.out.println((orderList.get(orderList.size() - 1)));
		String remove_string = ((orderList.get(orderList.size() - 1)).split(" ")[0]);
		String menu =  (orderList.get(orderList.size() - 1)).replace(remove_string, ""); 
		String[] row = { num, menu, now_string };
		
		jtable.setAutoCreateRowSorter(true);

		TableRowSorter tablesorter = new TableRowSorter(jtable.getModel());

		jtable.setRowSorter(tablesorter);

		model.addRow(row);
		/*
		jtable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					System.out.println("ㅎㅇ");
				}
			}
		});
		*/

		int index = 0;
		for (String o : orderList) {
			System.out.println("[" + index + "]:" + o);
			index++;
		}

	}

	public void whetherDialog() {
		int result = JOptionPane.showConfirmDialog(null, "주문이 들어왔습니다", "주문:", JOptionPane.OK_CANCEL_OPTION);
		System.out.println("result : " + result);
		if (result == 0) {
			System.out.println("수락");
			System.out.println("accept!");
			//(module1.getPOSThread()).setCheckWhether(true, true);
		}
		if (result == 2) {
			System.out.println("거절");
			System.out.println("reject!");
			//(module1.getPOSThread()).setCheckWhether(true, false);
		}
	}

	public static void main(String[] args) {
		OrderReceivedFrame mf = new OrderReceivedFrame();
	}

	class AcceptEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("accept!");
			//(module1.getPOSThread()).setCheckWhether(true, true);

		}
	}

	class RejectEventListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("reject!");
			//(module1.getPOSThread()).setCheckWhether(true, false);
		}
	}

}
