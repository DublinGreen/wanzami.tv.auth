package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.Slider;

public interface SliderRepository extends JpaRepository<Slider, Long> {
}