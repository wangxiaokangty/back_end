package com.example.blog.common.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IPage<T>{
    private List<T> records;
    private Long total;
    private int size;
    private int current;
    private int pages;

}
