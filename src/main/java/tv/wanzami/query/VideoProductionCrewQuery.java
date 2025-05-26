package tv.wanzami.query;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.VideoProductionCrew;
import tv.wanzami.repository.VideoProductionCrewRepository;

@Component
public class VideoProductionCrewQuery implements GraphQLQueryResolver {

	private VideoProductionCrewRepository repository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public VideoProductionCrewQuery(VideoProductionCrewRepository repository) {
		this.repository = repository;
	}

	public Iterable<VideoProductionCrew> findAllVideoProductionCrew() {
		return repository.findAll();
	}
	
	public long countVideoProductionCrew() {
		return repository.count();
	}

	public Optional<VideoProductionCrew> videoProductionCrewById(Long id) {
		return repository.findById(id);
	}
	
	public List<VideoProductionCrew> videoProductionCrewByVideoId(Long videoId) {
		return repository.findProductionCrewByVideoId(videoId);
	}
	
	public List<VideoProductionCrew> videoProductionCrewByVideoIdAndStatus(Long videoId, int status) {
		return repository.findByVideoIdAndStatus(videoId, status);
	}
		
}
