package study.study_project.domain;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String userName;
    private String userId;
    private String userPw;

    public User(String userName, String userId, String userPw) {
        this.userName = userName;
        this.userId = userId;
        this.userPw = userPw;
    }
}
