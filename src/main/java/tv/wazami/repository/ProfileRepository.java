package tv.wazami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wazami.model.Profile;

/**
 * Profile Repository Interface
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}