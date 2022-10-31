package snsProject.photogram.dto.image;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import snsProject.photogram.domain.Image;
import snsProject.photogram.domain.User;

@Data
public class ImageUploadDto {
    private MultipartFile file;
    private String caption;

    public Image toEntity(String postImageUrl, User user) {
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }
}
