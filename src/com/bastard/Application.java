package com.bastard;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.bastard.cls.ClassFile;

public class Application {

	public Application() {
	}

	public void start() {
		try {
			@SuppressWarnings("resource")
			JarFile jar = new JarFile("test.jar");
			Enumeration<JarEntry> entries = jar.entries();
			while(entries.hasMoreElements()) {
				JarEntry entry = (JarEntry) entries.nextElement();
				if (entry.getName().equals("a.class")) {
					InputStream in = jar.getInputStream(entry);

					byte[] tmp = new byte[in.available()];
					in.read(tmp, 0, tmp.length);

					ClassFile cls = new ClassFile(entry.getName(), tmp);
					cls.read();
				}
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
