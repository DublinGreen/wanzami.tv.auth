package tv.wanzami.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

/**
 * JwtUtil class to generate token and validate token
 */
public class JwtUtil {

	/**
	 * jwt secret key
	 */
	private static Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	

    /**
     * Load secret key and token expiration time from application.properties
     */
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.token.expiration.time}")
    private long expirationTime;

    // Initialize the SecretKey after the bean is constructed
    @PostConstruct
    public void init() {
    	SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

	/**
	 * jwt token expiration time
	 */
	private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

	public static SecretKey convertStringToKey(String base64Key) {
		// Decode the Base64 string and create a SecretKey
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Key));
	}

	/**
	 * method to generate token
	 * 
	 * @param username
	 * @return
	 */
	public static String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SECRET_KEY).compact();
	}

	/**
	 * Validate jwt token
	 * 
	 * @param token
	 * @return
	 */
	public static boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Extract Username from Token
	 * 
	 * @param token
	 * @return
	 */
	public static String getUsernameFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
	}
}
