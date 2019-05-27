package org.knowm.xchange.examples.blockbid;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.blockbid.BlockbidExchange;
import org.knowm.xchange.blockbid.service.BlockbidAccountServiceRaw;
import org.knowm.xchange.blockbid.service.BlockbidMarketDataServiceRaw;
import org.knowm.xchange.blockbid.service.BlockbidTradeService;
import org.knowm.xchange.blockbid.service.BlockbidTradeServiceRaw;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.trade.TradeService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BlockbidTradeDemo {
    private static CurrencyPair currencyPair = CurrencyPair.BTC_AUD;
    public static void main(String[] args) throws IOException {
        ExchangeSpecification exSpec = new BlockbidExchange().getDefaultExchangeSpecification();
        exSpec.setApiKey("73ad46e4-a0f9-4b57-95b2-1d591a11b0fb");
        exSpec.setSecretKey("CAzrrR8uzz+GwYnWZbv9fQz0IGig62KufBdNMxXRfCfniIs6sYoi8k5wLYqN4MqB6cJXDUR+AduefSv6aDUpyw==");
        Exchange blockbidExchange = ExchangeFactory.INSTANCE.createExchange(exSpec);

        TradeService tradeService = blockbidExchange.getTradeService();

//        raw((BlockbidTradeServiceRaw) tradeService);
        generic(tradeService);
    }
    private static void raw(BlockbidTradeServiceRaw rawBlockbidTradeService) throws IOException {
//        System.out.println(rawBlockbidMarketDataService.getBlockbidTicker("btcaud"));
//        Thread.sleep(1000);

//        System.out.println(rawBlockbidMarketDataService.getBlockbidTickers());
//        Thread.sleep(1000);
//        System.out.println(rawBlockbidMarketDataService.getBlockbidMarketTrades("btcaud"));
//        System.out.println(rawBlockbidTradeService.makeBlockbidMarketOrder(currencyPair, "buy", new BigDecimal("0.000001")));
//        System.out.println(rawBlockbidTradeService.placeBlockbidLimitOrder(currencyPair, "buy", new BigDecimal("0.000001"), new BigDecimal("2")));
//        Thread.sleep(1000);
//        System.out.println(rawBlockbidTradeService.getBlockbidOpenOrders(currencyPair));
//
    }
    private static void generic(TradeService tradeService) throws IOException {

//        Trades trades = marketService.getTrades(currencyPair);
//        System.out.println("Account Info: " + trades.toString());
        System.out.println(tradeService.getOpenOrders());

//
//        System.out.println(tradeService.placeMarketOrder(new MarketOrder(Order.OrderType.ASK,
//                new BigDecimal("0.001"),
//                currencyPair)));
//        System.out.println(tradeService.placeLimitOrder(new LimitOrder(Order.OrderType.ASK, new BigDecimal("0.001"), currencyPair, "1", new Date(),  new BigDecimal(("0.0001")))));
//                Order.OrderType.ASK,
//                new BigDecimal("0.001"),
//                currencyPair)));
//        System.out.println(dataService.getTrades(currencyPair, 100));
//        Thread.sleep(1000);

    }
}
