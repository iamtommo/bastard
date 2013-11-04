package com.bastard;

import java.io.File;
import java.io.InputStream;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import com.bastard.cls.ClassFile;

public class Application {

	public Application() {
	}
	
	public void start() {
		try {
			JarFile jar = new JarFile("test.jar");
			
			for (ZipEntry entry = jar.entries().nextElement(); jar.entries().hasMoreElements();) {
				InputStream in = jar.getInputStream(entry);
				
				byte[] tmp = new byte[in.available()];
				in.read(tmp, 0, tmp.length);
				
				ClassFile cls = new ClassFile(tmp);
				cls.read();
				
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Application().start();
	}
	
	public static void logerr(String err) {
		System.out.println("Error: " + err);
	}
	
	public static void log(String msg) {
		System.out.println(msg);
	}

}
