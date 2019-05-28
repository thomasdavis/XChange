package org.knowm.xchange.blockbid.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.*;

// Trade​(Order.OrderType type, BigDecimal originalAmount, CurrencyPair currencyPair, BigDecimal
// price,
// Date timestamp, String id)
/** @author Benedikt */
public class BlockbidOrderResult {
  public final BigDecimal price;
  public final String id;

  public BlockbidOrderResult(
      @JsonProperty("id") String id, @JsonProperty("price") BigDecimal price) {

    this.price = price;
    this.id = id;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public String getId() {
    return this.id;
  }

  @Override
  public String toString() {
    return "Order: " + this.id;
  }
}
