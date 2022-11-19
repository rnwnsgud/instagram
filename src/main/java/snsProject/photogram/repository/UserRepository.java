package snsProject.photogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snsProject.photogram.domain.User;
import snsProject.photogram.repository.custom.UserRepositoryCustom;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {
    User findByUsername(String username);
}
