package org.knowm.xchange.blockbid.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockbidOrdersRequest {

    @JsonProperty("orders")
    protected BlockbidOrderRequest[] orders;

    @JsonProperty("market")
    protected String market;

    public BlockbidOrdersRequest(String market, BlockbidOrderRequest[] orders) {

        this.orders = orders;
        this.market = market;
    }

    public BlockbidOrderRequest[] getOrders() {
        return this.orders;
    }

    public void setOrders(BlockbidOrderRequest[] orders) {
        this.orders = orders;
    }
}
