package abdkzmv.wm2.assignment3.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long id;

    @NotEmpty(message = "title cannot be empty")
    private String title;

    @Size(min = 10, max = 500, message = "description length must be between 10 and 500 characters")
    private String description;
}