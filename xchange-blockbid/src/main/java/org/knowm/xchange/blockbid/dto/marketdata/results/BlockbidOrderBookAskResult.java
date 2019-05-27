package org.knowm.xchange.blockbid.dto.marketdata.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

//LimitOrder​(Order.OrderType type, BigDecimal originalAmount,
// BigDecimal cumulativeAmount, CurrencyPair currencyPair, String id, Date timestamp, BigDecimal limitPrice)
public class BlockbidOrderBookAskResult {
    public final BigDecimal limitPrice;
    public final BigDecimal originalAmount;
    public final OrderType orderType;
    public BlockbidOrderBookAskResult(
            @JsonProperty("volume") BigDecimal originalAmount,
            @JsonProperty("price") BigDecimal limitPrice) {

        this.limitPrice = limitPrice;
        this.originalAmount = originalAmount;
        this.orderType = OrderType.ASK;
    }
    public BigDecimal getLimitPrice () {
        return this.limitPrice;
    }
    public OrderType getOrderType () {
        return this.orderType;
    }
    public BigDecimal getOriginalAmount () {
        return this.originalAmount;
    }
    @Override
    public String toString() {
        return "Price " + this.limitPrice + " " + this.originalAmount;
    }
}
