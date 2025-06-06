package tv.wanzami.mutation;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.config.JwtUtil;
import tv.wanzami.config.PasswordEncoder;
import tv.wanzami.enums.Role;
import tv.wanzami.infrastructure.MailRunner;
import tv.wanzami.model.EmailConfirmation;
import tv.wanzami.model.JwtToken;
import tv.wanzami.model.PaswordRecovery;
import tv.wanzami.model.User;
import tv.wanzami.repository.EmailConfirmationRepository;
import tv.wanzami.repository.JwtRepository;
import tv.wanzami.repository.PasswordRecoveryRepository;
import tv.wanzami.repository.UserRepository;
import tv.wanzami.service.EmailConfirmationService;

/**
 * User Mutation
 */
@Component
public class UserMutation implements GraphQLMutationResolver {

	private UserRepository userRepository;
	private JwtRepository jwtRepository;
	private EmailConfirmationRepository emailConfirmationRepository;
	private PasswordRecoveryRepository passwordRecoveryRepository;
    private final MailRunner mailRunner;
	
	@Autowired
	private EmailConfirmationService emailConfirmationService;
	
	public UserMutation(UserRepository userRepository, JwtRepository jwtRepository, EmailConfirmationRepository emailConfirmationRepository, PasswordRecoveryRepository passwordRecoveryRepository, MailRunner mailRunner) {
		this.userRepository = userRepository;
		this.jwtRepository = jwtRepository;
		this.emailConfirmationRepository = emailConfirmationRepository;
		this.passwordRecoveryRepository = passwordRecoveryRepository;
        this.mailRunner = mailRunner;
	}
	

	public User createUser(String firstName, String lastName, String email, String password, String role) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);

		PasswordEncoder passwordEncoder = new PasswordEncoder(password);
		user.setPassword(passwordEncoder.encode());

		user.setStatus(0);
		user.setCreated_at(new Date().toInstant());
		
		if (role != null && role.equalsIgnoreCase(Role.ADMIN.toString()) && role.equalsIgnoreCase(Role.NORMAL.toString()))
			user.setRole(role);
		
		userRepository.save(user);

		EmailConfirmation emailConfirmation = new EmailConfirmation();
		emailConfirmation.setUserId(user.getId());
		emailConfirmation.setStatus(0); // not used
		emailConfirmation.setCode(emailConfirmationService.generateConfirmationCode());
		emailConfirmation.setEmail(user.getEmail());
		emailConfirmation.setCreated_at(new Date().toInstant());
		emailConfirmationRepository.save(emailConfirmation);
		
		try {
			String link = "https://wanzami.tv/login.html?code=" + emailConfirmation.getCode() + "&id=" + user.getId();
            mailRunner.sendSignupEmail(email, "Welcome to the Wanzami Family", firstName + " " + lastName, link);
        } catch (MessagingException e) {
        }
		
		return user;
	}
	
	public String login(String email, String password) throws EntityNotFoundException {
		Optional<User> optUser = userRepository.findByEmail(email);
		PasswordEncoder passwordEncoder = new PasswordEncoder(password);
		String hashPassword = passwordEncoder.encode();
		User user = null;
		String token = null;

		if (optUser.isPresent()) {
			user = optUser.get();
			if (user.getSecret().equals(hashPassword) && user.getStatus() == 1) {
				token = JwtUtil.generateToken(email);
				user.setPassword(token);
				
				JwtToken jwtToken = new JwtToken();
				jwtToken.setStatus(1);
				jwtToken.setJwt(token);
				jwtToken.setUser(new User(user.getId()));		
				jwtToken.setCreated_at(Instant.now());
				jwtRepository.save(jwtToken);
				
				return token;

			}
		}
		
		throw new EntityNotFoundException("Email and password combination invalid!");
	}
	
	public String loginAdmin(String email, String password) throws EntityNotFoundException {
		Optional<User> optUser = userRepository.findByEmail(email);
		PasswordEncoder passwordEncoder = new PasswordEncoder(password);
		String hashPassword = passwordEncoder.encode();
		User user = null;
		String token = null;

		if (optUser.isPresent()) {
			user = optUser.get();
			if (user.getSecret().equals(hashPassword) && user.getStatus() == 1) {
				token = JwtUtil.generateToken(email);
				user.setPassword(token);
				
				JwtToken jwtToken = new JwtToken();
				jwtToken.setStatus(1);
				jwtToken.setJwt(token);
				jwtToken.setUser(new User(user.getId()));		
				jwtToken.setCreated_at(Instant.now());
				jwtRepository.save(jwtToken);
				
				return token;

			}
		}
		
		throw new EntityNotFoundException("Email and password combination invalid!");
	}
	
	public int logout(String token) throws EntityNotFoundException {
		Optional<JwtToken> optJwt = jwtRepository.findByJwt(token);
		int returnValue = 0;
		
		if (optJwt.isPresent()) {
			JwtToken jwtToken = optJwt.get();
			jwtToken.setStatus(0);			
			jwtRepository.save(jwtToken);
			jwtRepository.delete(jwtToken);
			
			returnValue = 1;
		}else {
			throw new EntityNotFoundException("jwt token is invalid!");
		}
		
		return returnValue;
	}
	
	public User confirmEmailCode(String code, Long id) throws EntityNotFoundException {
		Optional<EmailConfirmation> optEmailConfirmation = emailConfirmationRepository.findByCode(code);
		EmailConfirmation emailConfirmation = null;
		User user = null;

		if (optEmailConfirmation.isPresent()) {
			emailConfirmation = optEmailConfirmation.get();
			
			if(emailConfirmation.getUserId().toString().equals(id.toString())) {
				
				emailConfirmation.setStatus(1);
				emailConfirmation.setUpdated_at(new Date().toInstant());
				emailConfirmationRepository.save(emailConfirmation);

				Optional<User> optUser = userRepository.findByEmail(emailConfirmation.getEmail());
				user = optUser.get();
				
				user.setStatus(1); // activate user account
				user.setUpdated_at(new Date().toInstant());
				userRepository.save(user);
				
				return user;
			}
		}
		
		throw new EntityNotFoundException("Not found User to update!");
	}
	
	public Boolean passwordResetRequest(String email) throws EntityNotFoundException {
		Optional<User> optUser = userRepository.findByEmail(email);
		User user = null;

		if (optUser.isPresent()) {
			user = optUser.get();
			
			String emailConfirmation = emailConfirmationService.generateConfirmationCode();
			PaswordRecovery passwordRecovery = new PaswordRecovery();
			passwordRecovery.setUserId(user.getId());
			passwordRecovery.setStatus(0); // not used
			passwordRecovery.setCode(emailConfirmation);
			passwordRecovery.setEmail(user.getEmail());
			passwordRecovery.setCreated_at(new Date().toInstant());
			passwordRecoveryRepository.save(passwordRecovery);
			
			try {
				mailRunner.sendPasswordRecoveryEmail(email, "Wanzami account Password Reset", user.getFirstName() + " " + user.getLastName(), "https://wanzami.tv/password-reset.html?code=" + emailConfirmation + "&id=" + user.getId());
				return true;
			} catch (MessagingException e) {
				return false;
			}
		}
		
		throw new EntityNotFoundException("Email is invalid!");
	}
	
	public Boolean passwordReset(String password, String code) throws EntityNotFoundException {
		boolean returnValue = false;
		Optional<PaswordRecovery> optPasswordRecovery = passwordRecoveryRepository.findByCode(code);
		
		User user = null;
		PaswordRecovery passwordRecovery = null;
				
		if (!optPasswordRecovery.isPresent()) {
			throw new EntityNotFoundException("Password Recovery is invalid!");
		}
		
		passwordRecovery = optPasswordRecovery.get();
		Optional<User> optUser = userRepository.findById(passwordRecovery.getUserId());
		
		if (!optUser.isPresent()) {
			throw new EntityNotFoundException("User is invalid!");
		}

		user = optUser.get();
			
		if(passwordRecovery.getUserId().toString().equals(user.getId().toString()) && passwordRecovery.getStatus() == 0) {
			passwordRecovery.setStatus(1);
			passwordRecovery.setEmail("");
			passwordRecovery.setUpdated_at(new Date().toInstant());
			passwordRecoveryRepository.save(passwordRecovery);
			
			PasswordEncoder passwordEncoder = new PasswordEncoder(password);
			user.setPassword(passwordEncoder.encode());
			user.setUpdated_at(new Date().toInstant());
			userRepository.save(user);
			
			returnValue = true;
		}
		
		return returnValue;
	}
	
	
	public User softDeleteUserById(Long id) throws EntityNotFoundException {
		Optional<User> optUser = userRepository.findById(id);

		if (optUser.isPresent()) {
			User user = optUser.get();
			user.setStatus(0);
			user.setUpdated_at(new Date().toInstant());
			userRepository.save(user);
			return user;
		}

		throw new EntityNotFoundException("Not found User to update!");
	}

	public User setActiveUserById(Long id) throws EntityNotFoundException {
		Optional<User> optUser = userRepository.findById(id);

		if (optUser.isPresent()) {
			User user = optUser.get();
			user.setStatus(1);
			user.setUpdated_at(new Date().toInstant());
			userRepository.save(user);
			return user;
		}

		throw new EntityNotFoundException("Not found User to update!");
	}

}