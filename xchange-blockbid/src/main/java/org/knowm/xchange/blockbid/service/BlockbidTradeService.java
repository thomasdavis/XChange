package org.knowm.xchange.blockbid.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidTradeResult;
import org.knowm.xchange.blockbid.dto.trade.results.BlockbidLimitOrderResult;
import org.knowm.xchange.blockbid.dto.trade.results.BlockbidOpenOrderResult;
import org.knowm.xchange.blockbid.dto.trade.results.BlockbidOrderResult;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.*;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.TradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;

public class BlockbidTradeService extends BlockbidTradeServiceRaw implements TradeService {
  public BlockbidTradeService(Exchange exchange) {
    super(exchange);
  }

  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
    String side = marketOrder.getType() == Order.OrderType.BID ? "buy" : "sell";
    BlockbidOrderResult blockbidMarketOrder =
        makeBlockbidMarketOrder(
            marketOrder.getCurrencyPair(), side, marketOrder.getOriginalAmount());
    String orderId = blockbidMarketOrder.getId();
    return orderId;
  }

  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
    String side = limitOrder.getType() == Order.OrderType.BID ? "buy" : "sell";
    BlockbidLimitOrderResult blockbidLimitOrder =
        placeBlockbidLimitOrder(
            limitOrder.getCurrencyPair(),
            side,
            limitOrder.getOriginalAmount(),
            new BigDecimal("0.0001"));
    String orderId = blockbidLimitOrder.getId();
    return orderId;
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {
    List<BlockbidOpenOrderResult> blockbidOpenOrder = getBlockbidOpenOrders();
    List<LimitOrder> openOrders = new ArrayList<>(blockbidOpenOrder.size());
    for (BlockbidOpenOrderResult bbOpenOrder : blockbidOpenOrder) {
      openOrders.add(
          new LimitOrder(
              bbOpenOrder.getSide(),
              bbOpenOrder.getVolume(),
              bbOpenOrder.getCurrencyPair(),
              bbOpenOrder.getId(),
              new Date(),
              bbOpenOrder.getPrice()));
    }
    return new OpenOrders(openOrders);
  }

  public boolean cancelOrder(String orderId) throws IOException {
    return cancelBlockbidLimitOrder(orderId);
  }

  public UserTrades getTradeHistory(TradeHistoryParams params) throws IOException {
    CurrencyPair pair = ((TradeHistoryParamCurrencyPair) params).getCurrencyPair();
    List<BlockbidTradeResult> blockbidTrades = getBlockbidUserTrades(pair);
    List<UserTrade> trades = new ArrayList<>(blockbidTrades.size());
    for (BlockbidTradeResult bbTrade : blockbidTrades) {
      trades.add(
          new UserTrade(
              bbTrade.getOrderType(),
              bbTrade.getOriginalAmount(),
              bbTrade.getCurrencyPair(),
              bbTrade.getPrice(),
              bbTrade.getTimestamp(),
              bbTrade.getId(),
              bbTrade.getOrderId(),
              new BigDecimal("0"),
              Currency.AUD));
    }
    return new UserTrades(trades, Trades.TradeSortType.SortByID);
  }
}
