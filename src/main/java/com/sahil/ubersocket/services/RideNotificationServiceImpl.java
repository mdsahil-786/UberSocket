package com.sahil.ubersocket.services;

import com.sahil.ubersocket.dtos.RideRequestDTO;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



import com.sahil.ubersocket.RideNotificationRequest;
import com.sahil.ubersocket.RideNotificationResponse;
import com.sahil.ubersocket.RideNotificationServiceGrpc;


@Service
@RequiredArgsConstructor
public class RideNotificationServiceImpl extends RideNotificationServiceGrpc.RideNotificationServiceImplBase {

    private final SocketService socketService;

    @Override
    public void notifyDriversForNewRide(RideNotificationRequest request, StreamObserver<RideNotificationResponse> responseObserver) {
        RideRequestDTO rideRequestDTO = RideRequestDTO.builder()
                .pickUpLocationLatitude(request.getPickUpLocationLatitude())
                .pickUpLocationLongitude(request.getPickUpLocationLongitude())
                .bookingId(request.getBookingId())
                .driverIds(request.getDriverIdsList())
                .build();
        socketService.notifyDriversForNewRide(rideRequestDTO);
        responseObserver.onNext(RideNotificationResponse.newBuilder().setSuccess(true).build());
        responseObserver.onCompleted();
    }

}