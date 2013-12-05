package comp231.DreamHouse.tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DreamHouse.AddNewHouse;
import DreamHouse.UserDetail;

public class AddNewHouseTest {

	@Before
	public void setUp() throws Exception {
		
		System.out.println("Starting test of adding new house");
	}

	@After
	public void tearDown() throws Exception {
	
		System.out.println("Test of adding new house complete");
	}

	@Test
	public void testAddNewHouse() {
		JTextField txtUserName = new JTextField("pei");
		UserDetail ud=new UserDetail();
		ud.UserName.equals(txtUserName);
		AddNewHouse addnewhouse=new AddNewHouse(ud);
		JTextArea txtDescription = new JTextArea("Good Location,Good View!");
		JTextField txtSqFeet = new JTextField("5000");
		JTextField txtPrice = new JTextField("Price");
		JTextField txtBedRooms = new JTextField("5");
		JTextField txtWashRooms = new JTextField("3");
		JTextField txtGarage = new JTextField("1");
		JButton btnInsert = new JButton("Insert");
		btnInsert.doClick();
		addnewhouse.actionPerformed(new ActionEvent(btnInsert,0,null));
		btnInsert.addActionListener(addnewhouse);		
		Assert.assertEquals("{0} insert new house successfully","pei");
		System.out.println("The insert is successful");
		}
		
		//fail("Not yet implemented");
	}


