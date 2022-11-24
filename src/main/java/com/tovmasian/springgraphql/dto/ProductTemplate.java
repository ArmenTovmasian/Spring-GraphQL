package com.tovmasian.springgraphql.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ProductTemplate {
    private UUID id;
    private String name;
    private UUID eligibilityConditionId;
    private List<ProductOffering> productOfferings;
}
