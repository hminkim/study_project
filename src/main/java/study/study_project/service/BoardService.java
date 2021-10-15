package study.study_project.service;

import study.study_project.repository.BoardRepository;
import org.springframework.stereotype.Service;
import study.study_project.domain.Post;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // Post 받아서 저장하고, 해당 Post의 ID 리턴
    public Long savePost(Post post){
        boardRepository.save(post);
        return post.getId();
    }

    // ID를 주면 해당 글 조회
    public Optional<Post> findOne(Long id){
        return (boardRepository.findById(id));
    }

    // 모든 글 조회
    public List<Post> findAll(){
        return boardRepository.findAll();
    }

    // 글 수정, 수정할 글 id와 수정 내용 받아 바꾼다.
    public Long modifyPost(Long id, Post post){
        Post modify = boardRepository.modify(id, post);
        return modify.getId();
    }

    public Long deletePost(Long id){
        Post deleted = boardRepository.delete(id);
        return deleted.getId();
    }


}
