package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.VideoCountryRestriction;
import tv.wanzami.repository.VideoCountryRestrictionRepository;

@Component
public class VideoCountryRestrictionQuery implements GraphQLQueryResolver {

	private VideoCountryRestrictionRepository videoCountryRestrictionRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public VideoCountryRestrictionQuery(VideoCountryRestrictionRepository videoCountryRestrictionRepository) {
		this.videoCountryRestrictionRepository = videoCountryRestrictionRepository;
	}

	public Iterable<VideoCountryRestriction> findAllVideoCountryRestrictions() {
		return videoCountryRestrictionRepository.findAll();
	}

	public long countVideoCountryRestrictions() {
		return videoCountryRestrictionRepository.count();
	}

	public Optional<VideoCountryRestriction> videoCountryRestrictionById(Long id) {
		return videoCountryRestrictionRepository.findById(id);
	}

}
