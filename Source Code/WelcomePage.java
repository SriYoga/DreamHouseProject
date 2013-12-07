import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WelcomePage extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JComboBox comboBoxPrice;
	private JComboBox comboBoxLocation;
	private JComboBox comboBoxType;
	private JComboBox comboBoxBedroom;
	private JComboBox comboBoxWashroom;
	private JComboBox comboBoxGarage;
	private JComboBox cmbSquareFeet;
	JButton btnNewButton;
	Connection connection = null;


	public WelcomePage(final UserDetail user) {
		setTitle("Home - Search ");

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 485);
		setLocationRelativeTo(null);
		// setMaximumSize(800, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 11;
		gbc_lblNewLabel.gridy = 0;

		JLabel lblPrice_1 = new JLabel("Price");
		lblPrice_1.setForeground(Color.BLACK);
		lblPrice_1.setBounds(36, 154, 61, 16);
		contentPane.add(lblPrice_1);

		String[] priceStrings = {"All", "$200000 - 300000", "$300000 - 400000",
				"$400000 - 500000", "$500000 - 600000", "$600000 - 650000",
				"> $650000" };
		comboBoxPrice = new JComboBox(priceStrings);
		comboBoxPrice.setBounds(132, 150, 153, 27);
		contentPane.add(comboBoxPrice);
		comboBoxPrice.setSelectedIndex(0);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setForeground(Color.BLACK);
		lblLocation.setBounds(36, 182, 61, 16);
		contentPane.add(lblLocation);

		String[] locationStrings = { "All", "Toronto", "Ottawa", "Hamilton",
				"Kingston", "Oshawa", "Montreal" };
		comboBoxLocation = new JComboBox(locationStrings);
		comboBoxLocation.setBounds(132, 178, 153, 27);
		contentPane.add(comboBoxLocation);
		comboBoxLocation.setSelectedIndex(0);

		JLabel lblTypeOfHouse = new JLabel("Type of house");
		lblTypeOfHouse.setForeground(Color.BLACK);
		lblTypeOfHouse.setBounds(36, 240, 89, 16);
		contentPane.add(lblTypeOfHouse);

		String[] typeStrings = { "All", "Apartment", "Condominiums",
				"Detached House", "Semi-detached House", "Townhouse",
				"Duplex/Triplex"  };
		comboBoxType = new JComboBox(typeStrings);
		comboBoxType.setBounds(132, 236, 153, 27);
		contentPane.add(comboBoxType);
		comboBoxType.setSelectedIndex(0);

		JLabel lblNewLabel_1 = new JLabel("Bedroom");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(36, 268, 61, 16);
		contentPane.add(lblNewLabel_1);

		String[] bedroomStrings = { "All", "1", "2", "3", "4 and more" };
		comboBoxBedroom = new JComboBox(bedroomStrings);
		comboBoxBedroom.setBounds(132, 264, 153, 27);
		contentPane.add(comboBoxBedroom);
		comboBoxBedroom.setSelectedIndex(0);

		JLabel lblWashroom = new JLabel("Washroom");
		lblWashroom.setForeground(Color.BLACK);
		lblWashroom.setBounds(36, 296, 74, 16);
		contentPane.add(lblWashroom);

		String[] washroomStrings = { "All","1", "2", "3", "4 and more" };
		comboBoxWashroom = new JComboBox(washroomStrings);
		comboBoxWashroom.setBounds(132, 292, 153, 27);
		contentPane.add(comboBoxWashroom);
		comboBoxWashroom.setSelectedIndex(0);

		JLabel lblGarage = new JLabel("Garage");
		lblGarage.setForeground(Color.BLACK);
		lblGarage.setBounds(36, 324, 61, 16);
		contentPane.add(lblGarage);

		String[] garageStrings = {"All", "Not Available", "For 1 car", "For 2 cars",
				"For 3 cars", "For more than 3 cars" };
		comboBoxGarage = new JComboBox(garageStrings);
		comboBoxGarage.setBounds(132, 320, 153, 27);
		contentPane.add(comboBoxGarage);
		comboBoxGarage.setSelectedIndex(0);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "sysman",	"sysman123");
					Statement statement = connection.createStatement();
					String query = "select * from Home where " + getPrice()
							+ getSqFeet() + Location() 
							+ houseType()
							+ getBedRooms() + getWashRooms() + getGarage();
					ResultSet resultset = statement.executeQuery(query);
					SearchResult oSearch = new SearchResult(user, resultset);
					oSearch.show();
					//The Search Result part will be done by Shawn
					
					
					
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(95, 369, 89, 29);
		contentPane.add(btnSearch);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(191, 369, 94, 29);
		contentPane.add(btnClear);
		btnClear.addActionListener(

		new ActionListener() // anonymous inner class
		{
			// event handler called when clearJButton is pressed
			public void actionPerformed(ActionEvent event) {
				defaultJButtonActionPerformed(event);
			}

		} // end anonymous inner class

		);

		btnNewButton = new JButton("Log out");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(524, 21, 89, 27);
		contentPane.add(btnNewButton);

		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setBounds(107, 38, 61, 14);
		contentPane.add(lblWelcome);

		JLabel lblName = new JLabel(user.FirstName + " " + user.LastName);
		lblName.setBounds(163, 38, 201, 14);
		contentPane.add(lblName);

		JLabel lblSquareFeet = new JLabel(" Square Feet");
		lblSquareFeet.setForeground(Color.BLACK);
		lblSquareFeet.setBounds(33, 215, 79, 14);
		contentPane.add(lblSquareFeet);

		String[] squereFeetStrings = {  "All" ,"0 - 499", "500 - 999", "1000 - 1499",
				"1500 - 1999", "More then 2000"};
		cmbSquareFeet = new JComboBox(squereFeetStrings);
		cmbSquareFeet.setSelectedIndex(0);
		cmbSquareFeet.setBounds(132, 207, 153, 27);
		contentPane.add(cmbSquareFeet);
		
		JButton btnAboutUs = new JButton("About Us");
		btnAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				AboutUs aboutus = new AboutUs();				
				aboutus.show();
			}
		});
		btnAboutUs.setBounds(505, 394, 89, 27);
		contentPane.add(btnAboutUs);
		
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(WelcomePage.class.getResource("/Resources/WelcomePage1.jpg")));
				label.setBounds(0, 0, 623, 446);
				contentPane.add(label);
	}

	private String getPrice() {
		String query = "";
		if (comboBoxPrice.getSelectedItem().toString()
				.equals("$200000 - 300000")) {
			query = "price>='200000' and price<='300000'";
		}
		if (comboBoxPrice.getSelectedItem().toString()
				.equals("$300000 - 400000")) {
			query = "price>='300000' and price<='400000'";
		}
		if (comboBoxPrice.getSelectedItem().toString()
				.equals("$400000 - 500000")) {
			query = "price>='400000' and price<='500000'";
		}
		if (comboBoxPrice.getSelectedItem().toString()
				.equals("$500000 - 600000")) {
			query = "price>='500000' and price<='600000'";
		}
		if (comboBoxPrice.getSelectedItem().toString()
				.equals("$600000 - 650000")) {
			query = "price>='600000' and price<='650000'";
		}
		if (comboBoxPrice.getSelectedItem().toString().equals("> $650000")) {
			query = "price>'650000'";
		}
		if (comboBoxPrice.getSelectedItem().toString().equals("All")) {
			query = "price>='0'";
		}
		return query;
	}

	private String getSqFeet() {
		String query = "";

		if (cmbSquareFeet.getSelectedItem().toString().equals("0 - 499")) {
			query = "and sqfeet>='0' and sqfeet<='499'";
		}
		if (cmbSquareFeet.getSelectedItem().toString().equals("500 - 999")) {
			query = "and sqfeet>='500' and sqfeet<='999'";
		}
		if (cmbSquareFeet.getSelectedItem().toString().equals("1000 - 1499")) {
			query = "and sqfeet>='1000' and sqfeet<='1499'";
		}
		if (cmbSquareFeet.getSelectedItem().toString().equals("1500 - 1999")) {
			query = "and sqfeet>='1500' and sqfeet<='1999'";
		}
		if (cmbSquareFeet.getSelectedItem().toString().equals("More then 2000")) {
			query = "and sqfeet>'2000'";
		}
		if (cmbSquareFeet.getSelectedItem().toString().equals("All")) {
			query = "and sqfeet>='0'";
		}

		return query;
	}

	private String getBedRooms() {
		String query = "";

		if (!comboBoxBedroom.getSelectedItem().toString().equals("4 and more")
				&& !comboBoxBedroom.getSelectedItem().toString().equals("All")) {
			query = "and bedrooms=" + "'"
					+ comboBoxBedroom.getSelectedItem().toString() + "'";
		}
		if (comboBoxBedroom.getSelectedItem().toString().equals("4 and more")) {
			query = "and bedrooms>'4'";
		}
		if (comboBoxBedroom.getSelectedItem().toString().equals("All")) {
			query = "and bedrooms>='0'";
		}

		return query;
	}
//#############################################################################################################
	private String Location() 
	{
		String[] locationStrings = { "Toronto", "Ottawa", "Hamilton",	"Kingston", "Oshawa", "Montreal", "All" };
		String query = "";

		if (comboBoxLocation.getSelectedItem().toString().equals("Toronto")) {
			query = "and location='Toronto'";
		}
		if (comboBoxLocation.getSelectedItem().toString().equals("Ottawa")) {
			query = "and location='Ottawa'";
		}
		if (comboBoxLocation.getSelectedItem().toString().equals("Kingston")) {
			query = "and location='Kingston'";
		}
		if (comboBoxLocation.getSelectedItem().toString().equals("Oshawa")) {
			query = "and location='Oshawa'";
		}
		if (comboBoxLocation.getSelectedItem().toString().equals("Montreal")) {
			query = "and location='Montreal'";
		}
		if (comboBoxLocation.getSelectedItem().toString().equals("All")) {
			query = "and location IN ('Toronto', 'Ottawa' , 'Hamilton' , 'Kingston' , 'Oshawa' , 'Montreal')";
		}
		
		return query;
	}

	private String houseType()
	{
		String[] typeStrings = { "Apartment", "Condominiums", "Detached House", "Semi-detached House", 
				"Townhouse", "Duplex/Triplex" , "All" };
		String query = "";
		
		if (comboBoxType.getSelectedItem().toString().equals("Apartment")) {
			query = "and type = 'Apartment'";
		}
		if (comboBoxType.getSelectedItem().toString().equals("Condominiums")) {
			query = "and type = 'Apartmentment'";
		}
		if (comboBoxType.getSelectedItem().toString().equals("Detached House")) {
			query = "and type = 'Apartment'";
		}
		if (comboBoxType.getSelectedItem().toString().equals("Semi-detached House")) {
			query = "and type = 'Apartment'";
		}
		if (comboBoxType.getSelectedItem().toString().equals("Townhouse")) {
			query = "and type = 'Apartment'";
		}
		if (comboBoxType.getSelectedItem().toString().equals("Duplex/Triplex")) {
			query = "and type = 'Apartment'";
		}		
		if (comboBoxType.getSelectedItem().toString().equals("All")) {
			query = "and type IN ('Apartment', 'Condominiums', 'Detached House', 'Semi-detached House', 'Townhouse', 'Duplex/Triplex')";
		}
	return query;
	}
//#############################################################################################################
	
	private String getWashRooms() {
		String query = "";

		if (!comboBoxWashroom.getSelectedItem().toString().equals("4 and more")
				&& !comboBoxWashroom.getSelectedItem().toString().equals("All")) {
			query = "and washrooms=" + "'"
					+ comboBoxWashroom.getSelectedItem().toString() + "'";
		}
		if (comboBoxWashroom.getSelectedItem().toString().equals("4 and more")) {
			query = "and washrooms>'4'";
		}
		if (comboBoxWashroom.getSelectedItem().toString().equals("All")) {
			query = "and washrooms>='0'";
		}

		return query;
	}

	private String getGarage() {

		String[] garageStrings = { "Not Available", "For 1 car", "For 2 cars",
				"For 3 cars", "For more than 3 cars", "All" };
		String query = "";

		if (!comboBoxGarage.getSelectedItem().toString()
				.equals("Not Available")) {
			query = "and garage='0'";
		}
		if (comboBoxGarage.getSelectedItem().toString().equals("For 1 car")) {
			query = "and garage='1'";
		}
		if (comboBoxGarage.getSelectedItem().toString().equals("For 2 cars")) {
			query = "and garage='2'";
		}
		if (comboBoxGarage.getSelectedItem().toString().equals("For 3 cars")) {
			query = "and garage='3'";
		}
		if (comboBoxGarage.getSelectedItem().toString()
				.equals("For more than 3 cars")) {
			query = "and garage>'3'";
		}
		if (comboBoxGarage.getSelectedItem().toString().equals("All")) {
			query = "and garage>='0'";
		}

		return query;
	}

	// default fields
	private void defaultJButtonActionPerformed(ActionEvent event) {
		comboBoxPrice.setSelectedIndex(0);
		comboBoxLocation.setSelectedIndex(0);
		comboBoxType.setSelectedIndex(0);
		comboBoxBedroom.setSelectedIndex(0);
		comboBoxWashroom.setSelectedIndex(0);
		comboBoxGarage.setSelectedIndex(0);
		cmbSquareFeet.setSelectedIndex(0);
		// end method defaultJButtonActionPerformed
	}


	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnNewButton) {
			System.gc();
			for (Window window : Window.getWindows()) {
				window.dispose();
			}

			Login log = new Login();
			log.show();
		}
	}
}
