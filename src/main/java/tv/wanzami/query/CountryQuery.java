package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.Country;
import tv.wanzami.repository.CountryRepository;

/**
 * Country Query
 */
@Component
@CrossOrigin(origins = "http://localhost:3000")
public class CountryQuery implements GraphQLQueryResolver {

	private CountryRepository countryRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public CountryQuery(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	public Iterable<Country> findAllCountries() {
		return countryRepository.findAll();
	}

	public long countCountries() {
		return countryRepository.count();
	}

	public Optional<Country> countryById(Long id) {
		return countryRepository.findById(id);
	}

}
