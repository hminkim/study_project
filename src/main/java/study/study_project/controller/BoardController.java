package study.study_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import study.study_project.domain.Post;
import study.study_project.domain.User;
import study.study_project.service.BoardService;
import study.study_project.service.UserService;

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
        model.addAttribute("posts", allPosts);

        return "/board/index";
    }

    @GetMapping("/save")
    public String showSaveForm(Model model){
        List<User> allUsers = userService.findAll();
        model.addAttribute("users", allUsers);

        return "/board/save";
    }

    @PostMapping("/save")
    public String savePosts(@RequestParam String userId, @RequestParam String title, @RequestParam String content) {
        Post post = new Post(userId, title, content);
        System.out.println("post = " + post);
        boardService.savePost(post);

        return "redirect:/board/index";
    }

    @GetMapping("/{id}")
    public String showModifyDeleteForm(@PathVariable Long id, Model model) {
        Optional<Post> post = boardService.findOne(id);
        List<User> allUser = userService.findAll();
        model.addAttribute("users", allUser);

        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "/board/modify_delete";
        }
        else {
            return "redirect:/board/index";
        }
    }

    @PostMapping("/modify")
    public String modifyPost(@RequestParam Long postId, @RequestParam String userId, @RequestParam String title, @RequestParam String content) {

        Post modifyPost = new Post(userId, title, content);
        boardService.modifyPost(postId, modifyPost);

        return "redirect:/board/index";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        boardService.deletePost(id);

        return "redirect:/board/index";
    }
}