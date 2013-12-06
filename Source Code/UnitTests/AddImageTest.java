package comp231.DreamHouse.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DreamHouse.AddImage;

public class AddImageTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("Starting test of AddImage constructor");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("test of AddImage constructor complete");
	}

	@Test
	public void testAddImageConstructor() {
		
		AddImage image = new AddImage();
        assertNotNull(image);

		//fail("Not yet implemented");
	}

	@Test
	public void testActionPerformed() {
		//fail("Not yet implemented");
	}

}
