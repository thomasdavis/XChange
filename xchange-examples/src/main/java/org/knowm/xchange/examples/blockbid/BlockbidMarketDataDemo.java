package org.knowm.xchange.examples.blockbid;

import java.io.IOException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.blockbid.BlockbidExchange;
import org.knowm.xchange.blockbid.service.BlockbidMarketDataServiceRaw;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class BlockbidMarketDataDemo {
  private static CurrencyPair currencyPair = CurrencyPair.BTC_AUD;

  public static void main(String[] args) throws IOException {
    ExchangeSpecification exSpec = new BlockbidExchange().getDefaultExchangeSpecification();
    exSpec.setApiKey("xxx");
    exSpec.setSecretKey("xxx");
    Exchange blockbidExchange = ExchangeFactory.INSTANCE.createExchange(exSpec);

    MarketDataService marketDataService = blockbidExchange.getMarketDataService();
    raw((BlockbidMarketDataServiceRaw) marketDataService);
    generic(marketDataService);
  }

  private static void raw(BlockbidMarketDataServiceRaw rawBlockbidMarketDataService)
      throws IOException {
    System.out.println(rawBlockbidMarketDataService.getBlockbidTicker("btcaud"));
    System.out.println(rawBlockbidMarketDataService.getBlockbidTickers());
    System.out.println(rawBlockbidMarketDataService.getBlockbidMarketTrades(currencyPair));
    System.out.println(rawBlockbidMarketDataService.getBlockbidOrderBook(currencyPair));
    //
  }

  private static void generic(MarketDataService marketService) throws IOException {
    System.out.println(marketService.getOrderBook(currencyPair, 50));
    System.out.println(marketService.getTrades(currencyPair, 100));
    //        System.out.println(marketService.getTickers(currencyPair));
  }
}
