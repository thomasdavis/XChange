package org.knowm.xchange.blockbid.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BlockbidLimitOrderRequest {

    @JsonProperty("volume")
    protected BigDecimal volume;

    @JsonProperty("side")
    protected String side;

    protected String orderType;

    @JsonProperty("price")
    protected BigDecimal price;

    public BlockbidLimitOrderRequest(
            BigDecimal volume,
            String side,
            BigDecimal price) {

        this.volume = volume;
        this.orderType = "limit";
        this.side = side;
        this.price = price;
    }
    public BigDecimal getVolume () {
        return this.volume;
    }
    public String getOrderType () {
        return this.orderType;
    }
    public String getSide () {
        return this.side;
    }

    public String toString() {
        return "Order: " + this.side + this.volume + this.price;
    }
}
