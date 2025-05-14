package tv.wanzami.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import tv.wanzami.model.PaswordRecovery;

/**
 * Password Recovery Repository Interface
 */
public interface PasswordRecoveryRepository extends JpaRepository<PaswordRecovery, Long> {
	Optional<PaswordRecovery> findByUserId(Long user_id);
	Optional<PaswordRecovery> findByCode(String code);
}