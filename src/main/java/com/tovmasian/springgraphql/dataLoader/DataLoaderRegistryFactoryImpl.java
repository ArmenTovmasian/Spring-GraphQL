package com.tovmasian.springgraphql.dataLoader;

import io.leangen.graphql.spqr.spring.autoconfigure.DataLoaderRegistryFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class DataLoaderRegistryFactoryImpl implements DataLoaderRegistryFactory {

    DataLoaderRegistry dataLoaderRegistry;

    List<BatchLoader> loaders;

    @Override
    public DataLoaderRegistry createDataLoaderRegistry() {
        loaders.stream()
                .forEach(loader ->
                        dataLoaderRegistry.register(loader.getClass().getSimpleName(), DataLoader.newDataLoader(loader)));
        return dataLoaderRegistry;
    }


}
