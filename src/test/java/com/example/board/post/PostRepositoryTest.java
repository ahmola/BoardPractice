package com.example.board.post;

import com.example.board.comment.Comment;
import com.example.board.comment.CommentRepository;
import com.example.board.user.User;
import com.example.board.user.UserRepository;
import com.example.board.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    User makeUser(){
        User user = new User();
        user.setId(1L);
        user.setUsername("test1");
        user.setUserRole(UserRole.USER);
        user.setPassword("1234");

        return user;
    }

    List<Comment> makeComments(Post post, String username){
        Comment comment1 = new Comment();
        comment1.setId(1L);
        comment1.setComment("oh great");
        comment1.setUsername(username);
        comment1.setPost(post);

        Comment comment2 = new Comment();
        comment2.setId(2L);
        comment2.setComment("yeah");
        comment2.setUsername(username);
        comment2.setPost(post);

        return Arrays.asList(comment1, comment2);
    }

    List<Post> makePosts(User user){
        Post post1 = new Post();
        post1.setPostName("hi");
        post1.setId(1L);
        post1.setContent("hi my name is");
        post1.setLikes(3);
        post1.setUser(user);

        Post post2 = new Post();
        post2.setPostName("hello");
        post2.setContent("hello nice to meet you");
        post2.setLikes(1);
        post2.setId(2L);
        post2.setUser(user);

        return Arrays.asList(post1, post2);
    }

    @Test
    void testOneToMany(){
        User user = makeUser();
        List<Post> posts = makePosts(user);
        user.getPosts().addAll(posts);

        postRepository.save(posts.get(0));
        postRepository.save(posts.get(1));
        userRepository.save(user);

        User saved_user = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Failed"));

        assertNotNull(saved_user);
        assertEquals(saved_user.getPosts().size(), 2);
        System.out.println("Post : " + saved_user.getPosts());
    }

    @Test
    @Transactional
    void testOnetoManyTriple(){
        User user = makeUser();

        List<Post> posts = makePosts(user);

        List<Comment> commentsForPost1 = makeComments(posts.get(0), user.getUsername());
        List<Comment> commentsForPost2 = makeComments(posts.get(1), user.getUsername());

        posts.get(0).setComment(commentsForPost1);
        posts.get(1).setComment(commentsForPost2);
        user.setPosts(posts);

        commentRepository.save(commentsForPost1.get(0));
        commentRepository.save(commentsForPost1.get(1));
        commentRepository.save(commentsForPost2.get(0));
        commentRepository.save(commentsForPost2.get(1));

        postRepository.save(posts.get(0));
        postRepository.save(posts.get(1));

        userRepository.save(user);

        User saved_user = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Failed"));

        assertNotNull(saved_user);
        assertEquals(saved_user.getPosts().size(), 2);
        System.out.println(saved_user.getPosts());
        assertEquals(saved_user.getPosts().get(0).getComment().size(), 2);
        System.out.println(saved_user.getPosts().get(0).getComment());
        assertEquals(saved_user.getPosts().get(1).getComment().size(), 2);
        System.out.println(saved_user.getPosts().get(1).getComment());

    }
}