package org.knowm.xchange.blockbid.service;

import org.knowm.xchange.Exchange;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockbidTradeServiceRaw extends BlockbidBaseService {
    final static Logger logger = Logger.getLogger("BlockbidBaseService");

    public BlockbidTradeServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public BlockbidOrderResult makeBlockbidMarketOrder(CurrencyPair currencyPair, String side, BigDecimal originalAmount) throws IOException {
        String market = currencyPair.toString().replace("/", "").toLowerCase();
        BlockbidOrderRequest[] blockbidOrders = new BlockbidOrderRequest[1];
        blockbidOrders[0] = new BlockbidOrderRequest(originalAmount, side);
        BlockbidOrdersRequest request =
                new BlockbidOrdersRequest(market, blockbidOrders);
        try {
            List<BlockbidOrderResult> order = blockbid.makeMarketOrder(apiKey, signatureCreator, exchange.getNonceFactory().createValue(), request);
            return order.get(0);

        } catch (Exception e) {
            logger.log(Level.SEVERE,"makeBlockbidMarketOrder", e);
            return null;
        }

    }
    public List<BlockbidOpenOrderResult> getBlockbidOpenOrders() throws IOException {
        List<BlockbidOpenOrderResult> openOrdersResult =
                blockbid.getOpenOrders(apiKey, signatureCreator, exchange.getNonceFactory().createValue());
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
        blockbidOrders[0] = new BlockbidLimitOrderRequest(originalAmount, side, price);
        BlockbidLimitOrdersRequest request = new BlockbidLimitOrdersRequest(market, blockbidOrders);
        try {
            List<BlockbidLimitOrderResult> order = blockbid.placeLimitOrder(apiKey, signatureCreator, exchange.getNonceFactory().createValue(), request);
            return order.get(0);

        } catch (Exception e) {
            logger.log(Level.SEVERE,"makeMarket", e);
            return null;
        }

    }
    public boolean cancelBlockbidLimitOrder(String uuid) throws IOException {
        BlockbidLimitOrderResult cancelledOrder = blockbid.cancelOrder(apiKey, signatureCreator, exchange.getNonceFactory().createValue(), uuid);
        return true;
    }

    public List<BlockbidTradeResult> getBlockbidUserTrades(CurrencyPair currencyPair) throws IOException {
        String market = currencyPair.toString().replace("/", "").toLowerCase();
        List<BlockbidTradeResult> marketTradesResult =
                blockbid.getUserTrades(apiKey, signatureCreator, exchange.getNonceFactory().createValue(),market);
        return marketTradesResult;
    }
}
