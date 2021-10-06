package com.jaya.demo.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaya.demo.dto.request.ExchangeRatesDtoRequest;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ExchangeRatesResourceIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private static String URL = "/exchange";

    @Test
    @DisplayName("Should return 400 code when mandatory fields are missing")
    void shouldReturn400_WhenMandatoryFieldsAreMissing() throws Exception {

        ExchangeRatesDtoRequest requestDto = ExchangeRatesDtoRequest.builder().build();
        mvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto))
        ).andExpect(status().isBadRequest());
    }


}