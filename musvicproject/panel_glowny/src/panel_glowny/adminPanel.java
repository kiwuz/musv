package panel_glowny;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class adminPanel {
	
	public JComboBox<userEntity> comboBox;
	public JButton deleteUserButton;
	public JButton beAdminButton;
	public JLabel welcomeLabel;
	private JButton degradeButton;
	
	public void wyswietlListe() {
		List <userEntity> listaUzytkownikow = new ArrayList<userEntity>();
		listaUzytkownikow = MysqlConnect.addToList();
		//System.out.println(listaUzytkownikow.size());
		for(int i = 0; i < listaUzytkownikow.size(); i++) {
			userEntity result = listaUzytkownikow.get(i);
			System.out.println(listaUzytkownikow.get(i).getLogin());
			comboBox.addItem(result);
		}
	}

	adminPanel() {
		JFrame adminFrame = new JFrame();
		JPanel adminPanel = new JPanel();
		adminFrame.setVisible(true);
		adminFrame.getContentPane().add(adminPanel);
		adminPanel.setLayout(null);
		adminFrame.setSize(800, 600);
		
		welcomeLabel = new JLabel("Witaj, " + MysqlConnect.getStringFromSQL(login_panel.temporaryLogin, "Name"));
		welcomeLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		welcomeLabel.setBounds(51, 30, 289, 67);
		adminPanel.add(welcomeLabel);
		
		comboBox = new JComboBox<userEntity>();
		comboBox.setBounds(51, 107, 108, 32);
		adminPanel.add(comboBox);
		
		beAdminButton = new JButton("Mianuj adminem");
		beAdminButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userEntity newEntity = (userEntity) comboBox.getSelectedItem();
				MysqlConnect.updateIntegerToSQL(newEntity.getLogin(), "Type", 1);
			}
		});
		
		beAdminButton.setFont(new Font("Calibri", Font.BOLD, 16));
		beAdminButton.setBounds(51, 216, 174, 32);
		adminPanel.add(beAdminButton);
		
		deleteUserButton = new JButton("Usu\u0144 u\u017Cytkownika");
		deleteUserButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userEntity newEntity = (userEntity) comboBox.getSelectedItem();
				MysqlConnect.deleteUser(newEntity.getIDUser());
				comboBox.removeItem(newEntity);
			}
		});
		deleteUserButton.setFont(new Font("Calibri", Font.BOLD, 16));
		deleteUserButton.setBounds(51, 276, 174, 32);
		adminPanel.add(deleteUserButton);
		
		degradeButton = new JButton("Degraduj u\u017Cytkownika");
		degradeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userEntity newEntity = (userEntity) comboBox.getSelectedItem();
				MysqlConnect.updateIntegerToSQL(newEntity.getLogin(), "Type", 0);
			}
		});
		degradeButton.setFont(new Font("Calibri", Font.BOLD, 16));
		degradeButton.setBounds(399, 216, 190, 32);
		adminPanel.add(degradeButton);
		
		wyswietlListe();

	}
}
