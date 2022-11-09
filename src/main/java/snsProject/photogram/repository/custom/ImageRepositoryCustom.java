package snsProject.photogram.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import snsProject.photogram.domain.Image;

import java.util.List;

public interface ImageRepositoryCustom {

    Page<Image> getStory(Integer principalId, Pageable pageable, int page);
}
