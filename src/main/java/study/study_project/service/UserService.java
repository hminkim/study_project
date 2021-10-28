package study.study_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study.study_project.domain.User;
import study.study_project.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long register(User user){
        userRepository.save(user);
        return user.getSeq();
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findOne(Long seq){
        return (userRepository.findOne(seq));
    }

}
