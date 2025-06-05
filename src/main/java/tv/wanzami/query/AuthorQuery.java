package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.Author;
import tv.wanzami.repository.AuthorRepository;

@Component
public class AuthorQuery implements GraphQLQueryResolver {

	private AuthorRepository authorRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public AuthorQuery(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	public Iterable<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public long countAuthors() {
		return authorRepository.count();
	}

	public Optional<Author> authorById(Long id) {
		return authorRepository.findById(id);
	}
}
