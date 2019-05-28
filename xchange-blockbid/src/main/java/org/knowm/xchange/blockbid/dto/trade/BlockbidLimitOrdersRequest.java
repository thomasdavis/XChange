package org.knowm.xchange.blockbid.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class BlockbidLimitOrdersRequest {

    @JsonProperty("orders")
    protected BlockbidLimitOrderRequest[] orders;

    @JsonProperty("market")
    protected String market;

    public BlockbidLimitOrdersRequest(String market, BlockbidLimitOrderRequest[] orders) {

        this.orders = orders;
        this.market = market;
    }

    public BlockbidLimitOrderRequest[] getOrders() {
        return this.orders;
    }

    public void setOrders(BlockbidLimitOrderRequest[] orders) {
        this.orders = orders;
    }
}

