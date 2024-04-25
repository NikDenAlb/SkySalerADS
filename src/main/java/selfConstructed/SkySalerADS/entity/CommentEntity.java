package selfConstructed.SkySalerADS.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

    private int count;
    private List<Result> results;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result {
        private int author;
        private String authorImage;
        private String authorFirstName;
        private long createdAt;
        private int pk;
        private String text;
    }
}