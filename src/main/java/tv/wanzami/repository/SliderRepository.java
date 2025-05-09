package tv.wanzami.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.Slider;

public interface SliderRepository extends JpaRepository<Slider, Long> {
	// Custom method to find sliders with status == 1
    List<Slider> findByStatus(int status);
}