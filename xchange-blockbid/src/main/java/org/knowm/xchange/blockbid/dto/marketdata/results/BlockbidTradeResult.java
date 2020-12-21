package org.knowm.xchange.blockbid.dto.marketdata.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;

// Trade​(Order.OrderType type, BigDecimal originalAmount, CurrencyPair currencyPair, BigDecimal
// price,
// Date timestamp, String id)
/** @author Benedikt */
public class BlockbidTradeResult {
  public final OrderType type;
  public final BigDecimal originalAmount;
  public final CurrencyPair currencyPair;
  public final BigDecimal price;
  public final String orderId;
  public final String market;
  public Date timestamp;
  public final String id;

  public BlockbidTradeResult(
      @JsonProperty("createdAt") String createdAt,
      @JsonProperty("market") String market,
      @JsonProperty("side") String side,
      @JsonProperty("id") String id,
      @JsonProperty("price") BigDecimal price,
      @JsonProperty("orderId") String orderId,
      @JsonProperty("volume") BigDecimal volume) {
    this.type = side == "ask" ? OrderType.ASK : OrderType.BID;

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    df.setTimeZone(TimeZone.getTimeZone("GMT"));
    try {
      this.timestamp = df.parse(createdAt.substring(0, 22));
    } catch (ParseException e) {
      this.timestamp = null;
      e.printStackTrace();
    }
    this.currencyPair =
        new CurrencyPair(
            market.toUpperCase().substring(0, 3) + "/" + market.toUpperCase().substring(3));
    this.price = price;
    this.orderId = orderId;
    this.originalAmount = volume;
    this.id = id;
    this.market = market;
  }

  public OrderType getOrderType() {
    return this.type;
  }

  public String getOrderId() {
    return this.orderId;
  }

  public BigDecimal getOriginalAmount() {
    return this.originalAmount;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public Date getTimestamp() {
    return this.timestamp;
  }

  public String getId() {
    return this.id;
  }

  public CurrencyPair getCurrencyPair() {
    return this.currencyPair;
  }

  @Override
  public String toString() {
    return "Trade: " + this.market + " " + this.type + " " + this.originalAmount + " " + this.price;
  }
}
