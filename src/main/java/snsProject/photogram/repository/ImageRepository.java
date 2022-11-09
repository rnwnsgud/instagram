package snsProject.photogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snsProject.photogram.domain.Image;
import snsProject.photogram.repository.custom.ImageRepositoryCustom;

public interface ImageRepository extends JpaRepository<Image, Integer>, ImageRepositoryCustom {
}
