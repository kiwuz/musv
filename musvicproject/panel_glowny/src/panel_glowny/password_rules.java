package panel_glowny;

public class password_rules  { 
	public String warnings = "";
	public void is8Character(String password)
		{
			if (password.length() >= 8)
			{
				;
			}
			else
			{
				warnings = warnings + " 8+ characters, ";
			}
		}
		
		public void isUppercase(String password)
		{
			if (password.matches(".*[A-Z].*"))
			{
				;
			}
			else
			{
			warnings = warnings + "uppercase character, ";
			}
		}
		public void isLowercase(String password)
		{
			if (password.matches(".*[a-z].*"))
			{
				;
			}
			else
			{
			warnings = warnings + "lowercase character, ";
			}
		}
		public void isSpecialCharacter(String password)
		{
			if (password.matches(".*[@#$%^&!?^=].*") )
			{
				;
			}
			else
			{
			warnings = warnings + "special character, ";
			}
		}
		public void passwordChecker(String password)
		{
			warnings = "";
			is8Character(password);
			isUppercase(password);
			isLowercase(password);
			isSpecialCharacter(password);
			
		}
	}
	
	
