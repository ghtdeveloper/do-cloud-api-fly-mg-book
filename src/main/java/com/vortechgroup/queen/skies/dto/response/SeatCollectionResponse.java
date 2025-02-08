package com.vortechgroup.queen.skies.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class SeatCollectionResponse {
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private List<SeatResponseDto> seatResponseDtos;
}
