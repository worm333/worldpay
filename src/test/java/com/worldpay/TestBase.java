package com.worldpay;

import com.worldpay.domain.Offer;
import com.worldpay.repository.OfferRepository;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by eugeniuparvan on 10/15/18.
 */
public class TestBase {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected OfferRepository repository;

    @Before
    public void before() {
        repository.deleteAll();
    }

    protected Offer addOffer() {
        return repository.save(createOffer());
    }

    protected Offer addCanceledOffer() {
        Offer offer = createOffer();
        offer.setName("Canceled Offer");
        offer.setCanceled(true);
        return repository.save(offer);
    }

    private Offer createOffer() {
        Offer offer = new Offer();
        offer.setName("Simple offer");
        offer.setDescription("Simple description");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 30); //minus number would decrement the days
        offer.setExpiration(cal.getTime());

        offer.setPrice(100f);
        offer.setCurrency("USD");
        return offer;
    }
}
