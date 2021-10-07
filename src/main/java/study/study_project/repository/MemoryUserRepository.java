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
        user.setId(sequence);
        sequence++;

        store.put(user.getId(), user);
        return user;
    }

    public Optional<User> findOne(Long id){
        return Optional.ofNullable(store.get(id));
    }

    public List<User> findAll(){
        return new ArrayList<>(store.values());
    }

}