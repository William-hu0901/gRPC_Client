package org.daodao.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class DataQueryServiceClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        DataQueryServiceGrpc.DataQueryServiceBlockingStub stub = DataQueryServiceGrpc.newBlockingStub(channel);

        DataQueryRequest request = DataQueryRequest.newBuilder()
                .setQuery("SELECT * FROM users")
                .build();

        DataQueryResponse response = stub.queryData(request);
        System.out.println("Response: " + response.getResult());

        channel.shutdown();
    }
}