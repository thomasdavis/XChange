package org.knowm.xchange.blockbid;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;

import org.knowm.xchange.blockbid.service.BlockbidAccountService;
import org.knowm.xchange.blockbid.service.BlockbidMarketDataService;
import org.knowm.xchange.blockbid.service.BlockbidTradeService;
import si.mazi.rescu.SynchronizedValueFactory;

public class BlockbidExchange extends BaseExchange implements Exchange {

	@Override
	public SynchronizedValueFactory<Long> getNonceFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExchangeSpecification getDefaultExchangeSpecification() {		
		ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
		exchangeSpecification.setSslUri("https://api.blockbid.io");
		exchangeSpecification.setHost("https://api.blockbid.io");
		exchangeSpecification.setPort(80);
		exchangeSpecification.setExchangeName("Blockbid");
		exchangeSpecification.setExchangeDescription("Blockbid provide an API for accessing the Bitcoin Network.");
		return exchangeSpecification;
	}

	@Override
	protected void initServices() {
		this.accountService = new BlockbidAccountService(this);
		this.tradeService = new BlockbidTradeService(this);
		this.marketDataService = new BlockbidMarketDataService(this);
	}
}
