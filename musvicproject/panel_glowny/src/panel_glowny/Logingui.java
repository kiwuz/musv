package panel_glowny;


import java.awt.EventQueue;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.UIManager;

public class Logingui {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logingui window = new Logingui();
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
	public Logingui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(new Color(31, 33, 38));
		frame.setBounds(100, 100, 729, 458);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(660, 800);
		frame.setShape(new RoundRectangle2D.Double(1,1, 660,800, 20,20));
		frame.getContentPane().setLayout(null);
		
		
		JLabel photo_panel = new JLabel("");
		photo_panel.setIcon(new ImageIcon(Logingui.class.getResource("/panel_glowny/img/login_img/musvix_15.11.png")));
		photo_panel.setBounds(0, 23, 660, 600);
		frame.getContentPane().add(photo_panel);
		
		JButton sign_up_button = new JButton("");
		sign_up_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				register register_window = new register();
				register_window.frame.setVisible(true);
				frame.dispose();
			}
		});
		sign_up_button.setIcon(new ImageIcon(Logingui.class.getResource("/panel_glowny/img/login_img/signup_button.png")));
		sign_up_button.setBounds(0, 620, 660, 132);
		frame.getContentPane().add(sign_up_button);
		sign_up_button.setSize(660, 90);
		sign_up_button.setBorderPainted(false);
		
		JButton log_in_button = new JButton("");
		log_in_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login_panel login_window = new login_panel();
				login_window.frame.setVisible(true);
				frame.dispose();
			}
		});
		log_in_button.setIcon(new ImageIcon(Logingui.class.getResource("/panel_glowny/img/login_img/login1_button.png")));
		log_in_button.setBounds(0, 710, 97, 25);
		frame.getContentPane().add(log_in_button);
		log_in_button.setSize(660,90);
		log_in_button.setBorderPainted(false);
		
		JButton exit_button = new JButton("");
		exit_button.setIcon(new ImageIcon(Logingui.class.getResource("/panel_glowny/img/login_img/exit_button.png")));
		exit_button.setBounds(620, 0, 47, 25);
		frame.getContentPane().add(exit_button);
		exit_button.setSize(40, 20);
		exit_button.setBorderPainted(false);
		exit_button.addActionListener(e -> System.exit(0));
		
		JButton minimal_button = new JButton("");
		minimal_button.setIcon(new ImageIcon(Logingui.class.getResource("/panel_glowny/img/login_img/minimal_button.png")));
		minimal_button.setBounds(579, 0, 97, 25);
		frame.getContentPane().add(minimal_button);
		minimal_button.addActionListener(e -> frame.setState(frame.ICONIFIED));
		minimal_button.setSize(40, 20);
		minimal_button.setBorderPainted(false);
	}
}
