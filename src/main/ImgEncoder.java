package main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class ImgEncoder {
	public static byte[] extractBytes(String imageName) throws IOException {
		File imgPath = new File(imageName);
		FileInputStream fis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		fis = new FileInputStream(imgPath);
		
		int len=0;
		byte[] buf =new byte[1024];
		
		while((len=fis.read(buf)) != -1) {
			baos.write(buf,0,len);
		}
		byte[] fileArray = baos.toByteArray();
		fis.close();
		baos.close();
		return fileArray;	
	}
	
	public static byte[] encodingBase64(byte[] targetBytes) {
		Encoder encoder = Base64.getEncoder();
		return encoder.encode(targetBytes);
	}
}
