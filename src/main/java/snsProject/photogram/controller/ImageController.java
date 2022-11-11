package snsProject.photogram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import snsProject.photogram.config.auth.PrincipalDetails;
import snsProject.photogram.domain.Image;
import snsProject.photogram.dto.image.ImageUploadDto;
import snsProject.photogram.handler.exception.CustomValidationException;
import snsProject.photogram.service.ImageService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/image/popular")
    public String popular(Model model) {

        List<Image> images = imageService.popularImage();
        model.addAttribute("images", images);
        return "image/popular";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (imageUploadDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다", null);
        }

        imageService.imageUpload(imageUploadDto, principalDetails);
        return "redirect:/user/" + principalDetails.getUser().getId();
    }

    @GetMapping({"/", "/image/story"})
    public String story() {
        return "image/story";
    }


    @GetMapping("/image/upload")
    public String upload() {
        return "image/upload";
    }
}
