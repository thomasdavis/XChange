package org.knowm.xchange.blockbid.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.blockbid.dto.account.results.BlockbidBalanceResult;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidTradeResult;
import org.knowm.xchange.blockbid.dto.trade.BlockbidLimitOrderRequest;
import org.knowm.xchange.blockbid.dto.trade.BlockbidLimitOrdersRequest;
import org.knowm.xchange.blockbid.dto.trade.BlockbidOrderRequest;
import org.knowm.xchange.blockbid.dto.trade.BlockbidOrdersRequest;
import org.knowm.xchange.blockbid.dto.trade.results.BlockbidLimitOrderResult;
import org.knowm.xchange.blockbid.dto.trade.results.BlockbidOpenOrderResult;
import org.knowm.xchange.blockbid.dto.trade.results.BlockbidOrderResult;
import org.knowm.xchange.currency.CurrencyPair;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockbidTradeServiceRaw extends BlockbidBaseService {
    final static Logger logger =
            Logger.getLogger("rawsss");
    public BlockbidTradeServiceRaw(Exchange exchange) {

        super(exchange);
    }
    public BlockbidOrderResult makeBlockbidMarketOrder(CurrencyPair currencyPair, String side, BigDecimal originalAmount) throws IOException {
        String market = currencyPair.toString().replace("/", "").toLowerCase();
//        List<BlockbidBalanceResult> balanceResult =
//                blockbid.balances(apiKey, signatureCreator, "111");
        BlockbidOrderRequest[] blockbidOrders = new BlockbidOrderRequest[1];
        blockbidOrders[0] = new BlockbidOrderRequest(originalAmount, "btcaud", "buy");
        BlockbidOrdersRequest request =
                new BlockbidOrdersRequest(blockbidOrders);
        System.out.println("AOWEISOIDasdasdasdasdasd");
        System.out.println("AOWEISOIDasdasdasdasdasd");
        System.out.println("AOWEISOIDasdasdasdasdasd");
        System.out.println(request.getOrders());

//        BlockbidOrdersRequest newOrder = new BlockbidOrdersRequest(originalAmount, "market", "buy");

//        orders.add(newOrder);
//          price: price,
//          side: side,
//          volume: amount,
//          orderType: "limit"
//        "{"orders":[{"volume":0.0001,"side":"sell","orderType":"market"}],"market":"ethaud"}"
//         {"orders":[{"volume":0.0001,"side":"sell","orderType":"market"}],"market":"ethaud"}
        try {
            List<BlockbidOrderResult> order = blockbid.makeMarketOrder(apiKey, signatureCreator, "111", request);

            System.out.println("2222");
            System.out.println(order);
            return order.get(0);

        } catch (Exception e) {

            logger.log(Level.SEVERE,"makeMarket", e);
            return null;
        }

    }
    public List<BlockbidOpenOrderResult> getBlockbidOpenOrders(CurrencyPair currencyPair) throws IOException {
        String market = currencyPair.toString().replace("/", "").toLowerCase();
        System.out.println("Account Info: " + market);

        List<BlockbidOpenOrderResult> openOrdersResult =
                blockbid.getOpenOrders(apiKey, signatureCreator, "111", market);
        return openOrdersResult;
    }
    public List<BlockbidTradeResult> getBlockbidMarketTrades(CurrencyPair currencyPair) throws IOException {
        String market = currencyPair.toString().replace("/", "").toLowerCase();
        System.out.println("Account Info: " + market);

        List<BlockbidTradeResult> marketTradesResult =
                blockbid.getMarketTrades(market);
        return marketTradesResult;
    }
    public BlockbidLimitOrderResult placeBlockbidLimitOrder(CurrencyPair currencyPair, String side, BigDecimal originalAmount, BigDecimal price) throws IOException {
        String market = currencyPair.toString().replace("/", "").toLowerCase();
        BlockbidLimitOrderRequest[] blockbidOrders = new BlockbidLimitOrderRequest[1];
        blockbidOrders[0] = new BlockbidLimitOrderRequest(originalAmount, "limit", "buy", price);
        BlockbidLimitOrdersRequest request =
                new BlockbidLimitOrdersRequest(blockbidOrders);

        try {
            List<BlockbidLimitOrderResult> order = blockbid.placeLimitOrder(apiKey, signatureCreator, "111", request);

            System.out.println("2222");
            System.out.println(order);
            return order.get(0);

        } catch (Exception e) {

            logger.log(Level.SEVERE,"makeMarket", e);
            return null;
        }

    }
    public boolean cancelBlockbidLimitOrder(String uuid) throws IOException {

        BlockbidLimitOrderResult cancelledOrder = blockbid.cancelOrder(apiKey, signatureCreator, "111", uuid);
        return true;
    }
    public List<BlockbidTradeResult> getBlockbidUserTrades(CurrencyPair currencyPair) throws IOException {
        String market = currencyPair.toString().replace("/", "").toLowerCase();
        System.out.println("Account Info: " + market);

        List<BlockbidTradeResult> marketTradesResult =
                blockbid.getUserTrades(apiKey, signatureCreator, "111",market);
        return marketTradesResult;
    }
}
