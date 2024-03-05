package com.example.board.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Builder
@Entity
public class Comment {

    private Long id;

    private String username;

    @Lob
    private String comment;
}
