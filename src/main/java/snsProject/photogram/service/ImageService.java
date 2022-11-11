package snsProject.photogram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snsProject.photogram.config.auth.PrincipalDetails;
import snsProject.photogram.domain.Image;
import snsProject.photogram.dto.image.ImageUploadDto;
import snsProject.photogram.repository.ImageRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public List<Image> popularImage() {
        return imageRepository.getPopular();
    }

    @Transactional(readOnly = true)
    public Page<Image> imageStory(Integer principalId, Pageable pageable, int page) {
        Page<Image> images = imageRepository.getStory(principalId, pageable, page);

        images.forEach(image -> {

            image.setLikeCount(image.getLikes().size());

            image.getLikes().forEach(like -> {
                if (like.getUser().getId() == principalId) {
                    image.setLikeState(true);
                }
            });
        });

        return images;
    }

    @Value("${file.path}")
    private String uploadFolder;

    public void imageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
        System.out.println("이미지 파일이름 : " + imageFileName );
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);
        System.out.println("이미지 파일 경로 = " + imageFilePath);

        // 통신, I/O => 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);
    }
}
