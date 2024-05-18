package selfConstructed.SkySalerADS.dto;

import lombok.Data;

import java.util.List;

/**
 * no Mapper so far
 * just constructor to send out
 */
@Data
public class CommentsDTO {
    private Integer count;
    private List<CommentDTO> results;

    public CommentsDTO(List<CommentDTO> results) {
        this.results = results;
        this.count = results.size();
    }
}
