package snsProject.photogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snsProject.photogram.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
