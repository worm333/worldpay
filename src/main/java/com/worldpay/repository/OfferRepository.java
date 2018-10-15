package com.worldpay.repository;

import com.worldpay.constants.Constants;
import com.worldpay.domain.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

/**
 * Created by eugeniuparvan on 10/15/18.
 */
@RepositoryRestResource(collectionResourceRel = "offer", path = "offer")
@Transactional
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {


    @Query("SELECT offer FROM Offer offer WHERE " +
            "(:name IS NULL OR LOWER(offer.name) LIKE CONCAT ('%', LOWER(CAST(:name as string)), '%')) " +
            "AND (:description IS NULL OR LOWER(offer.description) LIKE CONCAT ('%', LOWER(CAST(:description as string)), '%')) " +
            "AND (CAST(:beforeDate as date) IS NULL OR offer.expiration <= :beforeDate) " +
            "AND (CAST(:afterDate as date) IS NULL OR offer.expiration >= :afterDate) " +
            "AND (:price IS NULL OR offer.price = :price) " +
            "AND (:currency IS NULL OR LOWER(offer.currency) = LOWER(CAST(:currency as string))) " +
            "AND (:canceled IS NULL OR offer.canceled = :canceled)")
    Page<Offer> query(@Param("name") String name, @Param("description") String description,
                      @Param("beforeDate") @DateTimeFormat(pattern = Constants.DATE_FORMAT_STRING) Date beforeDate,
                      @Param("afterDate") @DateTimeFormat(pattern = Constants.DATE_FORMAT_STRING) Date afterDate,
                      @Param("price") String price, @Param("currency") String currency, @Param("canceled") Boolean canceled,
                      Pageable pageable);

}
