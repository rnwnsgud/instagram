package snsProject.photogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snsProject.photogram.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
