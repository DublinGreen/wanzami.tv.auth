package tv.wanzami.service;

import java.security.SecureRandom;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tv.wanzami.model.EmailConfirmation;
import tv.wanzami.repository.EmailConfirmationRepository;

@Service
public class EmailConfirmationService {
	private static final int CODE_LENGTH = 6;
	private final SecureRandom random = new SecureRandom();
	
	private EmailConfirmationRepository emailConfirmationRepository;

	public String generateConfirmationCode() {
		StringBuilder sb = new StringBuilder(CODE_LENGTH);
		for (int i = 0; i < CODE_LENGTH; i++) {
			sb.append((char) ('0' + random.nextInt(10))); // Generates digits 0-9
		}
		return sb.toString();
	}

	public boolean verifyCode(String email, String code, Long userId) {
		
		EmailConfirmation emailConfirmation = null;

		Optional<EmailConfirmation> optEmailConfirmation = emailConfirmationRepository.findByUserId(userId);
		if (optEmailConfirmation.isPresent()) {
			emailConfirmation = optEmailConfirmation.get();
			if(code.equals(emailConfirmation.getCode())) {
				return true;
			}
		}
		return false;
	}

}
