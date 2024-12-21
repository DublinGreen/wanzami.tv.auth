package tv.wazami.config;

import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoder {
	
	private String password;
	
	public PasswordEncoder(String password) {
		this.password = password;
	}
	
	public String encode() {
		return new DigestUtils("SHA3-256").digestAsHex(password);
	}

}
