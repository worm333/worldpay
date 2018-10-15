package com.worldpay.controller;

import com.worldpay.TestBase;
import com.worldpay.domain.Offer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;

/**
 * Created by eugeniuparvan on 10/15/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OfferControllerTest extends TestBase {
    @Test
    public void testGetOffer() throws Exception {
        Offer offer = addOffer();
        String location = "/offer/" + offer.getId();
        mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(offer.getName()))
                .andExpect(jsonPath("$.description").value(offer.getDescription()))
                .andExpect(jsonPath("$.price").value(offer.getPrice()))
                .andExpect(jsonPath("$.currency").value(offer.getCurrency()))
                .andExpect(jsonPath("$.canceled").value(offer.isCanceled()));

        Offer canceledOffer = addCanceledOffer();
        location = "/offer/" + canceledOffer.getId();
        mockMvc.perform(get(location)).andExpect(status().isGone());

        location = "/offer/" + 999;
        mockMvc.perform(get(location)).andExpect(status().isNotFound());
    }

    @Test
    public void testCancelOffer() throws Exception {
        Offer offer = addOffer();
        Assert.assertFalse(offer.isCanceled());
        
        String location = "/offer/" + offer.getId();
        mockMvc.perform(get(location)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(offer.getName()))
                .andExpect(jsonPath("$.description").value(offer.getDescription()))
                .andExpect(jsonPath("$.price").value(offer.getPrice()))
                .andExpect(jsonPath("$.currency").value(offer.getCurrency()))
                .andExpect(jsonPath("$.canceled").value(offer.isCanceled()));

        mockMvc.perform(post(location + "/cancel")).andExpect(status().isOk());

        mockMvc.perform(get(location)).andExpect(status().isGone());

        Optional<Offer> offerOptional = repository.findById(offer.getId());
        Assert.assertTrue(offerOptional.get().isCanceled());
    }

}
