package tv.wazami.mutation;

import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wazami.model.Profile;
import tv.wazami.model.User;
import tv.wazami.repository.ProfileRepository;

@Component
public class ProfileMutation implements GraphQLMutationResolver {

	private ProfileRepository profileRepository;

	public ProfileMutation(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	public Profile createProfile(Long userId, Integer status, String name, String avatar) {
		Profile profile = new Profile();
		profile.setUser(new User(userId));		
		profile.setName(name);
		profile.setStatus(status);
		profile.setAvatar(avatar);

		profileRepository.save(profile);

		return profile;
	}

	public Profile updateProfile(Long id, Integer status, String name, String avatar) throws EntityNotFoundException {
		Optional<Profile> optProfile = profileRepository.findById(id);

		if (optProfile.isPresent()) {
			Profile profile = optProfile.get();

			if (name != null)
				profile.setName(name);
			
			if (status != null)
				profile.setStatus(status);

			if (avatar != null)
				profile.setAvatar(avatar);

			profileRepository.save(profile);
			return profile;
		}

		throw new EntityNotFoundException("Not found Profile to update!");
	}

	public Profile softDeleteProfileById(Long id) throws EntityNotFoundException {
		Optional<Profile> optProfile = profileRepository.findById(id);

		if (optProfile.isPresent()) {
			Profile profile = optProfile.get();
			profile.setStatus(0);

			profileRepository.save(profile);
			return profile;
		}

		throw new EntityNotFoundException("Not found Profile to update!");
	}

	public Profile setActiveProfileById(Long id) throws EntityNotFoundException {
		Optional<Profile> optProfile = profileRepository.findById(id);

		if (optProfile.isPresent()) {
			Profile profile = optProfile.get();
			profile.setStatus(1);

			profileRepository.save(profile);
			return profile;
		}
		throw new EntityNotFoundException("Not found Profile to update!");
	}

}