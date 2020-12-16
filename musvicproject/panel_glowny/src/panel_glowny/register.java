package panel_glowny;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.security.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import login_window.Logingui;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.security.*;
import javax.swing.SwingConstants;

public class register {

	JFrame frame;
	private JTextField login_field;
	private JTextField password_field;
	private JTextField name_field;
	private JTextField gender_field;
	

	/**
	 * Launch the application.
	 */
	enum gender {Male, Female, Other}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register window = new register();
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
	public register() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.WHITE);
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
		
		
		
		JButton exit_button = new JButton("");
		exit_button.setIcon(new ImageIcon(register.class.getResource("/panel_glowny/img/login_img/exit_button.png")));
		exit_button.setBounds(620, 0, 47, 25);
		frame.getContentPane().add(exit_button);
		exit_button.setSize(40, 20);
		exit_button.setBorderPainted(false);
		exit_button.addActionListener(e -> System.exit(0));
		
		JButton minimal_button = new JButton("");
		minimal_button.setIcon(new ImageIcon(register.class.getResource("/panel_glowny/img/login_img/minimal_button.png")));
		minimal_button.setBounds(579, 0, 97, 25);
		frame.getContentPane().add(minimal_button);
		minimal_button.addActionListener(e -> frame.setState(frame.ICONIFIED));
		minimal_button.setSize(40, 20);
		minimal_button.setBorderPainted(false);
		
		login_field = new JTextField();
		login_field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(login_field.getText().equals("Username"))
				{
				login_field.setText("");
				}
				}
			});
		login_field.setFont(new Font("Source Sans Pro", Font.PLAIN, 25));
		login_field.setForeground(new Color(240, 255, 255));
		login_field.setText("Username");
		login_field.setBackground(new Color(31, 33, 38));
		login_field.setBounds(154, 227, 376, 20);
		frame.getContentPane().add(login_field);
		login_field.setColumns(10);
		login_field.setSize(360, 25);
		login_field.setOpaque(false);
		login_field.setBorder(null);
		
		JLabel user_ico = new JLabel("");
		user_ico.setBounds(110, 227, 65, 26);
		frame.getContentPane().add(user_ico);
		user_ico.setIcon(new ImageIcon(register.class.getResource("/panel_glowny/img/login_img/user.png")));
		user_ico.setSize(40,26);
		
		JSeparator separator_login = new JSeparator();
		separator_login.setForeground(Color.WHITE);
		separator_login.setBackground(Color.WHITE);
		separator_login.setBounds(110, 265, 420, 12);
		frame.getContentPane().add(separator_login);
		
		password_field = new JPasswordField();
		password_field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(password_field.getText().equals("Password"))
				{
				password_field.setText("");
				}
			}
		});
		password_field.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JPasswordField field = (JPasswordField) event.getSource();
		        char[] password = field.getPassword();
		        Pattern specialCharacter = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		        Pattern UpperCase = Pattern.compile("[A-Z ]");
		        
		        if (password.length < 8) {
		            System.out.print("Password must contain at least 8 characters!");
		        }
		        if (!specialCharacter.matcher(password).find()) {
		            System.out.print("Password must contain at least one specail character!");
		        }
		        if (!UpperCase.matcher(password).find()) {
		            System.out.print("Password must contain at least one uppercase character!");
		        }
		    }
		});
		password_field.setText("Password");
		password_field.setOpaque(false);
		password_field.setForeground(new Color(240, 255, 255));
		password_field.setFont(new Font("Source Sans Pro", Font.PLAIN, 25));
		password_field.setColumns(10);
		password_field.setBorder(null);
		password_field.setBackground(new Color(31, 33, 38));
		password_field.setBounds(153, 445, 360, 25);
	    
		frame.getContentPane().add(password_field);
		
		JLabel pass_ico = new JLabel("");
		pass_ico.setIcon(new ImageIcon(register.class.getResource("/panel_glowny/img/login_img/password.png")));
		pass_ico.setBounds(109, 442, 40, 26);
		frame.getContentPane().add(pass_ico);
		
		JSeparator separator_password = new JSeparator();
		separator_password.setForeground(Color.WHITE);
		separator_password.setBackground(Color.WHITE);
		separator_password.setBounds(109, 486, 420, 12);
		frame.getContentPane().add(separator_password);
		
		JButton login_button = new JButton("");
			
		
		login_button.setIcon(new ImageIcon(register.class.getResource("/panel_glowny/img/login_img/sign_up_button.png")));
		login_button.setForeground(new Color(0, 51, 102));
		login_button.setBackground(new Color(0, 51, 102));
		login_button.setBounds(110, 555, 420, 65);
		login_button.setBorderPainted(false);
		frame.getContentPane().add(login_button);
		
		JButton login_fb_button = new JButton("");
		login_fb_button.setIcon(new ImageIcon(register.class.getResource("/panel_glowny/img/login_img/register_fb_button.png")));
		login_fb_button.setForeground(new Color(0, 51, 102));
		login_fb_button.setBackground(new Color(0, 51, 102));
		login_fb_button.setBounds(110, 661, 420, 65);
		login_fb_button.setBorderPainted(false);
		frame.getContentPane().add(login_fb_button);
		
		JButton haveacc_button = new JButton("Have an account? Log in");
		haveacc_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login_panel login_window = new login_panel();
				login_window.frame.setVisible(true);
				frame.dispose();
			}
		});
		haveacc_button.setForeground(SystemColor.textHighlightText);
		haveacc_button.setFont(new Font("Source Sans Pro", Font.BOLD, 22));
		haveacc_button.setBounds(110, 739, 420, 25);
		haveacc_button.setSize(420, 25);
		haveacc_button.setBorderPainted(false);
		haveacc_button.setOpaque(false);
		haveacc_button.setContentAreaFilled(false);
		frame.getContentPane().add(haveacc_button);
		
		JRadioButton showpass_button = new JRadioButton("");
		showpass_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (showpass_button.isSelected())
				{
					((JPasswordField) password_field).setEchoChar((char)0);
				}
				else
				{
					((JPasswordField) password_field).setEchoChar('*');
				}
			}
		});
		showpass_button.setBounds(529, 456, 25, 25);
		showpass_button.setBorderPainted(false);
		showpass_button.setOpaque(false);
		showpass_button.setContentAreaFilled(false);
		frame.getContentPane().add(showpass_button);
		
		name_field = new JTextField();
		name_field.setText("Name");
		name_field.setOpaque(false);
		name_field.setForeground(new Color(240, 255, 255));
		name_field.setFont(new Font("Source Sans Pro", Font.PLAIN, 25));
		name_field.setColumns(10);
		name_field.setBorder(null);
		name_field.setBackground(new Color(31, 33, 38));
		name_field.setBounds(154, 300, 360, 25);
		name_field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(name_field.getText().equals("Name"))
				{
				name_field.setText("");
				}
				}
			});
		frame.getContentPane().add(name_field);
		
		JLabel name_ico = new JLabel("");
		name_ico.setIcon(new ImageIcon(register.class.getResource("/panel_glowny/img/login_img/name.png")));
		name_ico.setBounds(110, 300, 40, 26);
		frame.getContentPane().add(name_ico);
		
		JSeparator separator_name = new JSeparator();
		separator_name.setForeground(Color.WHITE);
		separator_name.setBackground(Color.WHITE);
		separator_name.setBounds(110, 338, 420, 12);
		frame.getContentPane().add(separator_name);
		
		JSeparator separator_gender = new JSeparator();
		separator_gender.setForeground(Color.WHITE);
		separator_gender.setBackground(Color.WHITE);
		separator_gender.setBounds(110, 405, 420, 12);
		frame.getContentPane().add(separator_gender);
		
		JLabel gender_ico = new JLabel("");
		gender_ico.setIcon(new ImageIcon(register.class.getResource("/panel_glowny/img/login_img/sex.png")));
		gender_ico.setBounds(110, 367, 40, 26);
		frame.getContentPane().add(gender_ico);
		
		
		JComboBox gender_combo_box = new JComboBox(gender.values());
		gender_combo_box.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Other"}));
		gender_combo_box.setForeground(new Color(255, 250, 250));
		gender_combo_box.setBackground(new Color(31, 33, 38));
		gender_combo_box.setEditable(false);
		gender_combo_box.setFont(new Font("Source Sans Pro", Font.PLAIN, 25));
		gender_combo_box.setOpaque(false);
		gender_combo_box.setBorder(null);
		
		
		gender_combo_box.setBounds(151, 366, 363, 35);
		frame.getContentPane().add(gender_combo_box);
		
		JLabel password_warning = new JLabel("<html>Remember, that password should contain 8+ character, </br> uppercase, lowercase and special character</html>");
		password_warning.setForeground(Color.WHITE);
		password_warning.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 15));
		password_warning.setBounds(109, 497, 421, 51);
		frame.getContentPane().add(password_warning);
		
		
		
		JLabel photo_panel = new JLabel("");
		photo_panel.setIcon(new ImageIcon(register.class.getResource("/panel_glowny/img/login_img/jpanel_login_v3.png")));
		photo_panel.setBounds(0, 23, 660, 800);
		frame.getContentPane().add(photo_panel);
		
		gender_field = new JTextField();
		gender_field.setText("Gender\r\n");
		gender_field.setOpaque(false);
		gender_field.setForeground(new Color(240, 255, 255));
		gender_field.setFont(new Font("Source Sans Pro", Font.PLAIN, 25));
		gender_field.setColumns(10);
		gender_field.setBorder(null);
		gender_field.setBackground(new Color(31, 33, 38));
		gender_field.setBounds(154, 367, 360, 25);
		frame.getContentPane().add(gender_field);
		
		
		login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String register_login = login_field.getText();
				String register_password = password_field.getText();
                String register_name = name_field.getText();
                String register_gender = (String) gender_combo_box.getSelectedItem();
        
                password_rules passchcker = new password_rules();
                passchcker.passwordChecker(register_password);
                if (passchcker.warnings != "")
                {
                	JOptionPane.showMessageDialog(
                	        null, "Password should contain " + passchcker.warnings, "Failure", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                try {
					register_password = HashTextTest.sha1(HashTextTest.sha1(password_field.getText()));
				} catch (NoSuchAlgorithmException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}	
                MysqlConnect mysqlConnect = new MysqlConnect();
                String insertTableSQL = "INSERT INTO User"+"(Login,Password,Name,Gender,Type)"+" VALUES"+"('"+register_login+"','"+register_password+"','"+register_name+"','"+register_gender+"')";
                   
                
                try {
                   // PreparedStatement statement =  mysqlConnect.connect().prepareStatement(insertTableSQL);
                    PreparedStatement statement = mysqlConnect.connect().prepareStatement(insertTableSQL);
                   statement.execute(insertTableSQL);
                   
              
                   
                   JOptionPane.showMessageDialog(
               	        null, "Account created", "Complete", JOptionPane.DEFAULT_OPTION);
                   login_panel login_window = new login_panel();
   					login_window.frame.setVisible(true);
   					frame.dispose();
                } 
                
          
                catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException se)
                {
                	JOptionPane.showMessageDialog(
                	        null, "Login taken", "Failure", JOptionPane.ERROR_MESSAGE);
                }
                catch (SQLException exception) {
                    exception.printStackTrace();
                } finally {
                    mysqlConnect.disconnect();
                };

                }
		};
	
	});
	}	
}
