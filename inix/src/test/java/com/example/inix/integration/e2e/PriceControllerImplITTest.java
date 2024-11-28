package com.example.inix.integration.e2e;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerImplITTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findByDateProductBrand() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/prices/search")
                        .param("date", "2020-06-14T10.00.00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(
                        """
                                {
                                    "startDate": "2020-06-14T00.00.00",
                                    "endDate": "2020-12-31T23.59.59",
                                    "priceList": 1,
                                    "productId": 35455,
                                    "brandId": 1,
                                    "price": 35.50
                                }
                                """
                ));
    }
}
