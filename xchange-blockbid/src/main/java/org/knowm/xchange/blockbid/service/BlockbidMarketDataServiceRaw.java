package org.knowm.xchange.blockbid.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidTickerResult;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidTradeResult;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidOrderBookResult;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.exceptions.ExchangeException;

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
        System.out.println("Account Info: " + market);

        List<BlockbidTradeResult> marketTradesResult =
                blockbid.getMarketTrades(market);
        return marketTradesResult;
    }
    public BlockbidOrderBookResult getBlockbidOrderBook(CurrencyPair currencyPair)
            throws IOException {
        String market = currencyPair.toString().replace("/", "").toLowerCase();

        BlockbidOrderBookResult orderBook = blockbid.getOrderBook(market);
        System.out.println("what the");
        System.out.println(orderBook);

        return orderBook;
    }

//    public BleutradeTicker getBleutradeTicker(CurrencyPair currencyPair) throws IOException {
//
//        String pairString = BleutradeUtils.toPairString(currencyPair);
//        BleutradeTickerReturn response = bleutrade.getBleutradeTicker(pairString);
//
//        if (!response.getSuccess()) {
//            throw new ExchangeException(response.getMessage());
//        }
//
//        return response.getResult().get(0);
//    }
}

//package org.knowm.xchange.gemini.v1.service;
//
//        import java.io.IOException;
//        import java.util.ArrayList;
//        import java.util.Collection;
//        import java.util.List;
//        import org.knowm.xchange.Exchange;
//        import org.knowm.xchange.currency.CurrencyPair;
//        import org.knowm.xchange.gemini.v1.GeminiAdapters;
//        import org.knowm.xchange.gemini.v1.dto.GeminiException;
//        import org.knowm.xchange.gemini.v1.dto.marketdata.GeminiDepth;
//        import org.knowm.xchange.gemini.v1.dto.marketdata.GeminiLend;
//        import org.knowm.xchange.gemini.v1.dto.marketdata.GeminiLendDepth;
//        import org.knowm.xchange.gemini.v1.dto.marketdata.GeminiTicker;
//        import org.knowm.xchange.gemini.v1.dto.marketdata.GeminiTrade;
//
///**
// * Implementation of the market data service for Gemini
// *
// * <ul>
// *   <li>Provides access to various market data values
// * </ul>
// */
//public class GeminiMarketDataServiceRaw extends GeminiBaseService {
//
//    /**
//     * Constructor
//     *
//     * @param exchange
//     */
//    public GeminiMarketDataServiceRaw(Exchange exchange) {
//
//        super(exchange);
//    }
//
//    public GeminiTicker getGeminiTicker(String pair) throws IOException {
//
//        try {
//            GeminiTicker GeminiTicker = gemini.getTicker(pair);
//            return GeminiTicker;
//        } catch (GeminiException e) {
//            throw handleException(e);
//        }
//    }
//
//    public GeminiDepth getGeminiOrderBook(String pair, Integer limitBids, Integer limitAsks)
//            throws IOException {
//
//        try {
//            GeminiDepth GeminiDepth;
//            if (limitBids == null && limitAsks == null) {
//                GeminiDepth = gemini.getBook(pair);
//            } else {
//                GeminiDepth = gemini.getBook(pair, limitBids, limitAsks);
//            }
//            return GeminiDepth;
//        } catch (GeminiException e) {
//            throw handleException(e);
//        }
//    }
//
//    public GeminiLendDepth getGeminiLendBook(String currency, int limitBids, int limitAsks)
//            throws IOException {
//
//        try {
//            GeminiLendDepth GeminiLendDepth = gemini.getLendBook(currency, limitBids, limitAsks);
//            return GeminiLendDepth;
//        } catch (GeminiException e) {
//            throw handleException(e);
//        }
//    }
//
//    public GeminiTrade[] getGeminiTrades(String pair, long sinceTimestamp, int limitTrades)
//            throws IOException {
//
//        try {
//            GeminiTrade[] GeminiTrades = gemini.getTrades(pair, sinceTimestamp, limitTrades);
//            return GeminiTrades;
//        } catch (GeminiException e) {
//            throw handleException(e);
//        }
//    }
//
//    public GeminiLend[] getGeminiLends(String currency, long sinceTimestamp, int limitTrades)
//            throws IOException {
//
//        try {
//            GeminiLend[] GeminiLends = gemini.getLends(currency, sinceTimestamp, limitTrades);
//            return GeminiLends;
//        } catch (GeminiException e) {
//            throw handleException(e);
//        }
//    }
//
//    public Collection<String> getGeminiSymbols() throws IOException {
//
//        try {
//            return gemini.getSymbols();
//        } catch (GeminiException e) {
//            throw handleException(e);
//        }
//    }
//
//    public List<CurrencyPair> getExchangeSymbols() throws IOException {
//
//        try {
//            List<CurrencyPair> currencyPairs = new ArrayList<>();
//            for (String symbol : gemini.getSymbols()) {
//                currencyPairs.add(GeminiAdapters.adaptCurrencyPair(symbol));
//            }
//            return currencyPairs;
//        } catch (GeminiException e) {
//            throw handleException(e);
//        }
//    }
//}
