package pl.dma.appka.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddCommentFormDto {

    @NotEmpty(message = "pole nie może być puste")
    @Size(min = 1, max = 500, message = "minimimun 1 znak, maksymalnie 500 znaków")
    private String contents;

}
