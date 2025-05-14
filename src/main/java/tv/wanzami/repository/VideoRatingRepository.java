package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.wanzami.model.VideoRating;

public interface VideoRatingRepository extends JpaRepository<VideoRating, Long> {

}