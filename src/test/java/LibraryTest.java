package test.java;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.Library;

import org.junit.Assert.*;

public class LibraryTest {
	@Test
	public void testSomeLibraryMethod() {
		Library classUnderTest = new Library();
		assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
	}
}
