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
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection = DriverManager.getConnection(
							"jdbc:oracle:thin:@localhost:1521:orcl", "sysman",
							"Ekramili6");				
						
						FileInputStream fin = new FileInputStream(fc
								.getSelectedFile().getPath());	
						PreparedStatement ps = connection
								.prepareStatement("update home set location =" + "'" + cmbLocation.getSelectedItem().toString()+"'" + "," +
										"type =" + "'" + cmbType.getSelectedItem().toString()+"'" + "," +
										"sqfeet =" + "'" + txtSqFeet.getText().toString()+"'" + "," +
										"price =" + "'" + txtPrice.getText().toString()+"'" + "," +
										"description =" + "'" + txtDescription.getText()+"'" + "," +
										"bedrooms =" + "'" + txtBedRooms.getText().toString()+"'" + "," +
										"washrooms =" + "'" + txtWashRooms.getText().toString()+"'" + "," +
										"garage =" + "'" + txtGarage.getText().toString()+"'" + "," +
										"image = ? where homeid="+homeID +"");

						ps.setBinaryStream(1, fin, fin.available());
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null,
								"Home details successfully updates",
								"Information Message",
								JOptionPane.INFORMATION_MESSAGE);
					
				} catch (SQLException | IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(357, 405, 89, 23);
		contentPane.add(btnUpdate);
		
		try {
			connection = DriverManager
					.getConnection(
							"jdbc:oracle:thin:@localhost:1521:orcl",
							"sysman", "Ekramili6");
			Statement statement = connection
					.createStatement();
			String query = "select * from home where homeid="
					+ homeID + "";
			ResultSet resultset = statement
					.executeQuery(query);
			if (resultset.next()) {
				cmbLocation.setSelectedItem(resultset
						.getString("location"));
				cmbType.setSelectedItem(resultset
						.getString("type"));
				txtSqFeet.setText(resultset
						.getString("sqfeet"));
				txtPrice.setText(resultset
						.getString("price"));
				txtDescription.setText(resultset
						.getString("description"));
				txtBedRooms.setText(resultset
						.getString("bedrooms"));
				txtWashRooms.setText(resultset
						.getString("washrooms"));
				txtGarage.setText(resultset
						.getString("garage"));
				
				Blob b = resultset.getBlob(10);
				byte brr[] = new byte[(int) b.length()];
				brr = b.getBytes(1, (int) b.length());
				ImageIcon imgIcon = new ImageIcon(brr);
				Image scaledImage = ((ImageIcon) imgIcon)
						.getImage();
				Image resizedImage = scaledImage.getScaledInstance(
						lblImage.getWidth(),
						lblImage.getHeight(), 0);
				lblImage.setIcon(new ImageIcon(
						resizedImage));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		btnClose = new JButton("Close");
		btnClose.addActionListener(this);
		btnClose.setBounds(469, 405, 89, 23);
		contentPane.add(btnClose);

		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(this);
		btnLogout.setBounds(519, 28, 89, 23);
		contentPane.add(btnLogout);		
	}
		
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == btnUpload) {
				int returnValue = fc.showOpenDialog(this);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					ImageIcon icon = new ImageIcon(fc.getSelectedFile().getPath());
					Image img = null;
					try {
						img = ImageIO.read(fc.getSelectedFile());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Image resizedImage = img.getScaledInstance(lblImage.getWidth(),
							lblImage.getHeight(), 0);
					lblImage.setIcon(new ImageIcon(resizedImage));
				}
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
