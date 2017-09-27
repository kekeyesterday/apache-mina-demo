package com.jimmy.mina.io.sequence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

public class SequenceDemo {

	public static void main(String[] args) throws Exception {
		Vector<FileInputStream> vc = new Vector<>();
		vc.add(new FileInputStream("D:\\test\\1.txt"));
		vc.add(new FileInputStream("D:\\test\\2.txt"));
		vc.add(new FileInputStream("D:\\test\\3.txt"));
		
		
		Enumeration<FileInputStream> enumeration = vc.elements();
		SequenceInputStream sis = new SequenceInputStream(enumeration);
		FileOutputStream fos = new FileOutputStream("D:\\test\\4.txt");
		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = sis.read(buf)) != -1) {
			fos.write(buf, 0, len);
		}
		fos.flush();
		fos.close();
		sis.close();
	}

}
