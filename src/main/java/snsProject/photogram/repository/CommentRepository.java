package snsProject.photogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snsProject.photogram.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
