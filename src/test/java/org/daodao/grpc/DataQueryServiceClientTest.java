package org.daodao.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataQueryServiceClientTest {
    private ManagedChannel channel;
    private DataQueryServiceGrpc.DataQueryServiceBlockingStub stub;

    @BeforeEach
    public void setUp() {
        channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();
        stub = DataQueryServiceGrpc.newBlockingStub(channel);
    }

    @AfterEach
    public void tearDown() {
        if (channel != null) {
            channel.shutdown();
        }
    }

    @Test
    public void testClientQuery() {
        DataQueryRequest request = DataQueryRequest.newBuilder()
                .setQuery("SELECT * FROM users")
                .build();

        DataQueryResponse response = stub.queryData(request);
        assertEquals("Processed query: SELECT * FROM users", response.getResult());
    }
}