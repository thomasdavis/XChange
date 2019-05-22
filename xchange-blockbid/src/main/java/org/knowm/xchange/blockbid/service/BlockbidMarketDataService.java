package org.knowm.xchange.blockbid.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class BlockbidMarketDataService extends BlockbidMarketDataServiceRaw implements MarketDataService {
    public BlockbidMarketDataService(Exchange exchange) {
        super(exchange);
    }

}
