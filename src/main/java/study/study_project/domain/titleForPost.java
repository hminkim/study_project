package study.study_project.domain;

import lombok.Data;

// 제목에 작성자를 띄우기 위해 userSeq를 userId로 바꾸기 위한 오브젝트
@Data
public class titleForPost {
    private Long seq;
    private String title;
    private String userId;


    public titleForPost(Long seq, String title, String userId) {
        this.seq = seq;
        this.title = title;
        this.userId = userId;
    }
}