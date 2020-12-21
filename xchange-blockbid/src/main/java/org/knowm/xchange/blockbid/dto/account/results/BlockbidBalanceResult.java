package org.knowm.xchange.blockbid.dto.account.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/** @author Benedikt */
public class BlockbidBalanceResult {
  public final String currency;
  public final BigDecimal total;
  public final BigDecimal available;

  public BlockbidBalanceResult(
      @JsonProperty("currency") String currency,
      @JsonProperty("total") BigDecimal total,
      @JsonProperty("available") BigDecimal available) {
    this.currency = currency;
    this.total = total;
    this.available = available;
  }

  public String getCurrency() {
    return this.currency;
  }

  public BigDecimal getTotal() {
    return this.total;
  }

  public BigDecimal getAvailable() {
    return this.available;
  }

  @Override
  public String toString() {
    return "Market [i" + this.total + this.currency;
  }
}
