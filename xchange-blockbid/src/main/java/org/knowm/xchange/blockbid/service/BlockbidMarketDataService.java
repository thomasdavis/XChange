package org.knowm.xchange.blockbid.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.blockbid.dto.account.results.BlockbidBalanceResult;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidOrderBookAskResult;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidOrderBookBidResult;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidOrderBookResult;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidTradeResult;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.gdata.util.common.base.Preconditions.checkArgument;

public class BlockbidMarketDataService extends BlockbidMarketDataServiceRaw implements MarketDataService {
    public BlockbidMarketDataService(Exchange exchange) {
        super(exchange);
    }
    public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
        List<BlockbidTradeResult> blockbidTrades = getBlockbidMarketTrades(currencyPair);
        List<Trade> trades = new ArrayList<>(blockbidTrades.size());
        for (BlockbidTradeResult bbTrade : blockbidTrades) {
            trades.add(new Trade(bbTrade.getOrderType(), bbTrade.getOriginalAmount(), bbTrade.getCurrencyPair(),
                    bbTrade.getPrice(), bbTrade.getTimestamp(), bbTrade.getId()));
        }
        return new Trades(trades);
    }

    public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
        BlockbidOrderBookResult blockbidOrderbook = getBlockbidOrderBook(currencyPair);
        List<LimitOrder> asks = new ArrayList<>();
        for (BlockbidOrderBookAskResult bbAsk : blockbidOrderbook.getAsks()) {
            asks.add(new LimitOrder(Order.OrderType.ASK, bbAsk.getOriginalAmount(), currencyPair, "", new Date(), bbAsk.getLimitPrice()));
        }
        List<LimitOrder> bids = new ArrayList<>();
        for (BlockbidOrderBookBidResult bbBid : blockbidOrderbook.getBids()) {
            asks.add(new LimitOrder(Order.OrderType.ASK, bbBid.getOriginalAmount(), currencyPair, "", new Date(), bbBid.getLimitPrice()));
        }
        return new OrderBook(new Date(), asks, bids);
    }


}
