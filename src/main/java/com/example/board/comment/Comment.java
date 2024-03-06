package com.example.board.comment;

import com.example.board.post.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Entity
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String username;

    @Lob
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(getId(), comment1.getId()) && Objects.equals(getUsername(), comment1.getUsername()) && Objects.equals(getComment(), comment1.getComment()) && Objects.equals(getPost(), comment1.getPost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getComment(), getPost());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                ", post=" + post.getPostName() +
                '}';
    }
}
