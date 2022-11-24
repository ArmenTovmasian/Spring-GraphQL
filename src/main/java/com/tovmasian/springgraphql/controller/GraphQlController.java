package com.tovmasian.springgraphql.controller;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import io.leangen.graphql.spqr.spring.web.dto.GraphQLRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/graphql/v1")
public class GraphQlController {

    GraphQL graphQL;

    @Autowired
    public void setGraphQL(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @PostMapping
    public ExecutionResult graphQl(@RequestBody GraphQLRequest graphQLRequest) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(graphQLRequest.getQuery())
                .variables(graphQLRequest.getVariables())
                .operationName(graphQLRequest.getOperationName())
                .build();
        return graphQL.execute(executionInput);
    }
}
