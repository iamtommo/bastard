package com.bastard.test;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.bastard.cls.ClassFile;

@RunWith(JUnit4.class)
public class LabelsTest {

	@Test
	public void test() {
		File testFile = new File("deps/labelstest.class");
		Assert.assertTrue(testFile.exists());
		
		ClassFile classFile = new ClassFile(testFile);
		try {
			classFile.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
