package com.tovmasian.springgraphql;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CustomContext {
    private Map<Object, Object> variables;
    private Map<Object, Object> arguments;

    public CustomContext() {
        variables = new HashMap<>();
        arguments = new HashMap<>();
    }
}
