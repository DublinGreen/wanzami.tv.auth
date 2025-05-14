package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.wanzami.model.VideoCountryRestriction;

public interface VideoCountryRestrictionRepository extends JpaRepository<VideoCountryRestriction, Long> {
}