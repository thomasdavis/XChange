package org.knowm.xchange.blockbid.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class BlockbidOrdersRequest {

    @JsonProperty("orders")
    protected BlockbidOrderRequest[] orders;

    @JsonProperty("market")
    protected String market;

    public BlockbidOrdersRequest(
            BlockbidOrderRequest[] orders) {

        this.orders = orders;
        this.market = "ethaud";
    }

    public BlockbidOrderRequest[] getOrders() {
        return this.orders;
    }

    public void setOrders(BlockbidOrderRequest[] orders) {
        this.orders = orders;
    }
}

//package org.knowm.xchange.bitfinex.v1.dto.trade;
//
//        import com.fasterxml.jackson.annotation.JsonProperty;
//
//public class BitfinexNewOrderMultiRequest {
//
//    @JsonProperty("request")
//    protected String request;
//
//    @JsonProperty("nonce")
//    protected String nonce;
//
//    @JsonProperty("orders")
//    protected BitfinexNewOrder[] orders;
//
//    public BitfinexNewOrderMultiRequest(String nonce, BitfinexNewOrder[] orders) {
//
//        this.request = "/v1/order/new/multi";
//        this.nonce = nonce;
//        this.orders = orders;
//    }
//
//    public String getRequest() {
//
//        return request;
//    }
//
//    public void setRequest(String request) {
//
//        this.request = request;
//    }
//
//    public String getNonce() {
//
//        return nonce;
//    }
//
//    public void setNonce(String nonce) {
//
//        this.nonce = nonce;
//    }
//
//    public BitfinexNewOrder[] getOrders() {
//
//        return orders;
//    }
//
//    public void setOrders(BitfinexNewOrder[] orders) {
//
//        this.orders = orders;
//    }
//}
//
