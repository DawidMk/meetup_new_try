package pl.dma.appka.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.dma.appka.picture.Picture;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class AddEventFormDto {
    @NotEmpty(message = "pole nie może być puste")
    private String eventName;

    @NotEmpty(message = "pole nie może być puste")
    @Size(min = 20, message = "minimum 20 znaków opisu")
    private String description;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate endDate;

    private Picture picture;

    @Override
    public String toString() {
        return "AddEventFormDto{" +
                "eventName='" + eventName + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", picture=" + picture.getFileName() +
                '}';
    }
}
