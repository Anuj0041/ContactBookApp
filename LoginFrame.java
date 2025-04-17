package cb.gui;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class LoginFrame extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtid;
	private JPasswordField txtpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/cb/images/com.png")));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 794, 635);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Userid");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(207, 156, 68, 76);
		contentPane.add(lblNewLabel);
		
		txtid = new JTextField();
		txtid.setBounds(308, 180, 146, 36);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.addActionListener(this);//register the listener 
		
		btnsubmit.setIcon(new ImageIcon(LoginFrame.class.getResource("/cb/images/icon.png")));
		btnsubmit.setBackground(new Color(0, 255, 255));
		
		btnsubmit.setBounds(308, 405, 164, 76);
		contentPane.add(btnsubmit);
		
		JLabel lblNewLabel_1 = new JLabel("Password\r\n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(190, 243, 104, 45);
		contentPane.add(lblNewLabel_1);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(308, 247, 146, 45);
		contentPane.add(txtpass);
	}

	
	public void actionPerformed(ActionEvent e) {
		
	//System.out.println("button click");
		
		loginCheck();
		
		
	
		//String name="     Scott   ".trim();
	
	
	
	}
	
	
	public void loginCheck() {
		String userId=txtid.getText().trim();//fetch the value from Textfield
		char[] pass=txtpass.getPassword();
		String userPass=String.valueOf(pass);//convert char[] into String
		if(userId.isEmpty()||userPass.length()==0) 
		{
			JOptionPane.showMessageDialog(this,"Data Required");
		}
		else 
		{
			if(userId.equalsIgnoreCase("anuj") &&userPass.equals("abcde"))
			{
				UserDashBoard dashboard=new UserDashBoard();
				dashboard.setVisible(true);//it will show Frame
				this.dispose();//loginFrame will be closed
			}
			else {
				JOptionPane.showMessageDialog(this, "Invalid credentials", "LoginError", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
}
