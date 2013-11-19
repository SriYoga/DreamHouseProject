package dh;

import java.awt.Color;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class AddNewHouse extends JFrame  {

	private JPanel contentPane;
	
	JLabel lblImage;
	JButton btnClose;
	JButton btnLogout;
	JButton btnUpload;
	/**
	 * Create the frame.
	 */
	public AddNewHouse() {
		setTitle("Home - Add new house");
//	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 579);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setBounds(165, 21, 67, 14);
		contentPane.add(lblWelcome);

		JLabel label = new JLabel("Name");
		label.setBounds(242, 21, 316, 14);
		contentPane.add(label);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(195, 169, 67, 14);
		contentPane.add(lblLocation);

		JLabel lblHouseType = new JLabel("House Type");
		lblHouseType.setBounds(195, 201, 87, 14);
		contentPane.add(lblHouseType);

		JLabel lblSquareFeet = new JLabel("Square Feet");
		lblSquareFeet.setBounds(195, 238, 87, 14);
		contentPane.add(lblSquareFeet);

		JLabel lblPriceRange = new JLabel("Price Range");
		lblPriceRange.setBounds(195, 279, 87, 14);
		contentPane.add(lblPriceRange);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(192, 341, 102, 14);
		contentPane.add(lblDescription);

		final JTextArea txtDescription = new JTextArea();
		txtDescription.setBorder((Border) new LineBorder(new Color(0, 0, 0)));
		txtDescription.setWrapStyleWord(true);
		txtDescription.setLineWrap(true);
		txtDescription.setBounds(192, 366, 250, 84);
		contentPane.add(txtDescription);

		String[] locationStrings = { "Toronto","Missisauga" };
		final JComboBox cmbLocation = new JComboBox(locationStrings);
		cmbLocation.setBounds(280, 166, 149, 20);
		contentPane.add(cmbLocation);

		String[] typeStrings = { "Appartment",
				"Detached House", "Semi-detached House", "Townhouse",
				"Duplex/Triplex" };
		final JComboBox cmbType = new JComboBox(typeStrings);
		cmbType.setBounds(280, 199, 150, 20);
		contentPane.add(cmbType);

		final JTextField txtSqFeet = new JTextField();
		txtSqFeet.setBounds(279, 238, 150, 20);
		contentPane.add(txtSqFeet);

		final JTextField txtPrice = new JTextField();
		txtPrice.setBounds(278, 277, 150, 20);
		contentPane.add(txtPrice);

		final JTextField txtBdRooms=new JTextField();
		JLabel lblBedRooms = new JLabel("Bed Rooms");
		lblBedRooms.setBounds(481, 169, 77, 14);
		contentPane.add(lblBedRooms);
		txtBdRooms.setBounds(279, 238, 150, 20);
		contentPane.add(txtBdRooms);
		
		final JTextField txtWshRooms=new JTextField();
		JLabel lblWashRooms = new JLabel("Wash Rooms");
		lblWashRooms.setBounds(481, 212, 77, 14);
		contentPane.add(lblWashRooms);
		contentPane.add(txtWshRooms);
//
//		final JTextField txtGarage=new JTextField();
		JLabel lblGarage = new JLabel("Garage");
		lblGarage.setBounds(481, 248, 46, 14);
		contentPane.add(lblGarage);
//		contentPane.add(txtGarage);
		
		final JTextField txtBedRooms = new JTextField();
		txtBedRooms.setBounds(569, 166, 128, 20);
		contentPane.add(txtBedRooms);

		final JTextField txtWashRooms = new JTextField();
		txtWashRooms.setBounds(568, 209, 129, 20);
		contentPane.add(txtWashRooms);

		final JTextField txtGarage = new JTextField();
		txtGarage.setBounds(569, 245, 128, 20);
		contentPane.add(txtGarage);


		
		btnClose = new JButton("Close");
		btnClose.setBounds(608, 489, 89, 23);
		contentPane.add(btnClose);

		btnLogout = new JButton("Logout");
		btnLogout.setBounds(618, 31, 89, 23);
		contentPane.add(btnLogout);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(0, 0, 753, 540);
		contentPane.add(label_1);
		contentPane.setVisible(true);
	}
	public static void main(String args[])
	{
	AddNewHouse anh=new AddNewHouse();
	anh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	anh.setSize(500, 500);
	anh.setVisible(true);
	}


	}

