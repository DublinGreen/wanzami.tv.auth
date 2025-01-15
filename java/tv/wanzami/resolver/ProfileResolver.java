package tv.wazami.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLResolver;
import tv.wazami.model.Profile;
import tv.wazami.model.User;
import tv.wazami.repository.UserRepository;

@Component
public class ProfileResolver implements GraphQLResolver<Profile> {
	
	@Autowired
	private UserRepository userRepository;

	public ProfileResolver(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUser(Profile profile) {
		return userRepository.findById(profile.getUser().getId()).orElseThrow(null);
	}
}
