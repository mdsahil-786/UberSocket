package com.sahil.ubersocket.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RideRequestDTO {
    private String pickUpLocationLatitude;
    private String pickUpLocationLongitude;
    private Integer bookingId;
    private List<Integer> driverIds;
}