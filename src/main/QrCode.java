package main;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class QrCode {
	public QrCode() {
		try {
			
			QRCodeWriter writer = new QRCodeWriter();
	        String param = "uofmobile://action?targetIp=112.214.137.70&targetPort=13";
	        param = new String(param.getBytes("UTF-8"), "ISO-8859-1");
	        BitMatrix matrix = writer.encode(param, BarcodeFormat.QR_CODE, 500, 500);

	        int qrColor = 0xff020202;
	        int backgroundColor = 0xFFFFFFFF;

	        MatrixToImageConfig config = new MatrixToImageConfig(qrColor, 0xFFFFFFFF);
	        
	        BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_BYTE_BINARY);
	        BufferedImage qrimage = MatrixToImageWriter.toBufferedImage(matrix, config);
	        
			ImageIO.write(qrimage, "jpg", new File("QRCODE.jpg"));
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}