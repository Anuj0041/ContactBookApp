package cb.gui;

import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.Color;
import javax.swing.JButton;

public class UserDashBoard extends JFrame implements ActionListener{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDashBoard frame = new UserDashBoard();
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
	private JButton btnadd,btnupdate,btndelete;//instance variable
	public UserDashBoard() {
		setResizable(false);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("ContactBook");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 btnadd = new JButton("Add");
		btnadd.addActionListener(this);
		btnadd.setBounds(28, 32, 89, 23);
		contentPane.add(btnadd);
		
		 btnupdate = new JButton("update");
		btnupdate.addActionListener(this);
		btnupdate.setBounds(183, 32, 89, 23);
		contentPane.add(btnupdate);
		
		btndelete = new JButton("Delete");
		btndelete.addActionListener(this);
		btndelete.setBounds(321, 32, 89, 23);
		contentPane.add(btndelete);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnadd)
		{
			//System.out.println("Add is clicked");
			AddContact adcontact=new AddContact();
			adcontact.setVisible(true);
		}
		if(e.getSource()==btndelete)
		{
			//System.out.println("update is clicked");
			DeleteContact delcontact=new DeleteContact();
			delcontact.setVisible(true);
		}
		if(e.getSource()==btnupdate)
		{
		//System.out.println("Delete is clicked");	
			UpdateContact udcontact=new UpdateContact();
			udcontact.setVisible(true);
		}
		
	}
}
