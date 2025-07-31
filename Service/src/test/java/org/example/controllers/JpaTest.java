package org.example.controllers;

import org.example.entities.PartitionEntity;
import org.example.entities.TransportationType;
import org.example.repositories.PartitionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class JpaTest {

    @Autowired
    PartitionRepository partitionRepository;

    TestRestTemplate testRestTemplate = new TestRestTemplate(new RestTemplateBuilder());
    @Autowired
    PartitionController partitionController;

    @Test
    @Disabled
    public void namedPartitionRepoReturnPartitionList(){


        final List<PartitionEntity> partitionEntityList = partitionRepository.findByMaterial_IdAndTransportationEntity_TypeAndCreatedAtBetween(
                1L,
                TransportationType.NORMAL,
                LocalDateTime.of(LocalDate.parse("2025-07-29"), LocalTime.now()),
                LocalDateTime.of(LocalDate.parse("2025-07-30"), LocalTime.now()));

        Assert.assertNotNull(partitionEntityList);
    }

    @Test
    void testDailyReport(){
        final ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:9090/api/v1/partition/DailyReport/HTML/1/2024-01-01/2025-12-12/NORMAL", String.class);
        Assert.assertNotNull(response);
    }

    @Test
    void testController(){
        final ResponseEntity<String> html = partitionController.getDailyReport(1L, LocalDate.parse("2024-01-01"),
                LocalDate.parse("2025-12-12"),
                TransportationType.NORMAL, "HTML");
        final String htmlPage = new String(Base64.getDecoder().decode(html.getBody()));
        Assert.assertTrue(htmlPage.startsWith("<!DOCTYPE html"));
        Assert.assertNotNull(html);
    }


}
