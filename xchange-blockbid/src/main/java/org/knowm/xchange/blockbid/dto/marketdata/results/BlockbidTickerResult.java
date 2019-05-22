package org.knowm.xchange.blockbid.dto.marketdata.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Map;
import org.knowm.xchange.blockbid.dto.BlockbidResult;

/** @author Benedikt */
public class BlockbidTickerResult {
    public final String timestamp;
    public final String market;
    public final BigDecimal last;
    public final BigDecimal vol24h;
    public BlockbidTickerResult(
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("market") String market,
            @JsonProperty("last") BigDecimal last,
            @JsonProperty("vol24h") BigDecimal vol24h) {
        this.timestamp = timestamp;
        this.market = market;
        this.last = last;
        this.vol24h = vol24h;
    }
    public String getTimestamp () {
        return this.timestamp;
    }
    public String getCurrencyPair () {
        return this.market;
    }
    public BigDecimal getLast () {
        return this.last;
    }
    public BigDecimal getVolume () {
        return this.vol24h;
    }
    @Override
    public String toString() {
        return "Ticker " + this.timestamp + " " + this.market + " " + this.last + " " + this.vol24h;
    }
}
