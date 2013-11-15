
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class SearchResult extends JFrame implements ActionListener {

	private JPanel contentPane;
	// private JScrollPane sPane;
	String Location;
	Connection connection = null;
	JTextArea txtDescription;
	JLabel lblImage;
	JButton btnClose;
	JButton btnLogout;
	String selectedData = "";
	JButton btnNext;
	JButton btnPrevious;
	int imageID;

	/**
	 * Create the frame.
	 */

	public SearchResult(final UserDetail user, ResultSet result) {
		setTitle("Home - Search Result");

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 991, 490);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setBounds(26, 11, 65, 14);
		contentPane.add(lblNewLabel);

		JLabel lblName = new JLabel(user.FirstName + " " + user.LastName);
		lblName.setBounds(101, 11, 262, 14);
		contentPane.add(lblName);

		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(this);
		btnLogout.setBounds(870, 7, 89, 23);
		contentPane.add(btnLogout);

		txtDescription = new JTextArea();
		txtDescription.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDescription.setWrapStyleWord(true);
		txtDescription.setLineWrap(true);
		JScrollPane scPane = new JScrollPane(txtDescription);
		scPane.setBounds(621, 77, 338, 56);
		contentPane.add(scPane);

		lblImage = new JLabel("");
		lblImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImage.setBackground(Color.WHITE);
		lblImage.setBounds(619, 144, 340, 229);
		contentPane.add(lblImage);

		getData(result);

		JLabel lblLocation = new JLabel(Location);
		lblLocation.setBounds(90, 48, 46, 14);
		contentPane.add(lblLocation);

		//Here we can add for more buttons for other use cases
	}
	
	
	public void getData(ResultSet resultset) {
		try {
			String homeID = "", type = "", sqFeet = "", price = "", bedRooms = "", washRooms = "", garage = "";

			final DefaultTableModel model;
			model = new DefaultTableModel();
			model.addColumn("Home ID");
			model.addColumn("Type");
			model.addColumn("Square Feet");
			model.addColumn("Price");
			model.addColumn("Bed Rooms");
			model.addColumn("Wash Rooms");
			model.addColumn("Garage");

			final JTable tblResult = new JTable(model);
			TableColumnModel tcm = tblResult.getColumnModel();

			DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tblResult
					.getTableHeader().getDefaultRenderer();
			renderer.setHorizontalAlignment(SwingConstants.LEFT);
			for (int i = 0; i < (tcm.getColumnCount()); i++) {
				if (i == 0)
					tcm.getColumn(i).setPreferredWidth(160);
				if (i == 1)
					tcm.getColumn(i).setPreferredWidth(220);
				if (i == 2)
					tcm.getColumn(i).setPreferredWidth(180);
				if (i == 3)
					tcm.getColumn(i).setPreferredWidth(220);
				if (i == 4)
					tcm.getColumn(i).setPreferredWidth(180);
				if (i == 5)
					tcm.getColumn(i).setPreferredWidth(180);
				if (i == 6)
					tcm.getColumn(i).setPreferredWidth(170);
			}
			final ListSelectionModel selectionModel = tblResult
					.getSelectionModel();
			selectionModel
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			selectionModel
					.addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e) {
							// TODO Auto-generated method stub
							if (!e.getValueIsAdjusting()) {

								int lead = selectionModel
										.getLeadSelectionIndex();
								selectedData = (String) tblResult.getValueAt(
										lead, 0);
								try {
									connection = DriverManager
											.getConnection(
													"jdbc:oracle:thin:@localhost:1521:orcl",
													"sysman", "sysman123");
									Statement statement = connection
											.createStatement();
									String query = "select description,image from home where homeid="
											+ selectedData + "";
									ResultSet resultset = statement
											.executeQuery(query);
									if (resultset.next()) {
										txtDescription.setText(resultset
												.getString("description"));
										Blob b = resultset.getBlob(2);
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
										btnNext.setEnabled(true);
									}
								} catch (SQLException ex) {
									ex.printStackTrace();
								}
							}
						}
					});

			while (resultset.next()) {
				homeID = resultset.getString("homeid");
				type = resultset.getString("type");
				Location = resultset.getString("location");
				sqFeet = resultset.getString("sqfeet");
				price = resultset.getString("price");
				bedRooms = resultset.getString("bedrooms");
				washRooms = resultset.getString("washrooms");
				garage = resultset.getString("garage");

				model.addRow(new Object[] { homeID, type, sqFeet, price,
						bedRooms, washRooms, garage });
			}
			JScrollPane sPane = new JScrollPane(tblResult);
			sPane.setBounds(26, 77, 567, 298);
			contentPane.add(sPane);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == btnClose) {
			this.setVisible(false);
		}
		if (arg0.getSource() == btnLogout) {
			System.gc();
			for (Window window : Window.getWindows()) {
				window.dispose();
			}

			Login log = new Login();
			log.show();
		}
		
		
	}
}
