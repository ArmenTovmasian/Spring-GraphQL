package com.tovmasian.springgraphql.controller;

import com.tovmasian.springgraphql.CustomContext;
import com.tovmasian.springgraphql.dataLoader.DataLoaderRegistryFactoryImpl;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import io.leangen.graphql.spqr.spring.autoconfigure.DataLoaderRegistryFactory;
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
    DataLoaderRegistryFactory dataLoaderRegistryFactory;

    @Autowired
    public void setGraphQL(GraphQL graphQL) {
        this.graphQL = graphQL;
    }

    @Autowired
    public void setDataLoaderRegistryFactory(DataLoaderRegistryFactory dataLoaderRegistryFactory) {
        this.dataLoaderRegistryFactory = dataLoaderRegistryFactory;
    }

    @PostMapping
    public ExecutionResult graphQl(@RequestBody GraphQLRequest graphQLRequest) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .context(new CustomContext())
                .query(graphQLRequest.getQuery())
                .variables(graphQLRequest.getVariables())
                .operationName(graphQLRequest.getOperationName())
                .dataLoaderRegistry(dataLoaderRegistryFactory.createDataLoaderRegistry())
                .build();
        return graphQL.execute(executionInput);
    }
}
