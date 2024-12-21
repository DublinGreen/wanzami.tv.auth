package tv.wazami.mutation;

import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import tv.wazami.config.PasswordEncoder;
import tv.wazami.model.AuthPayload;
import tv.wazami.model.User;
import tv.wazami.repository.UserRepository;

@Component
public class AuthMutation implements GraphQLMutationResolver {

	private UserRepository userRespository;

	public AuthMutation(UserRepository userRespository) {
		this.userRespository = userRespository;
	}

    public AuthPayload login(String username, String password) {
        // Implement your login logic (e.g., validate user and return token)
    	Optional<User> optUser = userRespository.findByEmail(username);
    	User user = optUser.get();
        return new AuthPayload("bearer-token", user);
    }

    public AuthPayload register(String username, String email, String password) {
        // Implement your registration logic (e.g., save user to the database)
    	User user = new User();
    	user.setUsername(username);
    	user.setEmail(email);
    	user.setStatus(0);    	
		PasswordEncoder passwordEncoder = new PasswordEncoder(password);
		user.setPassword(passwordEncoder.encode());
		
        return new AuthPayload("bearer-token", user);
    }

}
