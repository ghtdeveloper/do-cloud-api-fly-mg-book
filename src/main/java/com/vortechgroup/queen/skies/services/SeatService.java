package com.vortechgroup.queen.skies.services;

import com.vortechgroup.queen.skies.domain.FlightEntity;
import com.vortechgroup.queen.skies.domain.SeatEntity;
import com.vortechgroup.queen.skies.dto.response.SeatCollectionResponse;
import com.vortechgroup.queen.skies.dto.response.SeatResponseDto;
import com.vortechgroup.queen.skies.repository.SeatRepository;
import com.vortechgroup.queen.skies.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
@Slf4j
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatResponseDto findById(Long id) {
        return seatRepository.findById(id).orElseThrow(() -> new NotFoundException("seat does not exist")).toDto();
    }

    public SeatCollectionResponse findAll(Integer page, Integer pageSize) {
        Page<SeatEntity> seatEntities = seatRepository.findAll(PageRequest.of(page, pageSize, Sort.by("id")));
        return SeatCollectionResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPages(seatEntities.getTotalPages() - 1)
                .totalElements(seatEntities.getTotalElements())
                .seatResponseDtos(seatEntities.stream().map(SeatEntity::toDto).collect(Collectors.toList()))
                .build();
    }

    public SeatResponseDto findByFlightNumber(String flightNumber) {
        return seatRepository.findByFlight_FlightNumber(flightNumber).map(SeatEntity::toDto).orElse(null);
    }

}
