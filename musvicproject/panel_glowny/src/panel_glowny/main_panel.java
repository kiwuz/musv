package panel_glowny;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.io.IOException;
import java.net.URL;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import panel_glowny.MysqlConnect;

import javax.swing.JProgressBar;
import java.awt.FlowLayout;
import javax.swing.JToggleButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;

public class main_panel implements ChangeListener {
	private boolean isPaused = false;
	private boolean isFirst = true;
	private int framePos = 0;
	private float volume = -10.0f;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JFrame frame;
	private String fullSongPath;
	private Audio song;
	private final JSeparator separator = new JSeparator();
	final static JLabel speaker_image = new JLabel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_panel window = new main_panel();
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
	
	public main_panel() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
			
		MysqlConnect mysqlConnect = new MysqlConnect();		
		mysqlConnect.connect();
		System.out.print(login_panel.logged_user);
		
		mysqlConnect.getTitleFromSQL();
		mysqlConnect.getimgLocation();
		mysqlConnect.getSelectedLanguage();
		
		ResourceBundle rb;
		if(mysqlConnect.language == 0) {
			Locale.setDefault(new Locale ("pl","PL"));
			rb = ResourceBundle.getBundle("language/cfg/resource_bundle");
			System.out.println(rb.getString("language"));
			System.out.println("Załadowny jezyk " + (rb.getString("language")));
			
		}
		else {
			Locale.setDefault(new Locale ("en","EN"));
			rb = ResourceBundle.getBundle("language/cfg/resource_bundle");
			System.out.println("Załadowny jezyk " + (rb.getString("language")));
		}
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300,900);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setTitle(MysqlConnect.title);
		
		JPanel change_panel = new JPanel();
		change_panel.setBounds(0, 70, 1294, 752);
		frame.getContentPane().add(change_panel);
		change_panel.setLayout(new CardLayout(0, 0));
		
		JPanel myacc_panel = new JPanel();
		myacc_panel.setBackground(SystemColor.controlHighlight);
		
		change_panel.add(myacc_panel, "account");
		myacc_panel.setLayout(null);
		
		JLabel author_image = new JLabel("");
		author_image.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/profile_pic/default_img.png")));
		author_image.setBounds(55, 30, 160, 160);
		myacc_panel.add(author_image);
		
		JButton changePhotoButton = new JButton("Zmie\u0144 zdj\u0119cie profilowe");
		changePhotoButton.setLocation(272, 27);
		changePhotoButton.setFont(new Font("Calibri", Font.BOLD, 14));
		changePhotoButton.setSize(171, 56);
		changePhotoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == changePhotoButton) {
					try {
					JFileChooser selectPhotoChooser = new JFileChooser();
					System.out.println(selectPhotoChooser.showOpenDialog(null));
					int responseFromButton = selectPhotoChooser.showOpenDialog(null);
						if(responseFromButton == JFileChooser.APPROVE_OPTION) 
						{
							File file = new File(selectPhotoChooser.getSelectedFile().getAbsolutePath());
							String userImage = file.toString();
							System.out.println(userImage);
							
							if(file.getName().endsWith(".jpg") || file.getName().endsWith(".png")) { //warunek ¿e wstawione zostanie tylko zdjecie
								System.out.println("Otworzono zdjêcie");	
								author_image.setIcon(new ImageIcon(userImage));
						}
						else System.out.println("Nieprawid³owy format pliku");
						}
					}
					catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		myacc_panel.add(changePhotoButton);
		
		JLabel login_label = new JLabel(login_panel.logged_user);
		login_label.setFont(new Font("Source Sans Pro", Font.PLAIN, 20));
		login_label.setBounds(282, 100, 161, 30);
		myacc_panel.add(login_label);
		
		JLabel login_label_1 = new JLabel("Login");
		login_label_1.setFont(new Font("Source Sans Pro", Font.PLAIN, 20));
		login_label_1.setBounds(308, 300, 100, 30);
		myacc_panel.add(login_label_1);
		
		JLabel login_label_1_1 = new JLabel("Login");
		login_label_1_1.setBounds(308, 352, 100, 30);
		myacc_panel.add(login_label_1_1);
		
		JLabel login_label_1_1_1 = new JLabel("Login");
		login_label_1_1_1.setBounds(308, 407, 100, 30);
		myacc_panel.add(login_label_1_1_1);
		
		JPanel mymusic_panel = new JPanel();
		mymusic_panel.setBackground(new Color(255, 51, 204));
		change_panel.add(mymusic_panel, "music");
		mymusic_panel.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(280, 0, 1014, 60);
		panel.setBackground(new Color(	65, 69, 80));
		mymusic_panel.add(panel);
		panel.setLayout(null);
		
		JButton music_songs_button = new JButton("");
		music_songs_button.setBounds(0, 0, 338, 60);
		music_songs_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/songs_m.png")));
		music_songs_button.setOpaque(false);
		music_songs_button.setContentAreaFilled(false);
		music_songs_button.setBorderPainted(false);
		music_songs_button.setRolloverEnabled(true);
		music_songs_button.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/songs_m-hover.png")));
		panel.add(music_songs_button);
		
		JButton music_albums_button = new JButton("");
		music_albums_button.setBounds(326, 0, 338, 60);
		panel.add(music_albums_button);
		music_albums_button.setRolloverEnabled(true);
		music_albums_button.setOpaque(false);
		music_albums_button.setContentAreaFilled(false);
		music_albums_button.setBorderPainted(false);
		music_albums_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/albums_m.png")));
		music_albums_button.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/albums_m-hover.png")));
		
		JButton music_arthist_button = new JButton("");
		music_arthist_button.setBounds(676, 0, 338, 60);
		panel.add(music_arthist_button);
		music_arthist_button.setRolloverEnabled(true);
		music_arthist_button.setOpaque(false);
		music_arthist_button.setContentAreaFilled(false);
		music_arthist_button.setBorderPainted(false);
		music_arthist_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/artists_m.png")));
		music_arthist_button.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/artists_m-hover.png")));
		
		JPanel player_panel = new JPanel();
		player_panel.setBounds(0, 822, 1294, 43);
		frame.getContentPane().add(player_panel);
		player_panel.setBackground(new Color(250, 250, 250));
		player_panel.setLayout(null);
		
		JButton rewind_button = new JButton("");
		rewind_button.setBounds(49, 3, 37, 37);
		player_panel.add(rewind_button);
		rewind_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		rewind_button.setBorderPainted(false);
		rewind_button.setOpaque(false);
		rewind_button.setContentAreaFilled(false);
		rewind_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/back.png")));
		
		
		
		JButton forward_button = new JButton("");
		forward_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		forward_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/next.png")));
		forward_button.setOpaque(false);
		forward_button.setContentAreaFilled(false);
		forward_button.setBorderPainted(false);
		forward_button.setBounds(179, 3, 37, 37);
		player_panel.add(forward_button);
		
		JLabel full_song_time = new JLabel();
		full_song_time.setText("/ 0:00");
		full_song_time.setFont(new Font("Source Sans Pro", Font.BOLD, 13));
		full_song_time.setBounds(979, 8, 37, 22);
		player_panel.add(full_song_time);
		
		JSlider volume_slider = new JSlider(-75,75,0);
		volume_slider.setBounds(1082, 8, 200, 26);
		player_panel.add(volume_slider);
		volume_slider.addChangeListener(this);
		
		//final JLabel speaker_image = new JLabel("");
		speaker_image.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/volume.png")));
		
		
		speaker_image.setBounds(1040, 8, 30, 30);
		player_panel.add(speaker_image);
		JButton playpause_button = new JButton("");
		playpause_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isPaused)
				{
					framePos = song.getFramePosition();
					song.stop();
					playpause_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/play.png")));
					isPaused = true;
					System.out.print(framePos);
				}
				else
				{
					song.playAfterPause(framePos);
					playpause_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/pause.png")));
					isPaused = false;
				}
			}
		});
		playpause_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/play.png")));
		playpause_button.setOpaque(false);
		playpause_button.setContentAreaFilled(false);
		playpause_button.setBorderPainted(false);
		playpause_button.setBounds(119, 3, 37, 37);
		player_panel.add(playpause_button);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(295, 8, 620, 27);
		player_panel.add(progressBar);
		
		JLabel song_played_time = new JLabel();
		song_played_time.setText("0:00");
		song_played_time.setFont(new Font("Source Sans Pro", Font.BOLD, 13));
		song_played_time.setBounds(950, 8, 26, 22);
		player_panel.add(song_played_time);
		
		JButton loop_button = new JButton("");
		loop_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				song.loop();
			}
		});
		loop_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/loop.png")));
		loop_button.setOpaque(false);
		loop_button.setContentAreaFilled(false);
		loop_button.setBorderPainted(false);
		loop_button.setBounds(246, 3, 37, 37);
		player_panel.add(loop_button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 60, 1294, 692);
		mymusic_panel.add(panel_1);
		
		JButton browse_music = new JButton("browse");
		browse_music.setBounds(591, 234, 75, 25);
		browse_music.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int rVal = chooser.showOpenDialog(frame);
				if(rVal == JFileChooser.APPROVE_OPTION)
				{
					String path = chooser.getCurrentDirectory().toString();
					String name = chooser.getSelectedFile().getName();
					fullSongPath = path + "\\" + name;				
					System.out.println(fullSongPath);
			
						//song.close();
						//playSong(fullSongPath, 0, 0);
						try {
							song = new Audio(path);
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//song.play(0, 0);
				}
			}
		});
		
		JButton play_music = new JButton("play");
		play_music.setBounds(10, 10, 70, 30);
		play_music.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			playSong("C:\\Users\\Leszek\\Music\\POP SMOKE - GOT IT ON ME.mp3");
			playpause_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/pause.png")));
			full_song_time.setText("/ " + song.clipLength());
			/*do {
				String PosRN = Float.toString((float)song.getFramePosition());
				song_played_time.setText(PosRN);
				} while (isPaused=true);*/
		}
		});
		panel_1.setLayout(null);
		panel_1.add(play_music);
		panel_1.add(browse_music);
		
		JLabel song_name = new JLabel("POP SMOKE - GOT IT ON ME");
		song_name.setFont(new Font("Source Sans Pro", Font.BOLD, 21));
		song_name.setBounds(129, 10, 507, 35);
		
		panel_1.add(song_name);
		
		JLabel song_name2 = new JLabel("Skepta ft. JME - That's Not Me");
		song_name2.setFont(new Font("Source Sans Pro", Font.BOLD, 20));
		song_name2.setBounds(129, 72, 486, 30);
		panel_1.add(song_name2);
		separator.setBackground(Color.BLACK);
		separator.setBounds(0, 50, 1294, 9);
		panel_1.add(separator);
		
		JButton play_music_1 = new JButton("play");
		play_music_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			playSong("C:\\Users\\Leszek\\Music\\Skepta ft. JME - Thats Not Me.mp3");
			playpause_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/pause.png")));
			full_song_time.setText("/ " + song.clipLength());
		}
		});
		play_music_1.setBounds(10, 72, 70, 30);
		panel_1.add(play_music_1);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBackground(Color.BLACK);
		separator2.setBounds(0, 112, 1294, 9);
		panel_1.add(separator2);
		
		JButton play_music_1_1 = new JButton("play");
		play_music_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playSong("C:\\Users\\Leszek\\Music\\Dutchavelli - Bando Diaries.mp3");
				playpause_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/pause.png")));
				full_song_time.setText("/ " + song.clipLength());
				
			}
			});
		
		play_music_1_1.setBounds(10, 134, 70, 30);
		panel_1.add(play_music_1_1);
		
		JSeparator separator3 = new JSeparator();
		separator3.setBackground(Color.BLACK);
		separator3.setBounds(0, 174, 1294, 9);
		panel_1.add(separator3);
		
		JSeparator separator_vertical = new JSeparator();
		separator_vertical.setOrientation(SwingConstants.VERTICAL);
		separator_vertical.setBackground(Color.BLACK);
		separator_vertical.setBounds(92, 0, 25, 175);
		panel_1.add(separator_vertical);
		
		JLabel song_name3 = new JLabel("Dutchavelli - Bando Diaries");
		song_name3.setFont(new Font("Source Sans Pro", Font.BOLD, 20));
		song_name3.setBounds(129, 122, 293, 35);
		panel_1.add(song_name3);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print(song.musicTime());
			}
		});
		btnNewButton.setBounds(241, 318, 97, 25);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 282, 60);
		mymusic_panel.add(panel_2);
		
		JPanel shop_panel = new JPanel();
		shop_panel.setBackground(new Color(200, 226, 234));
		change_panel.add(shop_panel, "name_22376715666500");
		shop_panel.setLayout(null);
		
		JPanel songalbum_panel = new JPanel();
		songalbum_panel.setBackground(new Color(65, 69, 80));
		songalbum_panel.setBounds(276, 0, 1018, 60);
		shop_panel.add(songalbum_panel);
		songalbum_panel.setLayout(null);
		
		JButton songs_button = new JButton("");
		songs_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/songs_m.png")));
		songs_button.setBounds(0, 0, 507, 60);
		songs_button.setOpaque(false);
		songs_button.setContentAreaFilled(false);
		songs_button.setBorderPainted(false);
		songs_button.setRolloverEnabled(true);
		songs_button.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/songs_m-hover.png")));
		songalbum_panel.add(songs_button);
		
		JButton albums_button = new JButton("");
		albums_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/albums_m.png")));
		albums_button.setBounds(519, 0, 507, 60);
		albums_button.setOpaque(false);
		albums_button.setContentAreaFilled(false);
		albums_button.setBorderPainted(false);
		albums_button.setRolloverEnabled(true);
		albums_button.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/albums_m-hover.png")));
		songalbum_panel.add(albums_button);
		
		JPanel shop_card_panel = new JPanel();
		shop_card_panel.setBounds(0, 58, 1294, 694);
		shop_panel.add(shop_card_panel);
		shop_card_panel.setLayout(new CardLayout(0, 0));
		
		
		JPanel song_panel = new JPanel();
		shop_card_panel.add(song_panel, "name_29342290723900");
		song_panel.setLayout(null);
		
		JPanel panel_music = new JPanel();
		panel_music.setBounds(0, 0, 1294, 681);
		song_panel.add(panel_music);
		panel_music.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_9 = new JPanel();
		panel_9.setPreferredSize(new Dimension(1300, 40));
		panel_9.setBackground(Color.GRAY);
		panel_music.add(panel_9);
		panel_9.setLayout(null);
		JLabel lblArtist = new JLabel("Artist");
		lblArtist.setBounds(198, 5, 69, 31);
		lblArtist.setForeground(Color.WHITE);
		lblArtist.setFont(new Font("Sitka Text", Font.BOLD, 24));
		panel_9.add(lblArtist);
		JLabel lblAlbum_1 = new JLabel("Song");
		lblAlbum_1.setBounds(600, 5, 57, 31);
		lblAlbum_1.setForeground(Color.WHITE);
		lblAlbum_1.setFont(new Font("Sitka Text", Font.BOLD, 24));
		panel_9.add(lblAlbum_1);
		JLabel lblName = new JLabel("Album");
		lblName.setBounds(1000, 5, 77, 31);
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Sitka Text", Font.BOLD, 24));
		panel_9.add(lblName);
		
		
		JPanel albums_panel = new JPanel();
		shop_card_panel.add(albums_panel, "name_29344868776900");
		albums_panel.setLayout(null);
		
		songs_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shop_card_panel.removeAll();
				shop_card_panel.repaint();
				shop_card_panel.revalidate();
				// TODO Auto-generated method stub
				shop_card_panel.add(song_panel);
				shop_card_panel.repaint();
				shop_card_panel.revalidate();
				
			}
		});
		
		albums_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shop_card_panel.removeAll();
				shop_card_panel.repaint();
				shop_card_panel.revalidate();
				// TODO Auto-generated method stub
				shop_card_panel.add(albums_panel);
				shop_card_panel.repaint();
				shop_card_panel.revalidate();
				
			}
		});
		
		
	
		
		JPanel panel_music_1 = new JPanel();
		panel_music_1.setBounds(0, 0, 1300, 1500);
		//albums_panel.add(panel_music_1);
		panel_music_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_music_1.setPreferredSize(new Dimension(1300, 1500));
		
		JPanel panel_9_1 = new JPanel();
		panel_9_1.setLayout(null);
		panel_9_1.setPreferredSize(new Dimension(1300, 40));
		panel_9_1.setBackground(Color.GRAY);
		panel_music_1.add(panel_9_1);
		
		JLabel lblArtist_1 = new JLabel("Artist");
		lblArtist_1.setForeground(Color.WHITE);
		lblArtist_1.setFont(new Font("Sitka Text", Font.BOLD, 24));
		lblArtist_1.setBounds(198, 5, 69, 31);
		panel_9_1.add(lblArtist_1);
		
		JLabel lblAlbum_1_1 = new JLabel("Song");
		lblAlbum_1_1.setForeground(Color.WHITE);
		lblAlbum_1_1.setFont(new Font("Sitka Text", Font.BOLD, 24));
		lblAlbum_1_1.setBounds(600, 5, 57, 31);
		panel_9_1.add(lblAlbum_1_1);
		
		JLabel lblName_1 = new JLabel("Album");
		lblName_1.setForeground(Color.WHITE);
		lblName_1.setFont(new Font("Sitka Text", Font.BOLD, 24));
		lblName_1.setBounds(1000, 5, 77, 31);
		panel_9_1.add(lblName_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 1294, 700);
		scrollPane.setViewportView(panel_music_1);
		albums_panel.add(scrollPane);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 282, 60);
		shop_panel.add(panel_3);
		
				//SETTINGS PANEL 
				/////////////////////
				
				
				JPanel settings_panel = new JPanel();
				settings_panel.setBackground(SystemColor.controlHighlight);
				change_panel.add(settings_panel, "name_22387308818100");
				JLabel chooseThemeLabel = new JLabel(rb.getString("CHOOSETHEME"));
				chooseThemeLabel.setBounds(501, 18, 120, 20);
				chooseThemeLabel.setFont(new Font("Calibri", Font.BOLD, 17));
				settings_panel.add(chooseThemeLabel);

				
				JButton restart_app = new JButton(rb.getString("RESTARTBUTTON"));
				restart_app.setFont(new Font("Tahoma", Font.ITALIC, 10));
				restart_app.setContentAreaFilled(false);
				restart_app.setBorderPainted(false);
				restart_app.setBounds(91, 58, 319, 18);
				settings_panel.add(restart_app);
				restart_app.setVisible(false);

				restart_app.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}});
				
			
				// JEZYK
				
				//TEXT
				JLabel chooseLanguage = new JLabel(rb.getString("CHOOSELANGUAGE"));
				chooseLanguage.setBounds(81, 17, 120, 23);
				chooseLanguage.setFont(new Font("Calibri", Font.BOLD, 17));
				settings_panel.add(chooseLanguage);
				
			
				//COMBO BOX JEZYK
				JComboBox<String> comboLanguage = new JComboBox<String>();
				comboLanguage.setBounds(222, 17, 172, 20);
				comboLanguage.addItem("Polski (Polish)") ;
				comboLanguage.addItem("English (English)");
				settings_panel.setLayout(null);
				settings_panel.add(comboLanguage);
				int selectedLanguage = mysqlConnect.language;
				String selectedItem;
				if(selectedLanguage == 0) { 
					selectedItem = "Polski (Polish)";
				}
				else { 
					selectedItem = "English (English)";
				}
				
				comboLanguage.setSelectedItem(selectedItem);
				comboLanguage.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {						
						String wybor;
						int SelectedLanguageFromCombobox;
						
						wybor = (String) comboLanguage.getSelectedItem();
						switch (wybor) {
						case "Polski (Polish)":
							SelectedLanguageFromCombobox = 0;
							System.out.println("Selected PL");		
							restart_app.setVisible(true);
							break;
							
							
						case "English (English)":
							SelectedLanguageFromCombobox = 1;
							System.out.println("Selected EN");
							settings_panel.add(restart_app);
							restart_app.setVisible(true);
							break;
							
						default:
							SelectedLanguageFromCombobox = 0;
							System.out.println("Default select: PL");
							break;
														
						}	
						mysqlConnect.sendSelectedLanguage(SelectedLanguageFromCombobox);
					    }		
					
					});
				
				
				
				
				
				// /JEZYK
				
				//AVATAR I LOGIN

				JLabel avatar = new JLabel("");
				avatar.setBackground(Color.BLACK);
				avatar.setBounds(877, 11, 35, 35);	
				ImageIcon icon = new ImageIcon(mysqlConnect.adresObrazka);
				avatar.setIcon(icon);
				settings_panel.add(avatar);
				
				
				
				
				JLabel txtLogin = new JLabel(login_panel.logged_user);
				txtLogin.setFont(new Font("Calibri", Font.BOLD, 17));
				txtLogin.setBounds(922, 18, 102, 20);
				settings_panel.add(txtLogin);
				
				JLabel txtLoginName = new JLabel(login_panel.logged_userName);
				txtLoginName.setFont(new Font("Calibri", Font.BOLD, 17));
				txtLoginName.setBounds(1034, 18, 71, 20);
				settings_panel.add(txtLoginName);
				

				// /AVATAR I LOGIN W SETTINGS
				
				JLabel txtSettings = new JLabel(rb.getString("SETTINGS"));
				txtSettings.setBounds(489, 78, 306, 55);
				txtSettings.setFont(new Font("Calibri", Font.BOLD, 55));
				settings_panel.add(txtSettings);
				
				
				
				JLabel txtStudio = new JLabel("STUDIO");
				txtStudio.setBounds(406, 160, 71, 20);
				txtStudio.setFont(new Font("Calibri", Font.BOLD, 20));
				settings_panel.add(txtStudio);
				
				
				JLabel line = new JLabel("");
				line.setBounds(388, 175, 505, 20);
				line.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/line.png")));
				settings_panel.add(line);
				
				
				JLabel line1 = new JLabel("");
				line1.setBounds(388, 532, 505, 20);
				line1.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/line.png")));
				settings_panel.add(line1);
				
				JLabel line2 = new JLabel("");
				line2.setBounds(388, 284, 505, 20);
				line2.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/line.png")));
				settings_panel.add(line2);
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBackground(Color.BLACK);
				separator_1.setBounds(-30, 58, 1294, 9);
				settings_panel.add(separator_1);
				
				
				//NAZWA STUDIA
				
				//TEXT
				JLabel txtChangeTitle = new JLabel(rb.getString("CHANGETITLE"));
				txtChangeTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtChangeTitle.setBounds(409, 204, 139, 20);
				settings_panel.add(txtChangeTitle);
				
				//TEXTFIELD
				JTextField fieldChangeTitle = new JTextField(12);
				fieldChangeTitle.setBounds(544, 205, 122, 23);
				settings_panel.add(fieldChangeTitle);
				
				
				//BUTTON ZMIEN
				JButton btnChangeTitle = new JButton();
				btnChangeTitle.setIcon(new ImageIcon(main_panel.class.getResource(rb.getString("BUTTONSAVE"))));
				btnChangeTitle.setToolTipText(rb.getString("CHANGETITLETIP"));
				btnChangeTitle.setBounds(676, 201, 88, 33);
				btnChangeTitle.setBorderPainted(false);
				btnChangeTitle.setContentAreaFilled(false);
				settings_panel.add(btnChangeTitle);
				btnChangeTitle.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String newTitle = fieldChangeTitle.getText();
						mysqlConnect.sendTitleToSQL(newTitle);
						frame.setTitle(MysqlConnect.title);											
					    }				
					});

				//BUTTON DEFAULT		
				JButton btntDefaultTitle = new JButton();
				btntDefaultTitle.setIcon(new ImageIcon(main_panel.class.getResource(rb.getString("BUTTONDEFAULT"))));
				btntDefaultTitle.setToolTipText(rb.getString("CHANGETITLETIPdefault"));
				btntDefaultTitle.setBounds(774, 201, 120, 33);
				btntDefaultTitle.setBorderPainted(false);
				btntDefaultTitle.setContentAreaFilled(false);
				settings_panel.add(btntDefaultTitle);
				btntDefaultTitle.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String DefaultTitle = "MUSVIX STUDIO";
						mysqlConnect.sendTitleToSQL(DefaultTitle);
						frame.setTitle(MysqlConnect.title);
											
					    }				
					});
				

				
				JLabel txtSocial = new JLabel(rb.getString("SOCIAL"));
				txtSocial.setFont(new Font("Calibri", Font.BOLD, 20));
				txtSocial.setBounds(406, 271, 192, 20);
				settings_panel.add(txtSocial);
				
				JLabel txtMusvix = new JLabel("MUSVIX");
				txtMusvix.setFont(new Font("Calibri", Font.BOLD, 16));
				txtMusvix.setBounds(409, 315, 139, 20);
				settings_panel.add(txtMusvix);

				JLabel txtShowMyPlaylist = new JLabel(rb.getString("SHOWMYPLAYLIST"));
				txtShowMyPlaylist.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtShowMyPlaylist.setBounds(409, 340, 271, 20);
				settings_panel.add(txtShowMyPlaylist);
				
				 JCheckBox ShowMyPlaylistSwitch = new JCheckBox(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/on_icon.png")));
				 ShowMyPlaylistSwitch.setSelectedIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/off_icon.png")));
				 ShowMyPlaylistSwitch.setBorderPainted(false);
				 ShowMyPlaylistSwitch.setContentAreaFilled(false);
				 ShowMyPlaylistSwitch.setRolloverEnabled(false);
				 ShowMyPlaylistSwitch.setBounds(823, 340, 39, 20);
				 settings_panel.add(ShowMyPlaylistSwitch);
				
				
				 JLabel txtShowLastArtiststxt = new JLabel(rb.getString("SHOWRECENTPLAYEDARTISTS"));
				 txtShowLastArtiststxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
				 txtShowLastArtiststxt.setBounds(409, 371, 271, 20);
				 settings_panel.add(txtShowLastArtiststxt);
				
				 JCheckBox ShowLastArtistsSwitch = new JCheckBox(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/on_icon.png")));
				 ShowLastArtistsSwitch.setSelectedIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/off_icon.png")));
				 ShowLastArtistsSwitch.setBorderPainted(false);
				 ShowLastArtistsSwitch.setContentAreaFilled(false);
				 ShowLastArtistsSwitch.setRolloverEnabled(false);
				 ShowLastArtistsSwitch.setBounds(823, 371, 39, 20);
				 settings_panel.add(ShowLastArtistsSwitch);
				
				
				
				JLabel txtFacebook = new JLabel("FACEBOOK");
				txtFacebook.setFont(new Font("Calibri", Font.BOLD, 16));
				txtFacebook.setBounds(410, 442, 86, 20);
				settings_panel.add(txtFacebook);
				
				JLabel txtFacebookConnect = new JLabel("PO\u0141\u0104CZ Z FB/WYSWIETL PROFIL I ZDJ PROFILOWE");
				txtFacebookConnect.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtFacebookConnect.setBounds(489, 473, 346, 20);
				settings_panel.add(txtFacebookConnect);
				
				JLabel txtStartup = new JLabel(rb.getString("STARTUP"));
				txtStartup.setFont(new Font("Calibri", Font.BOLD, 20));
				txtStartup.setBounds(410, 519, 192, 20);
				settings_panel.add(txtStartup);
				
				JLabel txtStartupMusvix = new JLabel(rb.getString("STARTUPMUSVIX"));
				txtStartupMusvix.setFont(new Font("Tahoma", Font.PLAIN, 14));
				txtStartupMusvix.setBounds(409, 563, 408, 20);
				settings_panel.add(txtStartupMusvix);
				
				 JCheckBox StartupSwitch = new JCheckBox(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/on_icon.png")));
				 StartupSwitch.setSelectedIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/off_icon.png")));
				 StartupSwitch.setBorderPainted(false);
				 StartupSwitch.setContentAreaFilled(false);
				 StartupSwitch.setRolloverEnabled(false);
				 StartupSwitch.setBounds(823, 563, 39, 20);
				 settings_panel.add(StartupSwitch);
				
				//ABOUT MUSVIX
				JButton aboutmusvix = new JButton(rb.getString("ABOUTMUSVIX"));
				aboutmusvix.setFont(new Font("Tahoma", Font.ITALIC, 10));
				aboutmusvix.setBounds(555, 661, 152, 20);
				aboutmusvix.setBorderPainted(false);
				aboutmusvix.setContentAreaFilled(false);
				settings_panel.add(aboutmusvix);
				aboutmusvix.addActionListener(new ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
							 try {
								 String URL = "https://www.google.com/"; //url strony
								 java.awt.Desktop.getDesktop().browse(java.net.URI.create(URL));
							 }
							 catch(Exception e) {
							 }
					}
				});
				
				
		 
		 
				JButton logout_button_1 = new JButton("");
				logout_button_1.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/logout_black.png")));
				logout_button_1.setRolloverEnabled(true);
				logout_button_1.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/logout_black_hover.png")));
				logout_button_1.setOpaque(false);
				logout_button_1.setContentAreaFilled(false);
				logout_button_1.setBorderPainted(false);
				logout_button_1.setBounds(549, 692, 204, 50);
				settings_panel.add(logout_button_1);
				logout_button_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!isPaused && !isFirst)
						{
						song.stop();
						song.close();
						}
						Logingui mlogin = new Logingui();
						mlogin.frame.setVisible(true);
						frame.dispose();
					}
				});
				
				settings_panel.setLayout(null);
				
				/////////
				// /SETINGS
				///////////////
		
		
		
		JPanel menu_panel = new JPanel();
		menu_panel.setBounds(280, 0, 1014, 70);
		frame.getContentPane().add(menu_panel);
		menu_panel.setBackground(new Color(31, 33, 38));
		menu_panel.setLayout(null);
		
		JButton myaccount_button = new JButton("");
		myaccount_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				change_panel.removeAll();
				change_panel.repaint();
				change_panel.revalidate();
				// TODO Auto-generated method stub
				change_panel.add(myacc_panel);
				change_panel.repaint();
				change_panel.revalidate();
				
			}});
		myaccount_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/my_account.png")));
		myaccount_button.setBounds(-15, 13, 204, 50);
		myaccount_button.setBorderPainted(false);
		myaccount_button.setOpaque(false);
		myaccount_button.setContentAreaFilled(false);
		myaccount_button.setRolloverEnabled(true);
		myaccount_button.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/my_account-hover.png")));
		menu_panel.add(myaccount_button);
		
		JButton mymusic_button = new JButton("");
		mymusic_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change_panel.removeAll();
				change_panel.repaint();
				change_panel.revalidate();
				// TODO Auto-generated method stub
				change_panel.add(mymusic_panel);
				change_panel.repaint();
				change_panel.revalidate();
				
			}
		});
		mymusic_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/mymusic.png")));
		mymusic_button.setOpaque(false);
		mymusic_button.setContentAreaFilled(false);
		mymusic_button.setBorderPainted(false);
		mymusic_button.setBounds(201, 13, 204, 50);
		mymusic_button.setRolloverEnabled(true);
		mymusic_button.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/mymusic-hover.png")));
		menu_panel.add(mymusic_button);
		
		JButton shop_button = new JButton("");
		shop_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change_panel.removeAll();
				change_panel.repaint();
				change_panel.revalidate();
				// TODO Auto-generated method stub
				change_panel.add(shop_panel);
				change_panel.repaint();
				change_panel.revalidate();
			}
		});
		shop_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/shop.png")));
		shop_button.setOpaque(false);
		shop_button.setContentAreaFilled(false);
		shop_button.setBorderPainted(false);
		shop_button.setBounds(417, 13, 204, 50);
		shop_button.setRolloverEnabled(true);
		shop_button.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/shop-hover.png")));
		menu_panel.add(shop_button);
		
		JButton settings_button = new JButton("");
		settings_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change_panel.removeAll();
				change_panel.repaint();
				change_panel.revalidate();
				// TODO Auto-generated method stub
				change_panel.add(settings_panel);
				change_panel.repaint();
				change_panel.revalidate();
			}
		});
		settings_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/settings.png")));
		settings_button.setOpaque(false);
		settings_button.setContentAreaFilled(false);
		settings_button.setBorderPainted(false);
		settings_button.setBounds(633, 13, 204, 50);
		settings_button.setRolloverEnabled(true);
		settings_button.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/settings-hover.png")));
		menu_panel.add(settings_button);
		
		JButton logout_button = new JButton("");
		logout_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isPaused && !isFirst)
				{
				song.stop();
				song.close();
				}
				login_panel.logged_user = "";
				Logingui mlogin = new Logingui();
				mlogin.frame.setVisible(true);
				frame.dispose();
			}
		});
		logout_button.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/logout.png")));
		logout_button.setOpaque(false);
		logout_button.setContentAreaFilled(false);
		logout_button.setBorderPainted(false);
		logout_button.setBounds(833, 13, 204, 50);
		logout_button.setRolloverEnabled(true);
		logout_button.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/logout-hover.png")));
		menu_panel.add(logout_button);
		
		JPanel logo_panel = new JPanel();
		logo_panel.setBackground(SystemColor.controlHighlight);
		logo_panel.setBounds(0, 0, 280, 70);
		frame.getContentPane().add(logo_panel);
		logo_panel.setLayout(null);
		
		JLabel musvix_logo = new JLabel("");
		musvix_logo.setBackground(new Color(248, 248, 255));
		musvix_logo.setBounds(-33, 0, 260, 70);
		logo_panel.add(musvix_logo);
		musvix_logo.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/musvix_left_logo.png")));
		
		JLabel logo_figure = new JLabel("");
		logo_figure.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/logo_right.png")));
		logo_figure.setBounds(224, 0, 56, 70);
		logo_panel.add(logo_figure);
		
	
		

		wyswietlanie_baz temp = new wyswietlanie_baz();
		try {
			temp.displayMusic();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int baza_count = wyswietlanie_baz.count;

		System.out.println(baza_count);
		addSongs(baza_count,panel_music, playpause_button, full_song_time);
		addSongs(baza_count, panel_music_1, playpause_button, full_song_time);
	

		
		JCheckBox selectLightThemeButton = new JCheckBox(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/day_mode_off.png")));
		 selectLightThemeButton.setForeground(new Color(0,0,0));
		 selectLightThemeButton.setSelectedIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/day_mode.png")));
		 selectLightThemeButton.setRolloverEnabled(true);
		 selectLightThemeButton.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/day_mode.png")));
		 selectLightThemeButton.setBorderPainted(false);
		 selectLightThemeButton.setContentAreaFilled(false);
		 selectLightThemeButton.setBounds(627, 15, 39, 23);
			selectLightThemeButton.addActionListener(new ActionListener() { //zmiana dla jasnego motywu
				public void actionPerformed(ActionEvent e) {
					//zmiana koloru wewnetrznego okna
					chooseThemeLabel.setForeground(new Color(0,0,0));
					chooseLanguage.setForeground(new Color(0,0,0));
					txtLogin.setForeground(new Color(0,0,0));
//panel ustawien - LIGHT THEME
					settings_panel.setBackground(SystemColor.controlHighlight);
					myacc_panel.setBackground(new Color(189,192,208));
					mymusic_panel.setBackground(new Color(189,192,208));
					shop_panel.setBackground(new Color(189,192,208));
					//zmiana koloru zewnetrznego okna
					logo_panel.setBackground(SystemColor.controlHighlight);
					menu_panel.setBackground(new Color(31, 33, 38));
					player_panel.setBackground(new Color(250, 250, 250));
					logo_figure.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/logo_right.png")));
				}
			});
		
			buttonGroup.add(selectLightThemeButton);
			settings_panel.add(selectLightThemeButton);
			selectLightThemeButton.setSelected(true);
		
		
		
		
		
			 JCheckBox selectDarkThemeButton = new JCheckBox(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/night_mode_off.png")));
			 selectDarkThemeButton.setSelectedIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/night_mode.png")));
			 selectDarkThemeButton.setRolloverEnabled(true);
			 selectDarkThemeButton.setRolloverIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/night_mode.png")));
			 selectDarkThemeButton.setBorderPainted(false);
			 selectDarkThemeButton.setContentAreaFilled(false);
				selectDarkThemeButton.setBounds(668, 15, 39, 23);
				selectDarkThemeButton.addActionListener(new ActionListener() { //zmiana dla ciemnego motywu 
					public void actionPerformed(ActionEvent setDarkTheme) {
						//zmiana koloru wewnetrznego okna
						chooseThemeLabel.setForeground(new Color(255, 255,255));
						chooseLanguage.setForeground(new Color(255, 255,255));
						txtLogin.setForeground(new Color(255, 255,255));
						settings_panel.setBackground(Color.GRAY); //panel ustawień - DARK THEME
						myacc_panel.setBackground(Color.GRAY);
						mymusic_panel.setBackground(Color.GRAY);
						shop_panel.setBackground(Color.GRAY);
						//zmiana koloru zewnetrznego okna
						logo_panel.setBackground(Color.GRAY);
						menu_panel.setBackground(new Color(31, 33, 38));
						player_panel.setBackground(Color.darkGray);
						song_played_time.setForeground(Color.black);
						full_song_time.setForeground(Color.black);
						logo_figure.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/logo_left.png")));
					}
				});
				buttonGroup.add(selectDarkThemeButton);
				settings_panel.add(selectDarkThemeButton);
		selectLightThemeButton.setSelected(true);
		
		
		if(mysqlConnect.loadTheme() == false) {
			//zmiana koloru wewnetrznego okna
			chooseThemeLabel.setForeground(new Color(0,0,0));
			chooseLanguage.setForeground(new Color(0,0,0));
			txtLogin.setForeground(new Color(0,0,0));
//panel ustawien - LIGHT THEME
			settings_panel.setBackground(SystemColor.controlHighlight);
			myacc_panel.setBackground(new Color(189,192,208));
			mymusic_panel.setBackground(new Color(189,192,208));
			shop_panel.setBackground(new Color(189,192,208));
			//zmiana koloru zewnetrznego okna
			logo_panel.setBackground(SystemColor.controlHighlight);
			menu_panel.setBackground(new Color(31, 33, 38));
			player_panel.setBackground(new Color(250, 250, 250));
			logo_figure.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/logo_right.png")));
			
		}
		else {
			//zmiana koloru wewnetrznego okna
			chooseThemeLabel.setForeground(new Color(255, 255,255));
			chooseLanguage.setForeground(new Color(255, 255,255));
			txtLogin.setForeground(new Color(255, 255,255));
			settings_panel.setBackground(Color.GRAY); //panel ustawień - DARK THEME
			myacc_panel.setBackground(Color.GRAY);
			mymusic_panel.setBackground(Color.GRAY);
			shop_panel.setBackground(Color.GRAY);
			//zmiana koloru zewnetrznego okna
			logo_panel.setBackground(Color.GRAY);
			menu_panel.setBackground(new Color(31, 33, 38));
			player_panel.setBackground(Color.darkGray);
			song_played_time.setForeground(Color.black);
			full_song_time.setForeground(Color.black);
			logo_figure.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/logo_left.png")));
		}
	}
	
	
	
<<<<<<< HEAD
	public void playSong(String song_name)
=======
	
	public void playSong(String song_directory)
>>>>>>> branch 'master' of https://github.com/ulewq/musv
	{
		
		if (!isFirst)
		{
		song.stop();
		song.close();
		}
		else
		{
		isFirst = false;
		}
		try {
			
			//System.out.print(url);
			song = new Audio(song_name);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		song.play(volume, framePos);
		
		}
	
	public String getFrameLoop()
	{
		do {
		float PosRN = (float)song.getFramePosition();
		return Float.toString(PosRN);
		} while (isPaused);
	}
	
	public void muted_vol(JSlider volume_slider)
	{
		if (volume_slider.getValue()==-75.0)
		{
		speaker_image.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/unvolume.png")));
		
		}
		else
		{
		speaker_image.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/volume.png")));
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
       
		JSlider source = (JSlider)e.getSource();
        volume = source.getValue();    
        song.changeVolume(volume);
        muted_vol(source);
        
       
	}
	
	public void addSongs (int x,JPanel nazwa_panelu, JButton playbutton, JLabel song_time)
	{ // songCount - ilsoc piosnek w bazie, nazwapanelu azwa aeludktoo bedziemy dodawac afelki z piosenkami
		int i_pos = x;
		
		for(int i=0;i<i_pos;i++) {
			final int index = i;
			
			JPanel panel_9 = new JPanel();
			JButton bttnPlay = new JButton();
			bttnPlay.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/play-button.png")));
			bttnPlay.setSize(25, 25);
			bttnPlay.setOpaque(false);
			bttnPlay.setContentAreaFilled(false);
			bttnPlay.setBorderPainted(false);
			bttnPlay.setBounds(10,5, 30, 30);
			bttnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//playSong(wyswietlanie_baz.Utwory[index][3]);
					playSong((System.getProperty("user.dir") + wyswietlanie_baz.Utwory[index][3]));
					playbutton.setIcon(new ImageIcon(main_panel.class.getResource("/panel_glowny/img/pause.png")));
					song_time.setText("/ " + song.clipLength());
					//full_song_time.setText("/ " + song.clipLength());
					/*do {
						String PosRN = Float.toString((float)song.getFramePosition());
						song_played_time.setText(PosRN);
						} while (isPaused=true);*/
				}
				});
			panel_9.add(bttnPlay);
			panel_9.setPreferredSize(new Dimension(1300, 40));
			panel_9.setBackground(Color.GRAY);
			nazwa_panelu.add(panel_9);
			//panel_9.setLayout(new FlowLayout(FlowLayout.LEFT, 200, 0));
			panel_9.setLayout(null);
			JLabel lblArtist  = new JLabel(wyswietlanie_baz.Utwory[i][0]);
			lblArtist.setFont(new Font("Sitka Text", Font.BOLD, 18));
			lblArtist.setForeground(Color.WHITE);
			lblArtist.setBounds(200, 10, 400, 20);
			panel_9.add(lblArtist);
			JLabel lblSong = new JLabel(wyswietlanie_baz.Utwory[i][1]);
			lblSong.setFont(new Font("Sitka Text", Font.BOLD, 18));
			lblSong.setBounds(600, 10, 400, 20);
			panel_9.add(lblSong);
			lblSong.setForeground(Color.WHITE);
			JLabel lblAlbum = new JLabel(wyswietlanie_baz.Utwory[i][2]);
			lblAlbum.setFont(new Font("Sitka Text", Font.BOLD, 18));
			lblAlbum.setForeground(Color.WHITE);
			lblAlbum.setBounds(1000, 10, 400, 20);
			panel_9.add(lblAlbum);
			

	}
	}
	
}

