package org.knowm.xchange.blockbid.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidTickerResult;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidTradeResult;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidOrderBookResult;
import org.knowm.xchange.currency.CurrencyPair;

import java.io.IOException;
import java.util.List;

public class BlockbidMarketDataServiceRaw extends BlockbidBaseService {

    public BlockbidMarketDataServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public List<BlockbidTickerResult> getBlockbidTickers() throws IOException {

        List<BlockbidTickerResult> tickersResult =
                blockbid.getTickers();

        return tickersResult;
    }

    public BlockbidTickerResult getBlockbidTicker(String currencyPair) throws IOException {

        List<BlockbidTickerResult> tickersResult =
                blockbid.getTicker(currencyPair);
        return tickersResult.get(0);
    }

    public List<BlockbidTradeResult> getBlockbidMarketTrades(CurrencyPair currencyPair) throws IOException {
        String market = currencyPair.toString().replace("/", "").toLowerCase();

        List<BlockbidTradeResult> marketTradesResult =
                blockbid.getMarketTrades(market);
        return marketTradesResult;
    }

    public BlockbidOrderBookResult getBlockbidOrderBook(CurrencyPair currencyPair)
            throws IOException {
        String market = currencyPair.toString().replace("/", "").toLowerCase();

        BlockbidOrderBookResult orderBook = blockbid.getOrderBook(market);

        return orderBook;
    }
}