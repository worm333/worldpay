package com.worldpay.repository;

import com.worldpay.TestBase;
import com.worldpay.constants.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Date;

/**
 * Created by eugeniuparvan on 10/15/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OfferRepositoryTest extends TestBase {
    @Test
    public void testSearch() throws Exception {
        addOffer();
        addOffer();
        addOffer();
        addOffer();
        addCanceledOffer();
        addCanceledOffer();

        mockMvc.perform(get("/offer/search/query")).andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements").value(6));

        mockMvc.perform(get("/offer/search/query?name=offer")).andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements").value(6));

        mockMvc.perform(get("/offer/search/query?name=Simple offer")).andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements").value(4));

        mockMvc.perform(get("/offer/search/query?canceled=true")).andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements").value(2));

        String dateString = Constants.SIMPLE_DATE_FORMAT.format(new Date());

        mockMvc.perform(get("/offer/search/query?beforeDate=" + dateString)).andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements").value(0));

        mockMvc.perform(get("/offer/search/query?afterDate=" + dateString)).andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements").value(6));

        mockMvc.perform(get("/offer/search/query?afterDate=" + dateString + "&name=Simple offer&description=Simple description")).andExpect(status().isOk())
                .andExpect(jsonPath("$.page.totalElements").value(4));
    }
}
