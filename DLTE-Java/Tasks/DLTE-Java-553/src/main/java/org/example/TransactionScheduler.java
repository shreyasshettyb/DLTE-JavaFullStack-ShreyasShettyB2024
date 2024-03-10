package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TransactionScheduler {
    public static void main(String[] args) {
        final TransactionAnalysis transactionAnalysis =new TransactionAnalysis();
        final ScheduledExecutorService service= Executors.newScheduledThreadPool(1);
        final ScheduledFuture future=service.scheduleAtFixedRate(transactionAnalysis,5,5, TimeUnit.SECONDS);
        service.schedule(new Runnable() {
            @Override
            public void run() {
                future.cancel(true);
                service.shutdown();
            }
        },15,TimeUnit.SECONDS);

    }
}