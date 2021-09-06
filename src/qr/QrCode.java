package qr;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class QrCode {
	
	private File QrFile;
	
	public QrCode() {
		 QrFile = new File("QRCODE.jpg");
	}
	
	public void create() {
		try {
			QRCodeWriter writer = new QRCodeWriter();
	        String param = "uofmobile://action?targetIp=" + getMyIP() + "&targetPort=88";
	        param = new String(param.getBytes("UTF-8"), "ISO-8859-1");
	        BitMatrix matrix = writer.encode(param, BarcodeFormat.QR_CODE, 500, 500);

	        int qrColor = 0xff020202;
	        int backgroundColor = 0xFFFFFFFF;

	        MatrixToImageConfig config = new MatrixToImageConfig(qrColor, 0xFFFFFFFF);
	        
	        BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_BYTE_BINARY);
	        BufferedImage qrimage = MatrixToImageWriter.toBufferedImage(matrix, config);
	        
			ImageIO.write(qrimage, "jpg", new File("QRCODE.jpg"));
			System.out.println(getMyIP());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	public void delete() {
		QrFile.delete();
	}
	
	public boolean isExist() {
		if(QrFile.exists()) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getMyIP() throws IOException {
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
		String ip = in.readLine();
		return ip;
	}
}
