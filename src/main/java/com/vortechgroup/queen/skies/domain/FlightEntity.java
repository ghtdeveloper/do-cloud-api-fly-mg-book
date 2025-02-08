package com.vortechgroup.queen.skies.domain;

import com.vortechgroup.queen.skies.dto.request.CreateFlightDto;
import com.vortechgroup.queen.skies.dto.request.UpdateFlightDto;
import com.vortechgroup.queen.skies.dto.response.FlightResponseDto;
import com.vortechgroup.queen.skies.utils.ToDTO;
import com.vortechgroup.queen.skies.utils.TransformFrom;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "tb_flight", schema = "core")
public class FlightEntity implements TransformFrom<CreateFlightDto, FlightEntity>, ToDTO<FlightResponseDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_flight_id_generated")
    @SequenceGenerator(name = "seq_flight_id_generated", allocationSize = 1,
            sequenceName = "seq_flight_id", schema = "core")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "flight_number", updatable = false)
    private String flightNumber;

    @Column(name = "origin", updatable = false)
    private String origin;

    @Column(name = "destination", updatable = false)
    private String destination;

    @Column(name = "departure_time", updatable = false)
    private String departureTime;

    @Column(name = "arrival_time", updatable = false)
    private String arrivalTime;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatEntity> seats;

    @Column(name = "active_status")
    private Character activeStatus;

    @Column(name = "created_on", updatable = false)
    private LocalDate createdOn;

    @Override
    public FlightResponseDto toDto() {
        return FlightResponseDto.builder()
                .id(this.id)
                .flightNumber(this.flightNumber)
                .origin(this.origin)
                .destination(this.destination)
                .departureTime(this.departureTime)
                .arrivalTime(this.arrivalTime)
                .activeStatus(this.activeStatus)
                .createdOn(this.createdOn)
                .build();
    }

    @Override
    public FlightEntity from(CreateFlightDto createFlightDto) {
        return FlightEntity.builder()
                .flightNumber(createFlightDto.getFlightNumber())
                .origin(createFlightDto.getOrigin())
                .destination(createFlightDto.getDestination())
                .departureTime(createFlightDto.getDepartureTime())
                .arrivalTime(createFlightDto.getArrivalTime())
                .activeStatus(createFlightDto.getActiveStatus())
                .createdOn(LocalDate.now())
                .build();
    }

    public FlightEntity from(UpdateFlightDto updateFlightDto) {
        return FlightEntity.builder()
                .id(updateFlightDto.getId())
                .departureTime(updateFlightDto.getDepartureTime())
                .arrivalTime(updateFlightDto.getArrivalTime())
                .activeStatus(updateFlightDto.getActiveStatus())
                .build();
    }
}
