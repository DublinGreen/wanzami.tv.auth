package tv.wanzami.query;

import java.util.Optional;
import org.springframework.stereotype.Component;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.User;
import tv.wanzami.repository.UserRepository;
import org.springframework.data.domain.Sort;

@Component
public class UserQuery implements GraphQLQueryResolver {

	private UserRepository userRepository;	

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public UserQuery(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Iterable<User> findAllUsers() {
		Sort sort = Sort.by(Sort.Direction.fromString("desc"), "id");
		System.out.println("TESTING : ");
		return userRepository.findAll(sort);
	}

	public Iterable<User> findAllActiveUsers() {
		return userRepository.findAllActiveUsers();
	}

	public long countUsers() throws Exception {
		return userRepository.count();
	}

	public Optional<User> userById(Long id) {
		return userRepository.findById(id);
	}
	
	public Optional<User> userByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
