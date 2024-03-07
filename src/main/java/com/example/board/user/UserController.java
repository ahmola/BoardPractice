package com.example.board.user;

import com.example.board.post.Post;
import com.example.board.post.PostConverter;
import com.example.board.post.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post:user.getPosts()) {
            postDTOS.add(PostConverter.PostToDTO(post));
        }
        model.addAttribute("posts", postDTOS);
        return "home";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute(name = "user") UserDTO userDTO){
        userService.save(userDTO);
        return "login";
    }
}
