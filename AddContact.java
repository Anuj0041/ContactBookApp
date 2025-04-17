package cb.gui;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cb.dbinfo.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.sql.*;
public class AddContact extends JFrame  implements ActionListener ,KeyListener{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtemail;
	private int status;
	private Throwable se;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddContact frame = new AddContact();
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
	public AddContact() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddContact.class.getResource("/cb/images/update.png")));
		setTitle("newcontact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 411);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(200, 115, 168));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setLocationRelativeTo(null);//it will put the frame cnter of the screen
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(102, 22, 77, 45);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		contentPane.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.addKeyListener(this);
		txtname.setForeground(new Color(247, 142, 113));
		txtname.setBounds(248, 33, 146, 35);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Phone No");
		lblNewLabel_1.setBounds(102, 78, 101, 34);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel_1);
		
		txtphone = new JTextField();
		txtphone.addKeyListener(this);
		txtphone.setForeground(new Color(0, 0, 0));
		txtphone.setBounds(248, 96, 146, 33);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setBounds(102, 171, 101, 35);
		contentPane.add(lblNewLabel_2);
		
		txtemail = new JTextField();
		txtemail.setForeground(new Color(0, 0, 0));
		txtemail.setBounds(248, 176, 146, 35);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		//Button
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.addActionListener(this);
		btnsubmit.addKeyListener(this);
		btnsubmit.setBackground(new Color(31, 94, 239));
		btnsubmit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnsubmit.setBounds(161, 253, 213, 66);
		contentPane.add(btnsubmit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("button clicked");
		new_contact();
		
	}
	public void new_contact()
	{
		String uName=txtname.getText();
		String uEmail=txtemail.getText();
		String uPhone=txtphone.getText();
		if(uName.isEmpty()||uEmail.isEmpty()||uPhone.isEmpty())
			JOptionPane.showMessageDialog(this,"Data Required in all fields");
		else {
			Connection c=DBConnection.openConnection();//contact_db reference
			String insertQuery="insert into contact(phone_number, name, email, date)values(?,?,?,?)";//question mark is place holder
			java.util.Date d=new java.util.Date();//java Date
			java.sql.Date sqlDate=new java.sql.Date(d.getTime());//convert into sql Date
		
		PreparedStatement ps=null;//it will communicate with table
		try {
			ps=c.prepareStatement(insertQuery);//passes query to rdms and compile the query and store the reference of the query into ps
		ps.setString(1, uPhone);
		ps.setString(2, uName);
		ps.setString(3, uEmail);
		ps.setDate(4, sqlDate);
		System.out.println(ps);
		ps.executeUpdate();//data will get inserted into table
		if(status>0)
			JOptionPane.showMessageDialog(this, "contact added successfully");
		
		
		txtemail.setText(" ");
		txtname.setText("  ");
		txtphone.setText("  ");
		}
		catch(SQLException se)
		{
			int code=se.getErrorCode();
			System.out.println("code is "+code);
			if(code==1062)
				JOptionPane.showMessageDialog(this, "Already added in the contact Book","Duplicate Error", JOptionPane.ERROR_MESSAGE);
			
			
			se.printStackTrace();
		}
		finally {
			try {
				if(c!=null)
					c.close();
				//database reference will be released
				if(c!=null)
					c.close();//database references will be freed
			}
			catch(SQLException se) {
				
			}
			se.printStackTrace();
		}
		}//finally close
	}


	public void keyTyped(KeyEvent e) {
		
	char c=e.getKeyChar();
		if(e.getSource()==txtname)
		{
		if(!(Character.isAlphabetic(c)||c==KeyEvent.VK_BACK_SPACE||c==KeyEvent.VK_SPACE))
		{
			e.consume();//to skip type event for that key(numeric key)
			JOptionPane.showMessageDialog(this, "only Alphabets are allowed");
		}
		}
		if(e.getSource()==txtphone)
		{
			if(!(Character.isDigit(c)||c==KeyEvent.VK_BACK_SPACE))
			{
				e.consume();//to skip type event for that key(numeric key)
				JOptionPane.showMessageDialog(this, "only Digits are allowed");
			}	
		}
		
		
	}

	
	public void keyPressed(KeyEvent e) {
		int code=e.getKeyCode();//it return the keycode of a specific key
		//System.out.println("key press is "+code);
	if(code==10)
		new_contact();
		
	}


	public void keyReleased(KeyEvent e) {
		
		
	}
}
