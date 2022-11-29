package com.tovmasian.springgraphql.query;

import com.tovmasian.springgraphql.CustomContext;
import com.tovmasian.springgraphql.dto.ProductTemplate;
import io.leangen.graphql.annotations.*;
import io.leangen.graphql.execution.ResolutionEnvironment;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@GraphQLApi
@AllArgsConstructor
@Slf4j
public class ProductTemplateQuery {

    @GraphQLQuery
    public List<ProductTemplate> getProductTemplates(
            @GraphQLArgument(name = "randomArgument") String randomArgument,
            @GraphQLRootContext CustomContext rootContext,
            @GraphQLEnvironment ResolutionEnvironment environment) {
        rootContext.getArguments().put("randomArgument", randomArgument);
        return Arrays.asList(generateProductTemplate(), generateProductTemplate());
    }

    @GraphQLQuery(name = "balance")
    public CompletableFuture<Integer> balance(@GraphQLContext ProductTemplate productTemplate,
                                              @GraphQLEnvironment ResolutionEnvironment environment) {
        DataLoader<UUID, Integer> dataLoader = environment.dataFetchingEnvironment.getDataLoaderRegistry().getDataLoader("BalanceBatchLoader");
        return dataLoader.load(productTemplate.getId());
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
