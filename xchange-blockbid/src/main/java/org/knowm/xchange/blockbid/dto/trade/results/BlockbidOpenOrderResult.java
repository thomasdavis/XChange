package org.knowm.xchange.blockbid.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;

import java.math.BigDecimal;

public class BlockbidOpenOrderResult {
    public final BigDecimal price;
    public final BigDecimal volume;
    public final Order.OrderType side;
    public final CurrencyPair currencyPair;
    public final String id;
    public BlockbidOpenOrderResult(
            @JsonProperty("id") String id,
            @JsonProperty("volume") BigDecimal volume,
            @JsonProperty("side") String side,
            @JsonProperty("market") String market,
            @JsonProperty("price") BigDecimal price) {

        this.price = price;
        this.volume = volume;
        this.side = side.equals("buy") ? Order.OrderType.BID : Order.OrderType.ASK;
        this.currencyPair = new CurrencyPair(market.toUpperCase().substring(0, 3) + "/" + market.toUpperCase().substring(3) );
        this.id = id;
    }
    public BigDecimal getPrice () {
        return this.price;
    }
    public BigDecimal getVolume () {
        return this.volume;
    }
    public Order.OrderType getSide () {
        return this.side;
    }
    public CurrencyPair getCurrencyPair () {
        return this.currencyPair;
    }
    public String getId () {
        return this.id;
    }

    @Override
    public String toString() {
        return "Order: " + this.id;
    }
}