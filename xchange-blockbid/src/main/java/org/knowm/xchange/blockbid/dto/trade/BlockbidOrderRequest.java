package org.knowm.xchange.blockbid.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BlockbidOrderRequest {

    @JsonProperty("volume")
    protected BigDecimal volume;

    @JsonProperty("side")
    protected String side;

    public BlockbidOrderRequest(
            BigDecimal volume,
            String side) {

        this.volume = volume;
        this.side = side;
    }
    public BigDecimal getVolume () {
        return this.volume;
    }
    public String getSide () {
        return this.side;
    }

    public String toString() {
        return "Order: " + this.side + this.volume;
    }
}
