package tv.wanzami.mutation;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.config.JwtUtil;
import tv.wanzami.config.PasswordEncoder;
import tv.wanzami.enums.Role;
import tv.wanzami.model.JwtToken;
import tv.wanzami.model.User;
import tv.wanzami.repository.JwtRepository;
import tv.wanzami.repository.UserRepository;

/**
 * User Mutation
 */
@Component
@CrossOrigin(origins = "http://localhost:3000")
public class UserMutation implements GraphQLMutationResolver {

	private UserRepository userRepository;
	
	private JwtRepository jwtRepository;
	
	public UserMutation(UserRepository userRepository, JwtRepository jwtRepository) {
		this.userRepository = userRepository;
		this.jwtRepository = jwtRepository;
	}
	

	public User createUser(String username, String email, String password, String telephone, String role) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);

		PasswordEncoder passwordEncoder = new PasswordEncoder(password);
		user.setPassword(passwordEncoder.encode());

		user.setStatus(0);
		user.setTelephone(telephone);
		user.setCreated_at(new Date().toInstant());

		if (role != null && role.equalsIgnoreCase(Role.ADMIN.toString()) && role.equalsIgnoreCase(Role.NORMAL.toString()))
			user.setRole(role);
		
		userRepository.save(user);

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
			if (user.getSecret().equals(hashPassword)) {
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
			if (user.getSecret().equals(hashPassword)) {
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