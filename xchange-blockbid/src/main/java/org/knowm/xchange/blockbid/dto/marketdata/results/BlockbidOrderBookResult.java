package org.knowm.xchange.blockbid.dto.marketdata.results;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
//Trade​(Order.OrderType type, BigDecimal originalAmount, CurrencyPair currencyPair, BigDecimal price,
// Date timestamp, String id)
/** @author Benedikt */
public class BlockbidOrderBookResult {
    public final List<BlockbidOrderBookAskResult> asks;
    public final List<BlockbidOrderBookBidResult> bids;
    public BlockbidOrderBookResult(
            @JsonProperty("asks") List<BlockbidOrderBookAskResult> asks,
            @JsonProperty("bids") List<BlockbidOrderBookBidResult> bids) {

        this.asks = asks;
        this.bids = bids;
    }
    public List<BlockbidOrderBookAskResult> getAsks () {
        return this.asks;
    }
    public List<BlockbidOrderBookBidResult> getBids () {
        return this.bids;
    }
    @Override
    public String toString() {
        return "Orderbook " + this.asks + " " + this.bids;
    }
}
