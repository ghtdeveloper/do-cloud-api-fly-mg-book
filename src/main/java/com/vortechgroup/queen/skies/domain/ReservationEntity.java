package com.vortechgroup.queen.skies.domain;

import com.vortechgroup.queen.skies.dto.request.CreateReservationDto;
import com.vortechgroup.queen.skies.dto.response.ReservationResponseDto;
import com.vortechgroup.queen.skies.utils.ToDTO;
import com.vortechgroup.queen.skies.utils.TransformFrom;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_reservation", schema = "core")
public class ReservationEntity implements TransformFrom<CreateReservationDto, ReservationEntity>, ToDTO<ReservationResponseDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reservation_id_generated")
    @SequenceGenerator(name = "seq_reservation_id_generated", allocationSize = 1,
            sequenceName = "seq_reservation_id", schema = "core")
    @Column(name = "id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightEntity flight;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false, unique = true)
    private SeatEntity seat;

    @Column(name = "passenger_name", updatable = false)
    private String passengerName;

    @Column(name = "reservation_code", unique = true)
    private String reservationCode;


    @Override
    public ReservationResponseDto toDto() {
        return ReservationResponseDto.builder()
                .id(this.id)
                .flightResponseDto(this.flight.toDto())
                .seatResponseDto(this.seat.toDto())
                .passengerName(this.passengerName)
                .reservationCode(this.reservationCode)
                .build();
    }

    @Override
    public ReservationEntity from(CreateReservationDto createReservationDto) {
        return ReservationEntity.builder()
                .flight(FlightEntity.builder().flightNumber(createReservationDto.getFlightNumber()).build())
                .seat(SeatEntity.builder().seatNumber(createReservationDto.getSeatNumber()).build())
                .passengerName(createReservationDto.getPassengerName())
                .reservationCode(createReservationDto.getReservationCode())
                .build();
    }

}
