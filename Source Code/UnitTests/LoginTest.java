package comp231.DreamHouse.tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DreamHouse.Login;
import DreamHouse.UserDetail;
import DreamHouse.WelcomePage;

public class LoginTest {

	@Before
	public void setUp() throws Exception {
		
				System.out.println("Starting test of the Login default constructor");
			}



	@After
	public void tearDown() throws Exception {
		System.out.println("Starting test of the Login default constructor complete");
	}

	@Test
	public void testLogin() {
		//fail("Not yet implemented");
		Login lg=new Login();
		UserDetail ud=new UserDetail();
		JTextField txtUserName = new JTextField("pei");
		JPasswordField txtPassword = new JPasswordField("sysman123");
		JButton btnLogin = new JButton("Login");
		lg.add(txtUserName);
		lg.add(txtPassword);
		lg.add(btnLogin);		
		btnLogin.doClick();
		lg.actionPerformed(new ActionEvent(btnLogin, 0, null));	
		Assert.assertEquals("{0} login successfully","pei");
	    //WelcomePage w=new WelcomePage(ud);
		System.out.println(txtUserName.toString()+"is a valid user.");
	}

}
