package com.algaworks.algafoodjavaclient.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Problem {
    private Long status;
    private String userMessage;
    private List<Object> objects = new ArrayList<>();

    @Data
    public static class Object {

        private String name;
        private String userMessage;

    }
}
