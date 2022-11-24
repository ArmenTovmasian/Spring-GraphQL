package com.tovmasian.springgraphql.query;

import com.tovmasian.springgraphql.dto.ProductTemplate;
import io.leangen.graphql.annotations.*;
import io.leangen.graphql.execution.ResolutionEnvironment;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Component
@GraphQLApi
@AllArgsConstructor
@Slf4j
public class ProductTemplateQuery {

    @GraphQLQuery
    public List<ProductTemplate> getProductTemplates(
            @GraphQLArgument(name="randomArgument") String randomArgument,
            @GraphQLRootContext graphql.GraphQLContext rootContext,
            @GraphQLEnvironment ResolutionEnvironment environment) {
        rootContext.put("randomArgument",randomArgument);
        return Arrays.asList(generateProductTemplate(), generateProductTemplate());
    }

    private ProductTemplate generateProductTemplate() {
        UUID id = UUID.randomUUID();
        return ProductTemplate.builder()
                .id(id)
                .eligibilityConditionId(UUID.randomUUID())
                .name("name of productTemplate".concat(String.valueOf(id)))
                .build();
    }
}
