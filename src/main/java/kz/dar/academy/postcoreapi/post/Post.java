package kz.dar.academy.postcoreapi.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Post {

    @NotNull
    private String postId;
    @NotNull
    private String clientId;
    @NotNull
    private String recipientId;
    private String item;
    private String status;

}
