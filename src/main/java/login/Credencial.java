package login;

import java.io.Serializable;

public class Credencial implements Serializable {

	private static final long serialVersionUID = 7841770888181997282L;
	private String userEmail;
	private String password;

	 public String getUserEmail() {
			return userEmail;
		}
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}

}

