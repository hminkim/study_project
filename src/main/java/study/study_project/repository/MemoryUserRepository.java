package study.study_project.repository;

import org.springframework.stereotype.Component;
import study.study_project.domain.User;

import java.util.*;

@Component
public class MemoryUserRepository implements UserRepository{

    private Map<Long, User> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public User save(User user) {
        user.setSeq(sequence);
        store.put(user.getSeq(), user);
        sequence++;
        return user;
    }

    public User findOne(Long seq){
        return store.get(seq);
    }

    public List<User> findAll(){
        return new ArrayList<>(store.values());
    }

}