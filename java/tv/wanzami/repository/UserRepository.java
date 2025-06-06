package tv.wazami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wazami.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);

}