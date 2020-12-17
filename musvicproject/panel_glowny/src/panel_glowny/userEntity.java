package panel_glowny;

public class userEntity {
	private int IDUser;
	private String Login;
	private String Password;
	private String Name;
	private String Gender;
	private int Type;
	private int Language;
	private int Layout;
	private String adresObrazka;
	
	public void setIDUser(int nID) {
		this.IDUser = nID;
	}
	public void setLogin(String nLogin) {
		this.Login = nLogin;
	}
	public void setPassword(String nPassword) {
		this.Password = nPassword;
	}
	public void setName(String nName) {
		this.Name = nName;
	}
	public void setGender(String nGender) {
		this.Gender = nGender;
	}
	public void setType(int nType) {
		this.Type = nType;
	}
	public void setLanguage(int nLanguage) {
		this.Language = nLanguage;
	}
	public void setLayout(int nLayout) {
		this.Layout = nLayout;
	}
	public void setAdresObrazka(String nAdres) {
		this.adresObrazka = nAdres;
	}
	

	
	public int getIDUser() {
		return this.IDUser;
	}
	public String getLogin() {
		return this.Login;
	}
	public String getPassword() {
		return this.Password;
	}
	public String getName() {
		return this.Name;
	}
	public String getGender() {
		return this.Gender;
	}
	public int getType() {
		return this.Type;
	}
	public int getLanguage() {
		return this.Language;
	}
	public int getLayout() {
		return this.Layout;
	}
	public String getAdresObrazka() {
		return this.adresObrazka;
	}
	
	public String toString() {
		return this.IDUser + ". " + this.Login;
	}
	
}
