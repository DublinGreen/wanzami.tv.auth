package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.User;
import tv.wanzami.repository.UserRepository;

/**
 * User Query
 */
@Component
@CrossOrigin(origins = "http://localhost:3000")
public class UserQuery implements GraphQLQueryResolver {

	private UserRepository userRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public UserQuery(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Iterable<User> findAllUsers() {
		return userRepository.findAll();
	}

	public long countUsers() {
		return userRepository.count();
	}

	public Optional<User> userById(Long id) {
		return userRepository.findById(id);
	}

}
