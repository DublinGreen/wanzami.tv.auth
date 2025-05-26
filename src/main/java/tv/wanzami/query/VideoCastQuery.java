package tv.wanzami.query;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.VideoCast;
import tv.wanzami.repository.VideoCastRepository;

@Component
public class VideoCastQuery implements GraphQLQueryResolver {

	private VideoCastRepository repository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public VideoCastQuery(VideoCastRepository repository) {
		this.repository = repository;
	}

	public Iterable<VideoCast> findAllVideoCasts() {
		return repository.findAll();
	}
	
	public long countVideoCasts() {
		return repository.count();
	}

	public Optional<VideoCast> videoCastById(Long id) {
		return repository.findById(id);
	}
	
	public List<VideoCast> videoCastByVideoId(Long videoId) {
		return repository.findVideoCastsByVideoId(videoId);
	}
	
}
