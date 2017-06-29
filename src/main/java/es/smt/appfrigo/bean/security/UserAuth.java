package es.smt.appfrigo.bean.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import es.smt.appfrigo.bean.Default;
import es.smt.appfrigo.model.TokenDTO;

public class UserAuth implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String username;
    private String password;
    private String email;
    private String surname;
    private int enterpriseId;
    private TokenDTO token;
    private String image;
    private String currency;
    /* Spring Security related fields*/
    private List<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    private String url;
    private List<Default> items;
    private boolean emailNot;
    
	public UserAuth() {
		super();
	}
	
	public UserAuth(String username, String password, String email, int enterpriseId, TokenDTO token,
			List<Role> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired,
			boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.enterpriseId = enterpriseId;
		this.token = token;
		this.authorities = authorities;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public List<Role> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public TokenDTO getToken() {
		return token;
	}
	public void setToken(TokenDTO token) {
		this.token = token;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public List<Default> getItems() {
		return items;
	}

	public void setItems(List<Default> items) {
		this.items = items;
	}
	
	public boolean getEmailNot() {
		return emailNot;
	}

	public void setEmailNot(boolean emailNot) {
		this.emailNot = emailNot;
	}

	@Override
	public String toString() {
		return "UserAuth [username=" + username + ", password=" + password + ", email=" + email + ", enterpriseId="
				+ enterpriseId + ", token=" + token + ", authorities=" + authorities + ", accountNonExpired="
				+ accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired="
				+ credentialsNonExpired + ", enabled=" + enabled + "]";
	}

}
