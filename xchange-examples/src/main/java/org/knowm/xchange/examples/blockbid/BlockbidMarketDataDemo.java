package org.knowm.xchange.examples.blockbid;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.blockbid.BlockbidExchange;
import org.knowm.xchange.blockbid.service.BlockbidAccountServiceRaw;
import org.knowm.xchange.blockbid.service.BlockbidMarketDataServiceRaw;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.List;

public class BlockbidMarketDataDemo {
    private static CurrencyPair currencyPair = CurrencyPair.BTC_AUD;
    public static void main(String[] args) throws IOException {
        ExchangeSpecification exSpec = new BlockbidExchange().getDefaultExchangeSpecification();
        exSpec.setApiKey("11762e7c-fc4f-4675-8b68-2fdc0573ea3f");
        exSpec.setSecretKey("USaQxCPeZgO/DO6vJIW/g8xKTwq+0eD40jMejeVxNzsgVOv3XKpwlpHuu/iUFBluNyx1msVHe1vgS+IFGO601A==");
        Exchange blockbidExchange = ExchangeFactory.INSTANCE.createExchange(exSpec);

        MarketDataService marketDataService = blockbidExchange.getMarketDataService();

//        raw((BlockbidMarketDataServiceRaw) marketDataService);
        generic(marketDataService);
    }
    private static void raw(BlockbidMarketDataServiceRaw rawBlockbidMarketDataService) throws IOException {
//        System.out.println(rawBlockbidMarketDataService.getBlockbidTicker("btcaud"));
//        Thread.sleep(1000);

//        System.out.println(rawBlockbidMarketDataService.getBlockbidTickers());
//        Thread.sleep(1000);
//        System.out.println(rawBlockbidMarketDataService.getBlockbidMarketTrades("btcaud"));
        System.out.println(rawBlockbidMarketDataService.getBlockbidOrderBook(currencyPair));
//        Thread.sleep(1000);
//
    }
    private static void generic(MarketDataService marketService) throws IOException {

//        Trades trades = marketService.getTrades(currencyPair);
//        System.out.println("Account Info: " + trades.toString());

//
        System.out.println(marketService.getOrderBook(currencyPair, 50));

//        System.out.println(dataService.getTrades(currencyPair, 100));
//        Thread.sleep(1000);
    }
}
