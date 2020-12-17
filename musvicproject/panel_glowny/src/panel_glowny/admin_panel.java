package panel_glowny;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class admin_panel {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_panel window = new admin_panel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public admin_panel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.print("Working Directory = " + System.getProperty("user.dir"));
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel main_jpanel = new JPanel();
		main_jpanel.setBounds(0, 0, 365, 654);
		frame.getContentPane().add(main_jpanel);
		main_jpanel.setLayout(null);
		
		JLabel admin_username = new JLabel(login_panel.temporaryLogin);
		admin_username.setBounds(12, 13, 78, 30);
		main_jpanel.add(admin_username);
		
		JList list = new JList();
		list.setBounds(12, 37, 341, 604);
		main_jpanel.add(list);
		
		
		JPanel db_panel = new JPanel();
		db_panel.setBounds(365, 0, 367, 653);
		frame.getContentPane().add(db_panel);
		db_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		/*JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		db_panel.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(20,650));
		*/
	admin_functions temp = new admin_functions();
	try {
		temp.displayUsers();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	int baza_count = admin_functions.count;
	addUsers(baza_count, db_panel);
	System.out.println(baza_count);
	}

	
	  
	
	
	public void addUsers (int x,JPanel nazwa_panelu)
	{
		int i_pos = x;
		
		for(int i=0;i<i_pos;i++) {
			
		
			JPanel panel_9 = new JPanel();
			panel_9.setPreferredSize(new Dimension(345, 40));
			//panel_9.setBackground(Color.GRAY);
			nazwa_panelu.add(panel_9);
			JButton button_20 = new JButton();
			button_20.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/play-button.png")));
			button_20.setSize(25, 25);
			button_20.setOpaque(false);
			button_20.setContentAreaFilled(false);
			button_20.setBorderPainted(false);
			
			panel_9.add(button_20);
			panel_9.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
			JLabel lblSong = new JLabel(admin_functions.Users[i][0]);
			lblSong.setFont(new Font("Sitka Text", Font.BOLD, 16));
			lblSong.setForeground(Color.BLACK);
			panel_9.add(lblSong);
			JLabel label_19 = new JLabel(admin_functions.Users[i][1]);
			label_19.setFont(new Font("Sitka Text", Font.BOLD, 16));
			panel_9.add(label_19);
			label_19.setForeground(Color.BLACK);
			JLabel label_20 = new JLabel(admin_functions.Users[i][2]);
			label_20.setFont(new Font("Sitka Text", Font.BOLD, 16));
			label_20.setForeground(Color.BLACK);
			
			
			panel_9.add(label_20);
			/*JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			nazwa_panelu.add(scrollPane);
			scrollPane.setPreferredSize(new Dimension(20,650));*/
		
	}
	}
}
