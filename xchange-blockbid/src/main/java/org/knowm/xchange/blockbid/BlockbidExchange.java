package org.knowm.xchange.blockbid;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.blockbid.service.BlockbidAccountService;
import org.knowm.xchange.blockbid.service.BlockbidMarketDataService;
import org.knowm.xchange.blockbid.service.BlockbidTradeService;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import si.mazi.rescu.SynchronizedValueFactory;

public class BlockbidExchange extends BaseExchange implements Exchange {

  private SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {

    return nonceFactory;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {
    ExchangeSpecification exchangeSpecification =
        new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://api.blockbid.io");
    exchangeSpecification.setHost("https://api.blockbid.io");
    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("Blockbid");
    exchangeSpecification.setExchangeDescription(
        "Blockbid is an Australian crypto exchange");
    return exchangeSpecification;
  }

  @Override
  protected void initServices() {
    this.accountService = new BlockbidAccountService(this);
    this.tradeService = new BlockbidTradeService(this);
    this.marketDataService = new BlockbidMarketDataService(this);
  }
}
