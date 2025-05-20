package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.VideoRating;
import tv.wanzami.repository.VideoRatingRepository;


@Component
public class VideoRatingQuery implements GraphQLQueryResolver {

	private VideoRatingRepository repository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public VideoRatingQuery(VideoRatingRepository repository) {
		this.repository = repository;
	}

	public Iterable<VideoRating> findAllVideoRating() {
		return repository.findAll();
	}

	public long countVideoRating() {
		return repository.count();
	}

	public Optional<VideoRating> videoRatingById(Long id) {
		return repository.findById(id);
	}
	
}
