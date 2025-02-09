package com.vortechgroup.queen.skies.services;

import com.vortechgroup.queen.skies.domain.FlightEntity;
import com.vortechgroup.queen.skies.dto.request.CreateFlightDto;
import com.vortechgroup.queen.skies.dto.response.FlightCollectionResponse;
import com.vortechgroup.queen.skies.dto.response.FlightResponseDto;
import com.vortechgroup.queen.skies.repository.FlightRepository;
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
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightResponseDto findById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new NotFoundException("flight does not exist")).toDto();
    }

    public FlightCollectionResponse findAll(Integer page, Integer pageSize) {
        Page<FlightEntity> flightEntityPage = flightRepository.findAll(PageRequest.of(page, pageSize, Sort.by("id")));
        return FlightCollectionResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPages(flightEntityPage.getTotalPages() - 1)
                .totalElements(flightEntityPage.getTotalElements())
                .flightResponseDtos(flightEntityPage.stream().map(FlightEntity::toDto).collect(Collectors.toList()))
                .build();
    }

    public FlightResponseDto findFlightByNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber).map(FlightEntity::toDto).orElse(null);
    }

    public FlightResponseDto save(CreateFlightDto createFlightDto) {
        FlightEntity flight = FlightEntity.builder().build().from(createFlightDto);
        return flightRepository.save(flight).toDto();
    }

}
