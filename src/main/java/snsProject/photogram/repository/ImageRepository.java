package snsProject.photogram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import snsProject.photogram.domain.Image;
import snsProject.photogram.repository.custom.ImageRepositoryCustom;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer>, ImageRepositoryCustom {

    @Query(value = "SELECT i.* " +
            "FROM image i INNER JOIN (SELECT imageId, COUNT(imageId) AS likeCount FROM likes GROUP BY imageId ORDER BY likeCount DESC) AS sortQuery " +
            "ON i.id= imageId " +
            "ORDER BY likeCount DESC ", nativeQuery = true)
    List<Image> getPopular();
}
