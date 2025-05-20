package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.VideoCategory;
import tv.wanzami.repository.VideoCategoryRepository;

@Component
public class VideoCategoryQuery implements GraphQLQueryResolver {

	private VideoCategoryRepository videoCategoryRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public VideoCategoryQuery(VideoCategoryRepository videoCategoryRepository) {
		this.videoCategoryRepository = videoCategoryRepository;
	}

	public Iterable<VideoCategory> findAllVideoCategories() {
		return videoCategoryRepository.findAll();
	}

	public long countVideoCategories() {
		return videoCategoryRepository.count();
	}

	public Optional<VideoCategory> videoCategoryById(Long id) {
		return videoCategoryRepository.findById(id);
	}
}
