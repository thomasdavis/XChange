package org.knowm.xchange.examples.blockbid;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.blockbid.BlockbidExchange;
import org.knowm.xchange.blockbid.service.BlockbidAccountServiceRaw;
import org.knowm.xchange.blockbid.service.BlockbidMarketDataServiceRaw;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;

public class BlockbidMarketDataDemo {
    public static void main(String[] args) throws IOException {
        ExchangeSpecification exSpec = new BlockbidExchange().getDefaultExchangeSpecification();
        exSpec.setApiKey("11762e7c-fc4f-4675-8b68-2fdc0573ea3f");
        exSpec.setSecretKey("USaQxCPeZgO/DO6vJIW/g8xKTwq+0eD40jMejeVxNzsgVOv3XKpwlpHuu/iUFBluNyx1msVHe1vgS+IFGO601A==");
        Exchange blockbidExchange = ExchangeFactory.INSTANCE.createExchange(exSpec);

        MarketDataService marketDataService = blockbidExchange.getMarketDataService();

        raw((BlockbidMarketDataServiceRaw) marketDataService);
        generic(marketDataService);
    }
    private static void raw(BlockbidMarketDataServiceRaw rawBlockbidMarketDataService) throws IOException {
//        System.out.println(rawBlockbidMarketDataService.getBlockbidTicker("btcaud"));
//        Thread.sleep(1000);

//        System.out.println(rawBlockbidMarketDataService.getBlockbidTickers());
//        Thread.sleep(1000);
        System.out.println(rawBlockbidMarketDataService.getBlockbidMarketHistory(currencyPair));
//        System.out.println(dataService.getBleutradeOrderBook(currencyPair, 50));
//        Thread.sleep(1000);
//
    }
    private static void generic(MarketDataService accountService) throws IOException {

//        AccountInfo accountInfo = accountService.getAccountInfo();
//        System.out.println("Account Info: " + accountInfo.toString());
//        Thread.sleep(1000);
//
//        System.out.println(dataService.getOrderBook(currencyPair, 50));
//        Thread.sleep(1000);
//
//        System.out.println(dataService.getTrades(currencyPair, 100));
//        Thread.sleep(1000);
    }
}
