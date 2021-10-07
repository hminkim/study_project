package study.study_project.repository;

import study.study_project.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findOne(Long id);
    List<User> findAll();
}