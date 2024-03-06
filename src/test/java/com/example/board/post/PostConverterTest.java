package com.example.board.post;

import com.example.board.comment.Comment;
import com.example.board.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostConverterTest {

    @Test
    void postToDTO() {
        User user = new User();
        user.setUsername("yo");

        Post post = new Post();
        post.setPostName("hi");
        post.setLikes(3);
        post.setContent("hi my name is");

        Comment comment = new Comment();
        comment.setComment("yeah");
        comment.setUsername("yo");
        comment.setId(1L);
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);

        post.setComment(commentList);
        post.setUser(user);

        PostDTO postDTO = PostConverter.PostToDTO(post);

        System.out.println(postDTO);
        assertNotNull(postDTO);
    }

    @Test
    void DTOToPost() {
        User user = new User();
        user.setUsername("yo");

        PostDTO postDTO = new PostDTO();
        postDTO.setUsername("hi");
        postDTO.setComments(Arrays.asList("oh", "wow", "damn"));
        postDTO.setPostName("good");
        postDTO.setLikes(3);
        postDTO.setContent("hi my name is");

        Post post = PostConverter.DTOToPost(postDTO, user);

        System.out.println(post);
        assertNotNull(post);
    }
}