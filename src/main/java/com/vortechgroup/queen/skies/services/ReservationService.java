package com.vortechgroup.queen.skies.services;

import com.vortechgroup.queen.skies.domain.FlightEntity;
import com.vortechgroup.queen.skies.domain.ReservationEntity;
import com.vortechgroup.queen.skies.domain.SeatEntity;
import com.vortechgroup.queen.skies.dto.request.CreateReservationDto;
import com.vortechgroup.queen.skies.dto.response.ReservationCollectionResponse;
import com.vortechgroup.queen.skies.dto.response.ReservationResponseDto;
import com.vortechgroup.queen.skies.repository.FlightRepository;
import com.vortechgroup.queen.skies.repository.ReservationRepository;
import com.vortechgroup.queen.skies.repository.SeatRepository;
import com.vortechgroup.queen.skies.utils.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;

    public ReservationResponseDto findById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new NotFoundException("reservation does not exist")).toDto();
    }

    public ReservationCollectionResponse findAll(Integer page, Integer pageSize) {
        Page<ReservationEntity> reservationEntities = reservationRepository.findAll(PageRequest.of(page, pageSize, Sort.by("id")));
        return ReservationCollectionResponse.builder()
                .page(page)
                .pageSize(pageSize)
                .totalPages(reservationEntities.getTotalPages() - 1)
                .totalElements(reservationEntities.getTotalElements())
                .reservationResponseDtos(reservationEntities.stream().map(ReservationEntity::toDto).collect(Collectors.toList()))
                .build();
    }

    @Transactional
    public ReservationResponseDto save(CreateReservationDto createReservationDto) {
        Optional<FlightEntity> flightEntityOptional = flightRepository.findByFlightNumber(createReservationDto.getFlightNumber());
        if (flightEntityOptional.isEmpty()) {
            throw new NotFoundException("flight does not exist");
        }
        FlightEntity flight = flightEntityOptional.get();
        Optional<SeatEntity> seatEntityOptional = seatRepository.findBySeatNumberAndFlight(createReservationDto.getSeatNumber(), flight);
        if (seatEntityOptional.isEmpty() || !seatEntityOptional.get().getAvailable()) {
            throw new RuntimeException("seat not available");
        }
        SeatEntity seat = seatEntityOptional.get();
        seat.setAvailable(false);
        seatRepository.save(seat);
        ReservationEntity reservation = ReservationEntity.builder().flight(flight).seat(seat).passengerName(createReservationDto.getPassengerName()).reservationCode(createReservationDto.getReservationCode()).build();
        return reservationRepository.save(reservation).toDto();
    }

    @Transactional
    public ReservationResponseDto update(String reservationCode) {
        Optional<ReservationEntity> reservationEntityOptional = reservationRepository.findByReservationCode(reservationCode);
        if (reservationEntityOptional.isEmpty()) {
            throw new RuntimeException("reservation not exist");
        }
        ReservationEntity reservation = reservationEntityOptional.get();
        SeatEntity seat = reservation.getSeat();
        seat.setAvailable(true);
        seatRepository.save(seat);
        return reservationRepository.save(reservation).toDto();
    }

    public List<ReservationResponseDto> findReservationByFlightNumber(String flightNumber) {
        Optional<FlightEntity> flightEntityOptional = flightRepository.findByFlightNumber(flightNumber);
        if (flightEntityOptional.isEmpty()) {
            throw new NotFoundException("flight does not exist");
        }
        return reservationRepository.findByFlight(flightEntityOptional.get()).stream().map(ReservationEntity::toDto).collect(Collectors.toList());
    }


}
