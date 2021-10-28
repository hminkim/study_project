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

    // Post를 받아서 저장하고, 해당 Post의 id 리턴
    public Long savePost(Post post){
        boardRepository.save(post);
        return post.getSeq();
    }

    // Post의 id를 주면 해당 글 조회
    public Optional<Post> findOne(Long Seq){
        return (boardRepository.findOne(Seq));
    }

    // 모든 글 조회
    public List<Post> findAll(){
        return boardRepository.findAll();
    }

    // 글 수정, 수정할 글 id와 수정 내용 받아 바꾼다.
    public Long modifyPost(Long seq, Post post){
        Post modify = boardRepository.modify(seq, post);
        return modify.getSeq();
    }

    public Long deletePost(Long seq){
        Post deleted = boardRepository.delete(seq);
        return deleted.getSeq();
    }


}
