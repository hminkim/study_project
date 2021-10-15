package study.study_project.repository;


import org.springframework.stereotype.Component;
import study.study_project.domain.Post;

import java.util.*;

@Component
public class MemoryBoardRepository implements BoardRepository{

    private Map<Long, Post> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Post save(Post post) {
        post.setId(sequence);
        store.put(post.getId(), post);
        sequence++;
        return post;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Post modify(Long id, Post post) {
        validPost(id); // 수정할 글 있는지 검증
        post.setId(id);
        store.put(id, post);

        return post;
    }

    @Override
    public Post delete(Long id) {
        validPost(id);
        Post post = store.get(id);
        store.remove(id);

        return post;
    }

    public void validPost(Long id){
        if(id >= sequence){
            throw new IllegalStateException("존재하지 않는 POST 입니다.");
        }
    }

}