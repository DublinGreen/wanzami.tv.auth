package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

}