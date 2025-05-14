package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.wanzami.model.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.status = 1")
    List<User> findAllActiveUsers();

}