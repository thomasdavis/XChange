package org.knowm.xchange.blockbid.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.blockbid.BlockbidAuthenticated;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class BlockbidBaseService extends BaseExchangeService implements BaseService {
  protected final String apiKey;
  protected final BlockbidAuthenticated blockbid;
  protected final ParamsDigest signatureCreator;
  protected final ParamsDigest payloadCreator;

  public BlockbidBaseService(Exchange exchange) {

    super(exchange);

    this.blockbid =
        RestProxyFactory.createProxy(
            BlockbidAuthenticated.class, exchange.getExchangeSpecification().getHost());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator =
        BlockbidHmacPostBodyDigest.createInstance(
            exchange.getExchangeSpecification().getSecretKey(),
            exchange.getExchangeSpecification().getApiKey());
    this.payloadCreator = new BlockbidPayloadDigest();
  }
}
