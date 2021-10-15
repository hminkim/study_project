package study.study_project.domain;

import lombok.Data;

@Data
public class Post {
    private Long id;

    private String userId;
    private String title;
    private String content;

    public Post(Long id, String userId, String title, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}