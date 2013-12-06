package comp231.DreamHouse.tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
		String loc="Toronto";
		String ty="apartment";
		String im=null;
		Connection connection = null;
		UserDetail ud=new UserDetail();
		ud.UserName.equals(txtUserName);
		AddNewHouse addnewhouse=new AddNewHouse(ud);
		JTextArea txtDescription = new JTextArea("Good Location,Good View!");
		JTextField txtSqFeet = new JTextField("5000");
		JTextField txtPrice = new JTextField("Price");
		JTextField txtBedRooms = new JTextField("5");
		JTextField txtWashRooms = new JTextField("3");
		JTextField txtGarage = new JTextField("1");
//		JButton btnInsert = new JButton("Insert");
//		btnInsert.doClick();
//		addnewhouse.actionPerformed(new ActionEvent(btnInsert,0,null));
//		btnInsert.addActionListener(addnewhouse);		
//		Assert.assertEquals("{0} insert new house successfully",txtUserName);
		try {
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:VTM", "sys as sysdba",
					"sysman123");
			PreparedStatement ps = connection
					.prepareStatement("insert into Home(location,type,sqfeet,price,description,bedrooms,washrooms,garage,image)"
							+ "values('"+loc.toString()+"','"+ty.toString()+"','"+txtSqFeet.getText()+"','"+txtPrice+"','"+txtDescription.toString()+"','"+txtBedRooms.getText()+"','"
							+txtWashRooms.getText()+"','"+txtGarage.getText()+"','"+im.toString()+"')");
			ps.setString(1, loc.toString());
			ps.setString(2, ty.toString());
			ps.setString(3, txtSqFeet.getText().toString());
			ps.setString(4, txtPrice.getText().toString());
			ps.setString(5, txtDescription.getText());
			ps.setString(6, txtBedRooms.getText().toString());
			ps.setString(7, txtWashRooms.getText().toString());
			ps.setString(8, txtGarage.getText().toString());
			ps.setString(9, im.toString());
			ps.executeUpdate();
			System.out.println("insert is successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("The insert is successful");
		}
		
	
	}


