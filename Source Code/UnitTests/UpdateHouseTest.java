package comp231.DreamHouse.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DreamHouse.UpdateHouse;
import DreamHouse.UserDetail;

public class UpdateHouseTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("Starting test of updatehouse constructor");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("test of updatehouse constructor complete");
	}

	@Test
	public void testUpdateHouseConstructor() {
		UserDetail ud=new UserDetail();
		int hid=6;
		UpdateHouse uh=new UpdateHouse(ud,hid);
		assertNotNull(uh);

	}

	@Test
	public void testActionPerformed() {
		fail("Not yet implemented");
	}

}
