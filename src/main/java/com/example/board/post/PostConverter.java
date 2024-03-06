package com.example.board.post;

import com.example.board.comment.Comment;
import com.example.board.user.User;
import com.example.board.user.UserRepository;
import com.example.board.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class PostConverter {

    public static PostDTO PostToDTO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setPostName(post.getPostName());

        List<String> comments = new ArrayList<>();
        for (Comment c: post.getComment()) {
            comments.add(c.getComment());
        }

        postDTO.setComments(comments);
        postDTO.setContent(post.getContent());
        postDTO.setLikes(post.getLikes());
        postDTO.setUsername(post.getUser().getUsername());

        return postDTO;
    }

    public static Post DTOToPost(PostDTO postDTO, User user){
        Post post = new Post();

        post.setPostName(postDTO.getPostName());

        post.setUser(user);

        post.setLikes(postDTO.getLikes());

        post.setContent(postDTO.getContent());

        List<Comment> comments = new ArrayList<>();
        for (String com: postDTO.getComments()) {
            Comment comment = new Comment();
            comment.setPost(post);
            comment.setComment(com);
            comment.setUsername(postDTO.getUsername());

            comments.add(comment);
        }
        post.setComment(comments);

        return post;
    }
}
