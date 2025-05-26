package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.Cast;

public interface CastRepository extends JpaRepository<Cast, Long> {
//	Iterable<Cast> findByVideoId(Long id);
}