import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.ImageIcon;

public class AboutUs extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	Connection connection = null;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutUs frame = new AboutUs();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AboutUs() {

		setTitle("About Us - Dream House Developers");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 347);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		

		JButton btnLogin = new JButton("Close");
		btnLogin.addActionListener(this);
		btnLogin.setBounds(273, 268, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(AboutUs.class.getResource("/Resources/AbouUs.jpg")));
		label.setBounds(0, 0, 412, 305);
		contentPane.add(label);
	}

	public void actionPerformed(ActionEvent arg0) {
		this.setVisible(false);	
	}
}
