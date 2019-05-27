package org.knowm.xchange.blockbid.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidTradeResult;
import org.knowm.xchange.blockbid.dto.trade.results.BlockbidLimitOrderResult;
import org.knowm.xchange.blockbid.dto.trade.results.BlockbidOpenOrderResult;
import org.knowm.xchange.blockbid.dto.trade.results.BlockbidOrderResult;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.service.trade.TradeService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlockbidTradeService extends BlockbidTradeServiceRaw implements TradeService {
    public BlockbidTradeService(Exchange exchange) {
        super(exchange);
    }
    public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
        String side = marketOrder.getType() == Order.OrderType.BID ? "buy" : "sell";
        BlockbidOrderResult blockbidMarketOrder = makeBlockbidMarketOrder(marketOrder.getCurrencyPair(), side, marketOrder.getOriginalAmount());
        String orderId = blockbidMarketOrder.getId();
        return orderId;
    }
    public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
        String side = limitOrder.getType() == Order.OrderType.BID ? "buy" : "sell";
        BlockbidLimitOrderResult blockbidLimitOrder = placeBlockbidLimitOrder(limitOrder.getCurrencyPair(), side, limitOrder.getOriginalAmount(), new BigDecimal("0.0001"));
        String orderId = blockbidLimitOrder.getId();
        return orderId;
    }
    @Override
    public OpenOrders getOpenOrders() throws IOException {
        List<BlockbidOpenOrderResult> blockbidOpenOrder = getBlockbidOpenOrders(CurrencyPair.BTC_AUD);
        List<LimitOrder> openOrders = new ArrayList<>(blockbidOpenOrder.size());
        for (BlockbidOpenOrderResult bbOpenOrder : blockbidOpenOrder) {
            openOrders.add(new LimitOrder(Order.OrderType.BID, new BigDecimal("0.11"), CurrencyPair.BTC_AUD, "1", new Date(), new BigDecimal("0.0001")));
        }
        return new OpenOrders(openOrders);
    }

}
