import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class UpdateHouse extends JFrame implements ActionListener{

	private JPanel contentPane;
	JFileChooser fc;
	JLabel lblImage;
	Connection connection = null;
	JButton btnClose;
	JButton btnLogout;
	JButton btnUpload;

	public UpdateHouse(UserDetail user, final int homeID) {
		setTitle("Home - Update house details");
		setBounds(100, 100, 639, 490);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setBounds(102, 45, 67, 14);
		contentPane.add(lblWelcome);

		JLabel label = new JLabel(user.FirstName + " " + user.LastName);
		label.setBounds(179, 45, 316, 14);
		contentPane.add(label);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(51, 127, 67, 14);
		contentPane.add(lblLocation);

		JLabel lblHouseType = new JLabel("House Type");
		lblHouseType.setBounds(42, 195, 87, 14);
		contentPane.add(lblHouseType);

		JLabel lblSquareFeet = new JLabel("Square Feet");
		lblSquareFeet.setBounds(42, 236, 87, 14);
		contentPane.add(lblSquareFeet);

		JLabel lblPriceRange = new JLabel("Price Range");
		lblPriceRange.setBounds(42, 264, 87, 14);
		contentPane.add(lblPriceRange);

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(41, 300, 102, 14);
		contentPane.add(lblDescription);

		final JTextArea txtDescription = new JTextArea();
		txtDescription.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDescription.setWrapStyleWord(true);
		txtDescription.setLineWrap(true);
		txtDescription.setBounds(41, 318, 227, 92);
		contentPane.add(txtDescription);

		String[] locationStrings = { "Toronto", "Ottawa", "Hamilton",
				"Kingston", "Oshawa", "Montreal" };
		final JComboBox cmbLocation = new JComboBox(locationStrings);
		cmbLocation.setBounds(128, 124, 130, 20);
		contentPane.add(cmbLocation);

		String[] typeStrings = { "Appartment", "Condominiums",
				"Detached House", "Semi-detached House", "Townhouse",
				"Duplex/Triplex" };
		final JComboBox cmbType = new JComboBox(typeStrings);
		cmbType.setBounds(134, 192, 130, 20);
		contentPane.add(cmbType);

		final JTextField txtSqFeet = new JTextField();
		txtSqFeet.setBounds(134, 233, 130, 20);
		contentPane.add(txtSqFeet);

		final JTextField txtPrice = new JTextField();
		txtPrice.setBounds(134, 261, 130, 20);
		contentPane.add(txtPrice);

		JLabel lblBedRooms = new JLabel("Bed Rooms");
		lblBedRooms.setBounds(326, 120, 77, 14);
		contentPane.add(lblBedRooms);

		JLabel lblWashRooms = new JLabel("Wash Rooms");
		lblWashRooms.setBounds(326, 162, 77, 14);
		contentPane.add(lblWashRooms);

		JLabel lblGarage = new JLabel("Garage");
		lblGarage.setBounds(326, 203, 46, 14);
		contentPane.add(lblGarage);

		JLabel lblSelectPictureTo = new JLabel("Upload picture");
		lblSelectPictureTo.setBounds(326, 247, 87, 14);
		contentPane.add(lblSelectPictureTo);

		final JTextField txtBedRooms = new JTextField();
		txtBedRooms.setBounds(460, 116, 128, 20);
		contentPane.add(txtBedRooms);

		final JTextField txtWashRooms = new JTextField();
		txtWashRooms.setBounds(459, 158, 129, 20);
		contentPane.add(txtWashRooms);

		final JTextField txtGarage = new JTextField();
		txtGarage.setBounds(460, 199, 128, 20);
		contentPane.add(txtGarage);

		fc = new JFileChooser();

		btnUpload = new JButton("Upload");
		btnUpload.addActionListener(this);
		btnUpload.setBounds(458, 243, 128, 23);
		contentPane.add(btnUpload);

		lblImage = new JLabel("");
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImage.setBounds(326, 277, 260, 102);
		contentPane.add(lblImage);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() 
		{


		});
		btnUpdate.setBounds(357, 405, 89, 23);
		contentPane.add(btnUpdate);
		
		

		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		btnClose.setBounds(469, 405, 89, 23);
		contentPane.add(btnClose);

		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(this);
		btnLogout.setBounds(519, 28, 89, 23);
		contentPane.add(btnLogout);		
	}		
			if (arg0.getSource() == btnClose) {
				this.setVisible(false);
			}
			if (arg0.getSource() == btnLogout) {
				System.gc();
				for (Window window : Window.getWindows()) {
					window.dispose();
				}
				
				Login log= new Login();
				log.show();
			}
		}

}
