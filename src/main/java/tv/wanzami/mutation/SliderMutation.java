package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Slider;
import tv.wanzami.repository.SliderRepository;

@Component
public class SliderMutation implements GraphQLMutationResolver {

	private SliderRepository repository;

	public SliderMutation(SliderRepository repository) {
		this.repository = repository;
	}
	
	public Slider createSlider(String name, String description, String image_link, String video_link,String background_link, String duration, String video_quality, Long video_id) {
		Slider slider = new Slider();
		slider.setName(name);
		slider.setStatus(0);		
		slider.setDescription(description);
		slider.setImage_link(image_link);
		slider.setBackground_link(background_link);
		slider.setVideo_link(video_link);
		slider.setDuration(duration);
		slider.setVideo_quality(video_quality);
		slider.setCreated_at(new Date().toInstant());
		repository.save(slider);

		return slider;
	}
	
	public Slider updateSlider(Long id, String name, String description, String image_link, String background_link, String video_link, String duration, String video_quality, Long video_id) throws EntityNotFoundException {
		Optional<Slider> optSlider = repository.findById(id);

		if (optSlider.isPresent()) {
			Slider slider = optSlider.get();

			if (name != null)
				slider.setName(name);
			
			if (description != null)
				slider.setDescription(description);
			
			if (image_link != null)
				slider.setDescription(image_link);
			
			if (background_link != null)
				slider.setBackground_link(background_link);			
			
			if (video_link != null)
				slider.setDescription(video_link);
			
			if (duration != null)
				slider.setDuration(duration);
			
			if (video_quality != null)
				slider.setVideo_quality(video_quality);
	
			slider.setUpdated_at(new Date().toInstant());

			repository.save(slider);
			return slider;
		}

		throw new EntityNotFoundException("Not found Cast to update!");
	}

	public Slider softDeleteSliderById(Long id) throws EntityNotFoundException {
		Optional<Slider> optSlider = repository.findById(id);

		if (optSlider.isPresent()) {
			Slider slider = optSlider.get();
			slider.setStatus(0);
			slider.setUpdated_at(new Date().toInstant());

			repository.save(slider);
			return slider;
		}

		throw new EntityNotFoundException("Not found Slider to update!");
	}

	public Slider setActiveSliderById(Long id) throws EntityNotFoundException {
		Optional<Slider> optSlider = repository.findById(id);

		if (optSlider.isPresent()) {
			Slider slider = optSlider.get();
			slider.setStatus(1);
			slider.setUpdated_at(new Date().toInstant());

			repository.save(slider);
			return slider;
		}

		throw new EntityNotFoundException("Not found Slider to update!");
	}

}
