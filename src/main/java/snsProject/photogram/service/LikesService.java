package snsProject.photogram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snsProject.photogram.domain.Likes;
import snsProject.photogram.repository.LikesRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class LikesService {

    private final LikesRepository likesRepository;

    public void like(int imageId, int principalId) {
        likesRepository.like(imageId, principalId);
    }

    public void unlike(int imageId, int principalId) {
        likesRepository.unLike(imageId, principalId);
    }

//    public void iLike(int imageId, int principalId) {
//        Likes likes = new Likes();
//        likes.setImage(imageRepository.findById(imageId));
//        likes.setUser(userRepository.findById(principalId));
//        likesRepository.save(likes);
//    }


}
