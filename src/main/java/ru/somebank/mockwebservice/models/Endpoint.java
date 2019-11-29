package ru.somebank.mockwebservice.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Endpoint {
    private String name;
    private String uri;
    private String method;
    private List<Header> headers = new ArrayList<>();
    private String body;
}
