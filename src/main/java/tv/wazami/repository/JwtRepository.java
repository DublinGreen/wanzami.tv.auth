package tv.wazami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wazami.model.JwtToken;

/**
 * Jwt Repository Interface
 */
public interface JwtRepository extends JpaRepository<JwtToken, Long> {

}