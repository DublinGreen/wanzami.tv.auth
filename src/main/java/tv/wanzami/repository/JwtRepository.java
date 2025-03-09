package tv.wanzami.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.wanzami.model.JwtToken;

/**
 * Jwt Repository Interface
 */
public interface JwtRepository extends JpaRepository<JwtToken, Long> {
	Optional<JwtToken> findByJwt(String jwt);
}