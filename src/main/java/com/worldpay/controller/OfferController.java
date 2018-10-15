package com.worldpay.controller;

import com.worldpay.domain.Offer;
import com.worldpay.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Optional;

/**
 * Created by eugeniuparvan on 10/15/18.
 */
@RepositoryRestController
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/offer/{id}", produces = "application/hal+json")
    public ResponseEntity<Offer> getOffer(@PathVariable Long id) {
        Optional<Offer> offerOptional = offerRepository.findById(id);
        if (offerOptional.isPresent()) {
            if (!offerOptional.get().isCanceled())
                return new ResponseEntity(offerOptional.get(), HttpStatus.OK);
            else
                return new ResponseEntity(HttpStatus.GONE);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/offer/{id}/cancel", produces = "application/hal+json")
    public ResponseEntity<Offer> cancelOffer(@PathVariable Long id) {
        Optional<Offer> offerOptional = offerRepository.findById(id);
        if (offerOptional.isPresent()) {
            Offer offer = offerOptional.get();
            offer.setCanceled(true);
            offerRepository.save(offer);
            return new ResponseEntity(offerOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
