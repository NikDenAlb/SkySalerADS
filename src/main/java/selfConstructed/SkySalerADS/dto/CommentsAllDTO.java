package selfConstructed.SkySalerADS.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentsAllDTO {
    private Integer count;
    private List<CommentDTO> results;
}
