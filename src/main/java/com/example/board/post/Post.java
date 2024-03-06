package com.example.board.post;

import com.example.board.comment.Comment;
import com.example.board.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Entity
public class Post {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String postName;

    @Lob
    private String content;

    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(getId(), post.getId()) && Objects.equals(getPostName(), post.getPostName()) && Objects.equals(getContent(), post.getContent()) && Objects.equals(getLikes(), post.getLikes()) && Objects.equals(getUser(), post.getUser()) && Objects.equals(getComment(), post.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPostName(), getContent(), getLikes(), getUser(), getComment());
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postName='" + postName + '\'' +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", user=" + user.getUsername() +
                ", comment=" + comment +
                '}';
    }
}
