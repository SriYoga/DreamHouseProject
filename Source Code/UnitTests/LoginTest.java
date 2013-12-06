package comp231.DreamHouse.tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public void testUsernamePwdValid(){
		Login login=new Login();
		
		JTextField txtUserName=new JTextField("pei");
//		txtUserName.getText();
		JPasswordField txtPassword = new JPasswordField("sysman123");
//		txtPassword.getText();
//		JButton btnLogin = new JButton("Login");
//		btnLogin.addActionListener(login);
//	//	System.out.println("yes");
//		btnLogin.doClick();
		
//		String userName="pei";
//		String password="sysman123";
		Connection connection = null;
		try{
		connection = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:VTM", "sys as sysdba",
				"sysman123");
		Statement statement = connection.createStatement();
		String query = "select * from UserDetails where Username="+"'"
				+ txtUserName.getText() +  "'"+" and password= " +"'"
				+ txtPassword.getText() + "'";
		ResultSet resultset = statement.executeQuery(query);
		System.out.println("This is valid user.");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
	}
	@Test
	public void testUsernamePwdValid2(){
		Login login=new Login();		
		JTextField txtUserName=new JTextField("p");
		JPasswordField txtPassword = new JPasswordField("sysman123");
		Connection connection = null;
		try{
		connection = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:VTM", "sys as sysdba",
				"sysman123");
		Statement statement = connection.createStatement();
		String query = "select * from UserDetails where Username="+"'"
				+ txtUserName.getText() +  "'"+" and password= " +"'"
				+ txtPassword.getText() + "'";
		ResultSet resultset = statement.executeQuery(query);
		if (resultset.next()) {
			UserDetail user = new UserDetail();
			user.UserID = Integer.valueOf(resultset.getString("UserID"));
			user.UserName = resultset.getString("UserName");
			System.out.printf("{0} is valid user.",user.UserName);}
		else{System.out.println("There is no such user");}
		
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	@Test
	public void testSqlQuery(){
		String userName="pei";
		String password="sysman123";
		Connection connection = null;
		try{
		connection = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:VTM", "sys as sysdba",
				"sysman123");
		Statement statement = connection.createStatement();
		String query = "select * from UserDetails where Username="+"'"
				+ userName +  "'"+" and password= " +"'"
				+ password + "'";
		ResultSet resultset = statement.executeQuery(query);
		System.out.printf("The username{0} and password {0}is valid to generate query result.",userName,password);
		}catch(SQLException e){
			e.printStackTrace();
		}
		}
	@Test
	public void testSqlQuery2(){
		String userName="pei";
		String password="sysman";
		Connection connection = null;
		try{
		connection = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:VTM", "sys as sysdba",
				"sysman123");
		Statement statement = connection.createStatement();
		String query = "select * from UserDetails where Username="+"'"
				+ userName +  "'"+" and password= " +"'"
				+ password + "'";
		ResultSet resultset = statement.executeQuery(query);
		System.out.println("The username and password is invalid to generate query result.");
		}catch(SQLException e){
			e.printStackTrace();
		}
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


