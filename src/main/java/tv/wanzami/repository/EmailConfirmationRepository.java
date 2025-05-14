package tv.wanzami.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import tv.wanzami.model.EmailConfirmation;

/**
 * Email Confirmation Repository Interface
 */
public interface EmailConfirmationRepository extends JpaRepository<EmailConfirmation, Long> {
	Optional<EmailConfirmation> findByUserId(Long user_id);
	Optional<EmailConfirmation> findByCode(String code);
}