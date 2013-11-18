package com.bastard.test;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.bastard.cls.ClassFile;

@RunWith(JUnit4.class)
public class BastardTest {

	@Test
	public void test() {
		ClassFile classFile = new ClassFile(new File("labelstest.class"));
		try {
			classFile.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
