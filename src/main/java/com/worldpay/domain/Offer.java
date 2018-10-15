package com.worldpay.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.worldpay.json.DateDeserializer;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by eugeniuparvan on 10/10/18.
 */
@Entity
public class Offer {
    @Id
    @GeneratedValue
    private long id;

    private @NotNull String name;

    private @NotNull String description;

    @JsonDeserialize(using = DateDeserializer.class)
    private @NotNull Date expiration;

    private @NotNull Float price;

    private @NotNull String currency;

    private boolean canceled;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
