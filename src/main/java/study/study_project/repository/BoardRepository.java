package study.study_project.repository;

import study.study_project.domain.Post;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    Post save(Post post);
    Optional<Post> findOne(Long seq);
    List<Post> findAll();
    Post modify(Long seq, Post post);
    Post delete(Long seq);
}
