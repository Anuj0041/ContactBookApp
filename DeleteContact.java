package cb.gui;
import java.sql.*;
import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cb.dbinfo.DBConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;
import java.sql.*;
public class DeleteContact extends JFrame implements ActionListener{
private JPanel contentPane;
public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteContact frame = new DeleteContact();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
private JComboBox<String>txtphone;
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
				String cName=rs.getString("name");//it will fetch the value from Name column
				String cPhone=rs.getString("phone_number");
				txtphone.addItem(cName+" : "+cPhone);//we are adding data in combobox
				
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
	
	public DeleteContact() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteContact.class.getResource("/cb/images/delete.png")));
		setForeground(new Color(66, 158, 185));
		setTitle("DeleteContact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(74, 85, 145));
		contentPane.setForeground(new Color(28, 255, 85));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Phone no");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(27, 30, 112, 40);
		contentPane.add(lblNewLabel);
		txtphone = new JComboBox();
		txtphone.setModel(new DefaultComboBoxModel(new String[] {"Select PhoneNumber"}));
		txtphone.setForeground(new Color(255, 128, 192));
		txtphone.setBounds(149, 30, 263, 52);
		fillCombo();//adding items from database in combobox via method
		contentPane.add(txtphone);
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(this);
		btndelete.setForeground(new Color(255, 0, 0));
		btndelete.setFont(new Font("Tahoma", Font.BOLD, 25));
		btndelete.setBounds(101, 135, 138, 52);
		contentPane.add(btndelete);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String data=(String) txtphone.getSelectedItem();
		if(data.equalsIgnoreCase("Select Phone"))
			JOptionPane.showMessageDialog(this, "Please Select a Valid Number");
		else {
			int choice=JOptionPane.showConfirmDialog(this, "Are you Sure to delete this "+data);
			//System.out.println(choice);
			if(choice==0) 
			{
				
			String[]contactArr=	data.split(" : ");//split the string on the basis of delimeter
			//and returns array with splited value
			String name=contactArr[0];
			String phone=contactArr[1];
			//System.out.println(phone);
			Connection con=DBConnection.openConnection();
			PreparedStatement ps=null;
			String deleteQuery="delete from contact where phone_number=?";
			try {
				ps=con.prepareStatement(deleteQuery);
				ps.setString(1, phone);
				int status=ps.executeUpdate();//it will delete data from contacttable
				if(status>0) {
			JOptionPane.showMessageDialog(this, name+ " "+phone +" number deleted successfully" );
			txtphone.removeAllItems();
			txtphone.addItem(" Select PhoneNumber");
			fillCombo();
				}
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
						con.close();
				}
				catch(SQLException se)
				{
					
				}
			}
			}
		}
	
	}

}
