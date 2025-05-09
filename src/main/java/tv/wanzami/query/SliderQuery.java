package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.Slider;
import tv.wanzami.repository.SliderRepository;


@Component
public class SliderQuery implements GraphQLQueryResolver {

	private SliderRepository repository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public SliderQuery(SliderRepository repository) {
		this.repository = repository;
	}

	public Iterable<Slider> findAllSliders() {
		return repository.findAll();
	}
	
	public Iterable<Slider> findAllActiveSliders() {
		return repository.findByStatus(1);
	}

	public long countSlider() {
		return repository.count();
	}

	public Optional<Slider> sliderById(Long id) {
		return repository.findById(id);
	}
	
}
