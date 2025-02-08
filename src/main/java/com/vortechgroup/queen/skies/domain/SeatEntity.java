package com.vortechgroup.queen.skies.domain;

import com.vortechgroup.queen.skies.dto.request.CreateSeatDto;
import com.vortechgroup.queen.skies.dto.request.UpdateSeatDto;
import com.vortechgroup.queen.skies.dto.response.SeatResponseDto;
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
@Table(name = "tb_seat", schema = "core")
public class SeatEntity implements TransformFrom<CreateSeatDto, SeatEntity>, ToDTO<SeatResponseDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_seat_id_generated")
    @SequenceGenerator(name = "seq_seat_id_generated", allocationSize = 1,
            sequenceName = "seq_seat_id", schema = "core")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "seat_number", updatable = false)
    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightEntity flight;

    @Column(name = "available", nullable = false)
    private boolean available = true;

    @Override
    public SeatResponseDto toDto() {
        return SeatResponseDto.builder()
                .id(this.id)
                .seatNumber(this.seatNumber)
                .available(this.available)
                .build();
    }

    @Override
    public SeatEntity from(CreateSeatDto createSeatDto) {
        return SeatEntity.builder()
                .seatNumber(createSeatDto.getSeatNumber())
                .build();
    }

    public SeatEntity from(UpdateSeatDto updateSeatDto) {
        return SeatEntity.builder()
                .id(updateSeatDto.getId())
                .seatNumber(updateSeatDto.getSeatNumber())
                .available(updateSeatDto.getAvailable())
                .build();
    }
}
