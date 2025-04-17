package cb.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class WelcomeScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeScreen frame = new WelcomeScreen();
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
	public WelcomeScreen() {
		setForeground(new Color(0, 255, 255));
		setTitle("ContactBook");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 596);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Contact Book \r\n Save Your Contact Here For Future Use");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(47, 216, 476, 103);
		contentPane.add(lblNewLabel);
		loadFrame();
	}
	public void loadFrame()
	{
		Thread t=new Thread(new Runnable() {
			public void run()//overriding run method
			{
				try{
					Thread.sleep(4000);
					LoginFrame login=new LoginFrame();//object
					login.setVisible(true);
					WelcomeScreen.this.dispose();//outer class object refering from inner class
				}
				catch(InterruptedException ie)
				{
					ie.printStackTrace();
				}
			}
		});//object of that anonymous class which has implemented runnable
		//{}denotes anonumous class body
		t.start();//runnable state
		}
	}

