package com.enigma.datamanagement.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageResponse<T> {

    List<T> content;

    private Long count;

    private Integer totalPage;

    private Integer page;

    private Integer size;

    private String sortBy;
}