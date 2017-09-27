package com.jimmy.mina.io.sequence;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCutApartDemo {

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("D:\\test\\tmp\\PowerWord_2016.3.1.248.exe");
		FileOutputStream fos = null;
		byte[] bytes = new byte[1024 * 1024 * 3];
		int len = 0;
		int count = 1;
		while ((len = fis.read(bytes)) != -1) {
			fos = new FileOutputStream("D:\\test\\tmp\\" + (count++) + ".part");
			fos.write(bytes, 0, len);
		}
		fos.close();
		fis.close();
	}

}
