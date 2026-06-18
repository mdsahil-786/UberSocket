package com.sahil.ubersocket.client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sahil.ubersocket.RideServiceGrpc;
import com.sahil.ubersocket.RideAcceptanceRequest;
import com.sahil.ubersocket.RideAcceptanceResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;

@Component
public class GrpcClient {

    @Value("${grpc.client.port:9090}")
    private int grpcClientPort;

    @Value("${grpc.client.host:localhost}")
    private String grpcClientHost;

    private ManagedChannel channel;
    private RideServiceGrpc.RideServiceBlockingStub rideServiceStub;


    @PostConstruct
    public void init() {
        channel = ManagedChannelBuilder.forAddress(grpcClientHost, grpcClientPort)
                .usePlaintext()
                .build();

        rideServiceStub = RideServiceGrpc.newBlockingStub(channel);
    }

    public boolean acceptRide(Integer driverId, Integer bookingId) {
        RideAcceptanceRequest request = RideAcceptanceRequest.newBuilder().setDriverId(driverId).setBookingId(bookingId).build();

        RideAcceptanceResponse response = rideServiceStub.acceptRide(request); // make the grpcCall

        return response.getSuccess();

    }


}