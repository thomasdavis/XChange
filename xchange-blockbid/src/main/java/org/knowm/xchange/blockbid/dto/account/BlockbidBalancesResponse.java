package org.knowm.xchange.blockbid.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class BlockbidBalancesResponse {

    public final String currency;
    public final BigDecimal amount;
    public final BigDecimal available;

    /**
     * Constructor
     *
     * @param currency
     * @param total
     * @param available
     */
    public BlockbidBalancesResponse(
            @JsonProperty("currency") String currency,
            @JsonProperty("total") BigDecimal total,
            @JsonProperty("available") BigDecimal available) {

//        this.type = type;
        this.currency = currency;
        this.amount = total;
        this.available = available;
    }

    public BigDecimal getAmount() {

        return amount;
    }

    public BigDecimal getAvailable() {

        return available;
    }

    public String getCurrency() {

        return currency;
    }



    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append(" [type=");
        builder.append(", currency=");
        builder.append(currency);
        builder.append(", amount=");
        builder.append(amount);
        builder.append(", available=");
        builder.append(available);
        builder.append("]");
        return builder.toString();
    }
}
