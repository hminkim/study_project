package study.study_project.domain;

import lombok.Data;

@Data
public class Post {
    private Long seq;

    private String title;
    private String content;
    private Long userSeq;


    public Post(Long userSeq, String title, String content) {
        this.userSeq = userSeq;
        this.title = title;
        this.content = content;
    }
}