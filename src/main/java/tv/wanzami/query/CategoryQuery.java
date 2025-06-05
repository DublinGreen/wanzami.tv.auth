package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.Category;
import tv.wanzami.repository.CategoryRepository;

@Component
public class CategoryQuery implements GraphQLQueryResolver {

	private CategoryRepository categoryRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public CategoryQuery(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Iterable<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	public long countCategories() {
		return categoryRepository.count();
	}

	public Optional<Category> categoryById(Long id) {
		return categoryRepository.findById(id);
	}
}
