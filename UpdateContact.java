package cb.gui;
import java.sql.*;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.xdevapi.Result;

import cb.dbinfo.DBConnection;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;

public class UpdateContact extends JFrame implements ItemListener,ActionListener{

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateContact frame = new UpdateContact();
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
	private JComboBox<String> comboBox;
	private int status;
	public void fillCombo()
	{
		Connection con=DBConnection.openConnection();
		PreparedStatement ps=null;
		String selectQuery="select * from contact";
		ResultSet rs=null;//Select query
		try
		{
			ps=con.prepareStatement(selectQuery);
			rs=ps.executeQuery();
			while(rs.next()==true)
			{
				//String cName=rs.getString("name");//it will fetch the value from Name column
				String cPhone=rs.getString("phone_number");
				comboBox.addItem(cPhone);//we are adding data in combobox
				
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)
					rs.close();
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();{
			}
		}
	}
}
	public UpdateContact() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateContact.class.getResource("/cb/images/update.png")));
		setTitle("UpdateContact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 comboBox = new JComboBox();
		 comboBox.addItemListener(this);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select Phone Number"}));
		comboBox.setBounds(171, 11, 178, 51);
		fillCombo();
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Phone");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(30, 11, 120, 84);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(30, 112, 75, 36);
		contentPane.add(lblNewLabel_1);
		
		txtname = new JTextField();
		txtname.setBounds(171, 118, 178, 36);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Email");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(30, 159, 95, 53);
		contentPane.add(lblNewLabel_2);
		
		txtemail = new JTextField();
		txtemail.setBounds(171, 165, 178, 36);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(this);
		btnupdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		btnupdate.setBounds(154, 212, 195, 51);
		contentPane.add(btnupdate);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	editcontact();	
		
	}
	public void editcontact()
	{
		String updatedName=txtname.getText();
		String updatedEmail=txtemail.getText();
		String phone=(String)comboBox.getSelectedItem();
		if(phone.equalsIgnoreCase("Select Phone Number"))
			JOptionPane.showMessageDialog(this, "Select Contact Number");
		
		
		
		
		else if(updatedName.isEmpty()||updatedEmail.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Name and Email Required","Empty field Error",JOptionPane.ERROR_MESSAGE);
		}
		else {
		Connection con=DBConnection.openConnection();
		PreparedStatement ps=null;
		String updateQuery="update contact set email=?,name=? where phone_number=?";
		try {
			ps=con.prepareStatement(updateQuery);
			ps.setString(1, updatedEmail);
			ps.setString(2, updatedName);
			ps.setString(3, phone);
			int status=   ps.executeUpdate();//insert/update/delete
			if(status>0)
				JOptionPane.showMessageDialog(this, "Contact Details Updated successfully");
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally {
			try {
				if(ps!=null)
					ps.close();
				if(con!=null)
					con.close();}
			
			
		catch(SQLException se) {
			se.printStackTrace();
		}}}
		
	}
	
	
	
	
	@Override
	public void itemStateChanged(ItemEvent e) 
	{
		int state=e.getStateChange();
		System.out.println(state);
		if(state==1)
		{
			String combobox=(String)comboBox.getSelectedItem();	
			//System.out.println(comboBox);
			if(combobox.equals("Select Phone Number"))
			{
				JOptionPane.showMessageDialog(this, "Select a valid contact Number");
				txtemail.setText("");
				txtname.setText("");
				
			}
			else 
			{
			Connection con=DBConnection.openConnection();
			PreparedStatement ps=null;
			ResultSet rs=null;
			String selectQuery="select * from contact where phone_number=?";
			try {
				ps=con.prepareStatement(selectQuery);
				ps.setString(1, combobox);
				rs=ps.executeQuery();
				rs.next();//it will put the cursor on the resultant data
				String nm=rs.getString("name");
				String em=rs.getString("email");
				txtemail.setText(em);
			txtname.setText(nm);
	}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			finally {
				try {
					if(rs!=null)
						rs.close();
					if(ps!=null)
						ps.close();
					if(con!=null)
						con.close();
				}
				catch(SQLException se) {
					se.printStackTrace();{
		      }
			 }
		    }
	       }
		 }
	}}


