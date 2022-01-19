package com.enigma.datamanagement.util;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class WebResponse<T> {

    private String message;
    private T data;
}
