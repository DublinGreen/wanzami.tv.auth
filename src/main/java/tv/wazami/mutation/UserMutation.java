package tv.wazami.mutation;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wazami.config.JwtUtil;
import tv.wazami.config.PasswordEncoder;
import tv.wazami.model.JwtToken;
import tv.wazami.model.User;
import tv.wazami.repository.JwtRepository;
import tv.wazami.repository.UserRepository;

/**
 * User Mutation
 */
@Component
public class UserMutation implements GraphQLMutationResolver {

	private UserRepository userRepository;
	
	private JwtRepository jwtRepository;

	public UserMutation(UserRepository userRepository, JwtRepository jwtRepository) {
		this.userRepository = userRepository;
		this.jwtRepository = jwtRepository;
	}

	public User createUser(String username, String email, String password, String telephone) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);

		PasswordEncoder passwordEncoder = new PasswordEncoder(password);
		user.setPassword(passwordEncoder.encode());

		user.setStatus(0);
		user.setTelephone(telephone);
		user.setCreated_at(new Date().toInstant());
		user.setUpdated_at(new Date().toInstant());

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

	public User updateUser(Long id, String username, String email, String password, String telephone)
			throws EntityNotFoundException {
		Optional<User> optUser = userRepository.findById(id);

		if (optUser.isPresent()) {
			User user = optUser.get();

			if (username != null)
				user.setUsername(username);

			if (email != null)
				user.setEmail(email);

			if (password != null) {
				PasswordEncoder passwordEncoder = new PasswordEncoder(password);
				user.setPassword(passwordEncoder.encode());
			}

			if (telephone != null)
				user.setTelephone(telephone);

			userRepository.save(user);
			return user;
		}

		throw new EntityNotFoundException("Not found User to update!");
	}

	public User softDeleteUserById(Long id) throws EntityNotFoundException {
		Optional<User> optUser = userRepository.findById(id);

		if (optUser.isPresent()) {
			User user = optUser.get();
			user.setStatus(0);

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

			userRepository.save(user);
			return user;
		}

		throw new EntityNotFoundException("Not found User to update!");
	}

}
