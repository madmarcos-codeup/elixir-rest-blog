package com.example.restblog.data;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private Long id;
    private String name;
    private Collection<Post> posts;
}
