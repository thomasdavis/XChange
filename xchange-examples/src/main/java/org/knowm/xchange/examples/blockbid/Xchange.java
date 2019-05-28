package org.knowm.xchange.examples.blockbid;


import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.blockbid.BlockbidExchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.trade.params.DefaultTradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.trade.params.DefaultTradeHistoryParamsTimeSpan;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.util.logging;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class Xchange {
    private static Logger logger = Logger.getLogger("xchange");

    public static void main(String[] args) throws Exception {
        System.out.println("wwwwwwwww Info: " );

        logger.info("Order zzzzzzzzz: ");
        Exchange ex = createExchange();
        CurrencyPair pair = new CurrencyPair("BCH", "AUD");
        System.out.println("Order xxxxxxxxxxxxx: ");
        showPublicData(ex, pair);
        showBalance(ex);
        makeLimit(ex,pair);
        makeMarket(ex, pair);
        showOpenOrders(ex);
        cancelOpenOrders(ex);
        showHistory(ex, pair);
//        showHistoryTimeSpan(ex);

    }

    private static void showPublicData(Exchange ex, CurrencyPair pair) {
        logger.info("Order ttttttttt: ");

        MarketDataService marketData = ex.getMarketDataService();
        try {
            logger.info("Order book: " + marketData.getOrderBook(pair));
        } catch (Exception e){
            logger.log(Level.SEVERE, "showPublicData.getOrderBook:",e);
        }
        try {
            logger.info("Trades: " + marketData.getTrades(pair));
        } catch (Exception e) {
            logger.log(Level.ALL,"showPublicData.getTrades:",e);
        }
    }

    private static Exchange  createExchange() {
        return ExchangeFactory.INSTANCE.createExchange(BlockbidExchange.class, "11762e7c-fc4f-4675-8b68-2fdc0573ea3f","USaQxCPeZgO/DO6vJIW/g8xKTwq+0eD40jMejeVxNzsgVOv3XKpwlpHuu/iUFBluNyx1msVHe1vgS+IFGO601A==");
    }


    private static void showBalance(Exchange ex) {
        logger.info("Balances:");
        try {
            ex.getAccountService().getAccountInfo().getWallets().forEach(
                    (k, v) -> v.getBalances().forEach(
                            (cur, bal) -> {
                                if (bal.getAvailable().compareTo(BigDecimal.ZERO) == 1) {
                                    logger.info(cur.toString() + " : " + bal.getTotal().toPlainString());
                                }
                            }));
        } catch(Exception e){
            logger.log(Level.ALL,"Balances:",e);
        }
    }

    private static void showOpenOrders(Exchange ex) throws IOException {
        System.out.println("Order 12121: ");

        try {
            OpenOrders oo = ex.getTradeService().getOpenOrders();
            logger.info("Open trades : " + oo.getOpenOrders().size());
            oo.getOpenOrders().forEach(o -> logger.info(o.toString()));
        } catch (Exception e){
            logger.log(Level.SEVERE,"showOpenOrders:",e);
        }
    }

    private static void cancelOpenOrders(Exchange ex) {
        try {
            OpenOrders oo = ex.getTradeService().getOpenOrders();
            oo.getOpenOrders().forEach(o ->
            {
                try {
                    ex.getTradeService().cancelOrder(o.getId());
                } catch (IOException e) {
                    logger.log(Level.SEVERE,"cancel "+o.getId(),e);
                }
            });
            logger.info("Cancelled: " + oo);
        } catch (Exception e){
            logger.log(Level.SEVERE,"cancelOpenOrders",e);
        }

    }

    private static void showHistory(Exchange ex, CurrencyPair pair){
        try {
            UserTrades tr = ex.getTradeService().getTradeHistory(new DefaultTradeHistoryParamCurrencyPair(pair));
            logger.info("History trades : " + tr.getUserTrades().size());
            tr.getUserTrades().forEach( t -> logger.info(t.toString()));
        } catch (Exception e){
            logger.log(Level.SEVERE,"showHistory",e);
        }
    }

    private static void makeLimit(Exchange ex, CurrencyPair pair){
        try {
            String result = ex.getTradeService().placeLimitOrder(new LimitOrder(
                    Order.OrderType.BID,
                    new BigDecimal("0.1"),
                    pair,
                    "",
                    new Date(),
                    new BigDecimal("0.01")
            ));
            logger.info("Limit order number: " + result);
        } catch (Exception e){
            logger.log(Level.SEVERE,"makeLimit",e);
        }
    }

    private static void makeMarket(Exchange ex, CurrencyPair pair){
        System.out.println("Order xssssss: ");

        try {
            String result = ex.getTradeService().placeMarketOrder(
                    new MarketOrder(Order.OrderType.ASK,
                            new BigDecimal("0.001"),
                            pair));
            logger.info("Market order number: " + result);
        } catch (Exception e) {
            System.out.println("Order error: ");

            logger.log(Level.SEVERE,"makeMarket", e);
        }
    }

    private static void showHistoryTimeSpan(Exchange ex) {
        try {
            UserTrades tr = ex.getTradeService().getTradeHistory( new DefaultTradeHistoryParamsTimeSpan(
                    new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24), new Date()));
            logger.info("History trades : " + tr.getUserTrades().size());
            tr.getUserTrades().forEach( t -> logger.info(t.toString()));
        } catch (Exception e){
            logger.log(Level.SEVERE,"showHistory",e);
        }
    }
}
