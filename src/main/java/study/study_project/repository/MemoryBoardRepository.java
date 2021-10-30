package study.study_project.repository;

import org.springframework.stereotype.Component;
import study.study_project.domain.Post;

import java.util.*;

@Component
public class MemoryBoardRepository implements BoardRepository {

    private Map<Long, Post> store = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public Post save(Post post) {
        post.setSeq(sequence);
        store.put(post.getSeq(), post);
        sequence++;
        return post;
    }

    @Override
    public Optional<Post> findOne(Long seq) {
        return Optional.ofNullable(store.get(seq));
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Post modify(Long seq, Post post) {
        validPost(seq); // 수정할 글 있는지 검증
        post.setSeq(seq);
        store.put(seq, post);

        return post;
    }

    @Override
    public Post delete(Long seq) {
        validPost(seq);
        Post post = store.get(seq);
        store.remove(seq);

        return post;
    }

    public void validPost(Long seq) {
        if (seq >= sequence) {
            throw new IllegalStateException("존재하지 않는 POST 입니다.");
        }
    }

}