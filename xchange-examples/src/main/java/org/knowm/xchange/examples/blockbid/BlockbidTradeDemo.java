package org.knowm.xchange.examples.blockbid;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.blockbid.BlockbidExchange;
import org.knowm.xchange.blockbid.service.BlockbidTradeServiceRaw;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.DefaultTradeHistoryParamCurrencyPair;

public class BlockbidTradeDemo {
  private static CurrencyPair currencyPair = CurrencyPair.BTC_AUD;

  public static void main(String[] args) throws IOException {
    ExchangeSpecification exSpec = new BlockbidExchange().getDefaultExchangeSpecification();
    exSpec.setApiKey("xxx");
    exSpec.setSecretKey("xxx");
    Exchange blockbidExchange = ExchangeFactory.INSTANCE.createExchange(exSpec);
    TradeService tradeService = blockbidExchange.getTradeService();
    raw((BlockbidTradeServiceRaw) tradeService);
    generic(tradeService);
  }

  private static void raw(BlockbidTradeServiceRaw rawBlockbidTradeService) throws IOException {
    System.out.println(
        rawBlockbidTradeService.makeBlockbidMarketOrder(
            currencyPair, "buy", new BigDecimal("0.000001")));
    System.out.println(
        rawBlockbidTradeService.placeBlockbidLimitOrder(
            currencyPair, "buy", new BigDecimal("0.000001"), new BigDecimal("2")));
    System.out.println(rawBlockbidTradeService.getBlockbidOpenOrders());
    System.out.println(rawBlockbidTradeService.getBlockbidUserTrades(currencyPair));
    System.out.println(rawBlockbidTradeService.cancelBlockbidLimitOrder("31574929"));
  }

  private static void generic(TradeService tradeService) throws IOException {
    System.out.println(tradeService.getOpenOrders());
    System.out.println(tradeService.cancelOrder("31508292"));
    System.out.println(
        tradeService.placeMarketOrder(
            new MarketOrder(Order.OrderType.ASK, new BigDecimal("0.001"), currencyPair)));
    System.out.println(
        tradeService.placeLimitOrder(
            new LimitOrder(
                Order.OrderType.ASK,
                new BigDecimal("0.001"),
                currencyPair,
                "1",
                new Date(),
                new BigDecimal(("0.0001")))));
    System.out.println(
        tradeService.getTradeHistory(new DefaultTradeHistoryParamCurrencyPair(currencyPair)));
  }
}
