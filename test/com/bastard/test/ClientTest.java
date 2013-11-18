package com.bastard.test;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.bastard.cls.ClassFile;

@RunWith(JUnit4.class)
public class ClientTest {
	
	@Test
	public void test() throws Exception {
		JarFile jar = new JarFile("deps/test.jar");
		Enumeration<JarEntry> entries = jar.entries();
		while(entries.hasMoreElements()) {
			JarEntry entry = (JarEntry) entries.nextElement();
			if (entry.getName().equals("av.class")) {
				InputStream in = jar.getInputStream(entry);

				byte[] tmp = new byte[in.available()];
				in.read(tmp, 0, tmp.length);

				ClassFile cls = new ClassFile(entry.getName(), tmp);
				cls.read();
				System.out.println(cls.getConstantPool().getEntries()[847].toString());
			}
		}
	}

}
