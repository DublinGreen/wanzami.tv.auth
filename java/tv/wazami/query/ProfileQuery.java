package tv.wazami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wazami.model.Profile;
import tv.wazami.repository.ProfileRepository;

@Component
public class ProfileQuery implements GraphQLQueryResolver {

	private ProfileRepository profileRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public ProfileQuery(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	public Iterable<Profile> findAllProfiles() {
		return profileRepository.findAll();
	}

	public long countProfiles() {
		return profileRepository.count();
	}

	public Optional<Profile> profileById(Long id) {
		return profileRepository.findById(id);
	}

}
