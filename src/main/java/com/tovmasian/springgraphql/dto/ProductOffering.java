package com.tovmasian.springgraphql.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductOffering {
    private UUID id;
    private String name;
    private UUID productTemplateId;
}
