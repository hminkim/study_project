package study.study_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import study.study_project.domain.Post;
import study.study_project.domain.User;
import study.study_project.domain.titleForPost;
import study.study_project.service.BoardService;
import study.study_project.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    @GetMapping("/index")
    public String showPosts(Model model) {
        List<Post> allPosts = boardService.findAll();
        List<titleForPost> titleForPosts = new ArrayList<>();

        for(int i = 0 ; i < allPosts.size() ; i++) {
            Post currentPost = allPosts.get(i);

            Long userSeq = currentPost.getUserSeq();
            String userId = userService.findOne(userSeq).getUserId();
            Long seq = currentPost.getSeq();
            String title = currentPost.getTitle();

            titleForPost currentTitleForPost = new titleForPost(seq,title,userId);

            titleForPosts.add(currentTitleForPost);
        }


        model.addAttribute("titleForPosts", titleForPosts);

        return "/board/index";
    }

    @GetMapping("/save")
    public String showSaveForm(Model model){
        List<User> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);

        return "/board/save";
    }

    @PostMapping("/save")
    public String savePosts(@RequestParam Long userSeq, @RequestParam String title, @RequestParam String content) {
        User user = userService.findOne(userSeq);
        Post post = new Post(userSeq, title, content);
        boardService.savePost(post);

        return "redirect:/board/index";
    }

    @GetMapping("/{seq}")
    public String showModifyDeleteForm(@PathVariable Long seq, Model model) {
        Optional<Post> post = boardService.findOne(seq);
        List<User> allUser = userService.findAll();
        model.addAttribute("users", allUser);

        if (post.isPresent()) {
            model.addAttribute("posts", post.get());
            return "/board/modify_delete";
        }
        else {
            return "redirect:/board/index";
        }
    }

    @PostMapping("/modify/{seq}")
    public String modifyPost(@RequestParam Long userSeq, @RequestParam String title, @RequestParam String content) {

        Post modifyPost = new Post(userSeq, title, content);
        boardService.modifyPost(userSeq, modifyPost);

        return "redirect:/board/index";
    }

    @GetMapping("/delete/{seq}")
    public String deletePost(@PathVariable Long seq) {
        boardService.deletePost(seq);

        return "redirect:/board/index";
    }

}