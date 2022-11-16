package snsProject.photogram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snsProject.photogram.domain.Comment;
import snsProject.photogram.domain.Image;
import snsProject.photogram.domain.User;
import snsProject.photogram.handler.exception.CustomApiException;
import snsProject.photogram.repository.CommentRepository;
import snsProject.photogram.repository.ImageRepository;
import snsProject.photogram.repository.UserRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public Comment writeComment(String content, int imageId, int userId) {

        // TIP : 객체를 만들 때 id만 담아서 insert 할 수 있다.
        // 어짜피 Comment 객체의 image, user는 id 필드만 필요하기 때문이다.
        System.out.println("imageId = " + imageId);

        Image imageEntity = imageRepository.findById(imageId).orElseThrow(() -> {
            throw new CustomApiException("이미지 아이디를 찾을 수 없습니다.");
        });
//        System.out.println("imageEntity = " + imageEntity);

        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
        });

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setImage(imageEntity);
        comment.setUser(userEntity);

        return commentRepository.save(comment);
    }

    public void deleteComment(int id) {
        try {
            commentRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomApiException(e.getMessage());
        }
    }

}
