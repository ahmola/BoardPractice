package com.example.board.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDTO {

    private String postName;
    private String content;
    private Integer likes;
    private String username;
    private List<String> comments;

}
