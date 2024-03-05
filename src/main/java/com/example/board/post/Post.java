package com.example.board.post;

import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Builder
@Entity
public class Post {

    private Long id;

    private String postName;

    private Integer likes;
}
