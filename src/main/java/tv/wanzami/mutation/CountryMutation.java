package tv.wanzami.mutation;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Country;
import tv.wanzami.repository.CountryRepository;

/**
 * Country Mutation
 */
@Component
@CrossOrigin(origins = "http://localhost:3000")
public class CountryMutation implements GraphQLMutationResolver {

	private CountryRepository countryRepository;

	public CountryMutation(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	public Country createCountry(String name, Integer status) {
		Country country = new Country();
		country.setName(name);
		country.setStatus(status);

		countryRepository.save(country);

		return country;
	}

	public Country updateCountry(Long id, String name) throws EntityNotFoundException {
		Optional<Country> optCountry = countryRepository.findById(id);

		if (optCountry.isPresent()) {
			Country country = optCountry.get();

			if (name != null)
				country.setName(name);

			countryRepository.save(country);
			return country;
		}

		throw new EntityNotFoundException("Not found Country to update!");
	}
	
	public Country softDeleteCountryById(Long id) throws EntityNotFoundException {
		Optional<Country> optCountry = countryRepository.findById(id);

		if (optCountry.isPresent()) {
			Country country = optCountry.get();
			country.setStatus(0);

			countryRepository.save(country);
			return country;
		}

		throw new EntityNotFoundException("Not found Country to update!");
	}

	public Country setActiveCountryById(Long id) throws EntityNotFoundException {
		Optional<Country> optCountry = countryRepository.findById(id);

		if (optCountry.isPresent()) {
			Country country = optCountry.get();
			country.setStatus(1);

			countryRepository.save(country);
			return country;
		}

		throw new EntityNotFoundException("Not found Country to update!");
	}

}
