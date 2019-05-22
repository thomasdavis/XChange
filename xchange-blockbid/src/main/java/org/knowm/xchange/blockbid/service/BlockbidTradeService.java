package org.knowm.xchange.blockbid.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.trade.TradeService;
public class BlockbidTradeService extends BlockbidTradeServiceRaw implements TradeService {
    public BlockbidTradeService(Exchange exchange) {
        super(exchange);
    }
}
