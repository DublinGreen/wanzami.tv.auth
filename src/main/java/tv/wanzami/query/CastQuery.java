package tv.wanzami.query;

import java.util.Optional;
import org.springframework.stereotype.Component;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.Cast;
import tv.wanzami.repository.CastRepository;

@Component
public class CastQuery implements GraphQLQueryResolver {

	private CastRepository castRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public CastQuery(CastRepository castRepository) {
		this.castRepository = castRepository;
	}

	public Iterable<Cast> findAllCasts() {
		return castRepository.findAll();
	}

	public long countCasts() {
		return castRepository.count();
	}

	public Optional<Cast> castById(Long id) {
		return castRepository.findById(id);
	}
	
}
