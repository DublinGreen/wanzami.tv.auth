package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.VideoMeta;
import tv.wanzami.repository.VideoMetaRepository;

@Component
public class VideoMetaQuery implements GraphQLQueryResolver {

	private VideoMetaRepository repository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public VideoMetaQuery(VideoMetaRepository repository) {
		this.repository = repository;
	}

	public Iterable<VideoMeta> findAllVideoMeta() {
		return repository.findAll();
	}
	
	public long countVideoMeta() {
		return repository.count();
	}

	public Optional<VideoMeta> videoMetaById(Long id) {
		return repository.findById(id);
	}
	
}
