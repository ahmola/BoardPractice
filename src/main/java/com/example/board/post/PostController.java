package com.example.board.post;

import com.example.board.user.User;
import com.example.board.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostController {

    private final UserService userService;
    private final PostService postService;

    @GetMapping("/upload")
    public String uploadForm(Model model){
        model.addAttribute(new PostDTO());
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadPost(@ModelAttribute(name = "postDTO")PostDTO postDTO,
                             Authentication authentication){
        User user = userService.findByUsername(authentication.getName());

        Post post = new Post();
        post.setPostName(postDTO.getPostName());
        post.setContent(postDTO.getContent());
        post.setComment(new ArrayList<>());
        post.setUser(user);
        post.setLikes(0);
        postService.save(post);

        return "redirect:home";
    }
}
