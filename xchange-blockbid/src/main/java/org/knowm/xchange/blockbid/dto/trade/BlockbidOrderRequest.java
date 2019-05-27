package org.knowm.xchange.blockbid.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BlockbidOrderRequest {

    @JsonProperty("volume")
    protected BigDecimal volume;

    @JsonProperty("side")
    protected String side;

    @JsonProperty("orderType")
    protected String orderType;

    public BlockbidOrderRequest(
            BigDecimal volume,
            String orderType,
            String side) {

        this.volume = new BigDecimal("0.0001");
        this.orderType = "market";
        this.side = "sell";
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
        return "Order " + this.side + this.volume;
    }
}
