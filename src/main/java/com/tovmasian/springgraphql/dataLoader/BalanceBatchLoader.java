package com.tovmasian.springgraphql.dataLoader;

import org.dataloader.BatchLoader;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Component
public class BalanceBatchLoader implements BatchLoader<UUID, Integer> {

    private static final Executor balanceExecutors = Executors
            .newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    @Override
    public CompletionStage<List<Integer>> load(List<UUID> templateIds) {
        return CompletableFuture.supplyAsync(() -> {
            List<Integer> balances = getHardcodedBalanceByTemplateIds(templateIds);
            return balances;
        }, balanceExecutors);
    }

    private List<Integer> getHardcodedBalanceByTemplateIds(List<UUID> ids) {
        return Arrays.asList(100, 200);
    }
}
