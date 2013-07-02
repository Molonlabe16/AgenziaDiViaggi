/**
 * @project WebVoyager
 * 
 * @package gestioneutenti.model.bean
 * 
 * @name LoginBean.java
 *
 * @description
 *
 * @author Giacomo
 * 
 */

package gestioneutenti.model.bean;

import java.io.Serializable;

public class LoginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;

	public LoginBean() {}

	public String getUsername() {
		return username;
	}

	public LoginBean setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public LoginBean setPassword(String password) {
		this.password = password;
		return this;
	}
	
	

	

}