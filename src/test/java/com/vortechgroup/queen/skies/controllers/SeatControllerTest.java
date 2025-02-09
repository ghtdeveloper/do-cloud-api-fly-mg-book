package com.vortechgroup.queen.skies.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vortechgroup.queen.skies.dto.request.CreateSeatDto;
import com.vortechgroup.queen.skies.services.SeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
class SeatControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SeatService seatService;

    @InjectMocks
    private SeatController seatController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(seatController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSaveSeatWithoutAuthorization() throws Exception {
        CreateSeatDto createSeatDto = new CreateSeatDto("A1",1L, true);
        mockMvc.perform(post("/api/v1.0/fly/seats/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createSeatDto)))
                .andExpect(status().isBadRequest());

        verify(seatService, times(0)).save(any(CreateSeatDto.class));
    }


}