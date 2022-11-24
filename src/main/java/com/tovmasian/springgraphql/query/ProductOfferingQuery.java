package com.tovmasian.springgraphql.query;

import com.tovmasian.springgraphql.dto.ProductOffering;
import com.tovmasian.springgraphql.dto.ProductTemplate;
import io.leangen.graphql.annotations.*;
import io.leangen.graphql.execution.ResolutionEnvironment;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@GraphQLApi
@AllArgsConstructor
@Slf4j
public class ProductOfferingQuery {

    @GraphQLQuery
    public ProductOffering getProductOfferingById(@GraphQLArgument(name = "id") UUID id) {
        return ProductOffering.builder()
                .id(id)
                .productTemplateId(UUID.randomUUID())
                .name("name of productOffering".concat(String.valueOf(id)))
                .build();
    }

    @GraphQLQuery(name = "productOfferings")
    public List<ProductOffering> productOfferingsToTemplate(
            @GraphQLContext ProductTemplate productTemplate,
            @GraphQLRootContext graphql.GraphQLContext rootContext,
            @GraphQLEnvironment ResolutionEnvironment environment
    ) {
        log.info("randomArgument's value from rootContext - "+rootContext.get("randomArgument"));
        return Arrays.asList(
                generateProductOfferingByProductTemplateId(productTemplate.getId()),
                generateProductOfferingByProductTemplateId(productTemplate.getId())
        );
    }


    private ProductOffering generateProductOfferingByProductTemplateId(UUID productTemplateId) {
        UUID id = UUID.randomUUID();
        return ProductOffering.builder()
                .id(id)
                .productTemplateId(productTemplateId)
                .name("name of productOffering".concat(String.valueOf(id)))
                .build();
    }
}
