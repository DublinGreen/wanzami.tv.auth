package tv.wazami.config;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * App password encoder
 */
public class PasswordEncoder {
	
	private String password;
	
	/**
	 * Constructor method
	 * @param password
	 */
	public PasswordEncoder(String password) {
		this.password = password;
	}
	
	/**
	 * Password encoder
	 * @return
	 */
	public String encode() {
		return new DigestUtils("SHA3-256").digestAsHex(password);
	}

}
