package study.study_project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import study.study_project.domain.User;
import study.study_project.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String showRegister(){

        return "users/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String id, @RequestParam String password){
        User user = new User(name, id, password);
        userService.register(user);

        return "redirect:/";
    }

    @GetMapping("/index")
    public String showUsers(Model model){
        List<User> allUser = userService.findAll();
        model.addAttribute("users", allUser);

        return "users/index";
    }
}
