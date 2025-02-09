package com.vortechgroup.queen.skies.services;

import com.vortechgroup.queen.skies.dto.request.CreateReservationEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, CreateReservationEvent> kafkaTemplate;
    @Value("${kafka.topic.reservation}")
    private String reservationTopic;

    @PostConstruct
    public void init() {
        log.info("Kafka topic loaded: {}", reservationTopic);
    }

    public void sendReservationEvent(CreateReservationEvent event) {
        kafkaTemplate.send(reservationTopic, event.getReservationCode(), event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Reservation event sent: {}", event);
                    } else {
                        log.error("Failed to send reservation event", ex);
                    }
                });
    }
}
