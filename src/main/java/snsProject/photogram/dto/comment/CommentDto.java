package snsProject.photogram.dto.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


// NotNull : null 값 체크
// NotEmpty : 빈 값 or null 값 체크
// NotBlank : 빈 값 or null 값 or 공백 체크
@Data
public class CommentDto {

    @NotBlank
    private String content;
    @NotNull
    private int imageId;
}
