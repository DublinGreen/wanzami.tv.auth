package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.JwtToken;

/**
 * Jwt Repository Interface
 */
public interface JwtRepository extends JpaRepository<JwtToken, Long> {

}