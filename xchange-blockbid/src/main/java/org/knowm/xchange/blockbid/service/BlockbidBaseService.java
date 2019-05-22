package org.knowm.xchange.blockbid.service;

import org.knowm.xchange.blockbid.Blockbid;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.FundsExceededException;
import org.knowm.xchange.blockbid.BlockbidAuthenticated;
import org.knowm.xchange.blockbid.dto.BlockbidException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;
public class BlockbidBaseService extends BaseExchangeService implements BaseService {
    protected final String apiKey;
    protected final BlockbidAuthenticated blockbid;
    protected final ParamsDigest signatureCreator;
    protected final ParamsDigest payloadCreator;
    /**
     * Constructor
     *
     * @param exchange
     */
    public BlockbidBaseService(Exchange exchange) {

        super(exchange);


        this.blockbid = RestProxyFactory.createProxy(BlockbidAuthenticated.class, exchange.getExchangeSpecification().getHost());
        this.apiKey = exchange.getExchangeSpecification().getApiKey();
        this.signatureCreator =
                BlockbidHmacPostBodyDigest.createInstance(exchange.getExchangeSpecification().getSecretKey(), exchange.getExchangeSpecification().getApiKey());
        this.payloadCreator = new BlockbidPayloadDigest();
    }
}

//package org.knowm.xchange.gemini.v1.service;
//
//        import org.knowm.xchange.Exchange;
//        import org.knowm.xchange.exceptions.ExchangeException;
//        import org.knowm.xchange.exceptions.FundsExceededException;
//        import org.knowm.xchange.gemini.v1.GeminiAuthenticated;
//        import org.knowm.xchange.gemini.v1.dto.GeminiException;
//        import org.knowm.xchange.service.BaseExchangeService;
//        import org.knowm.xchange.service.BaseService;
//        import si.mazi.rescu.ParamsDigest;
//        import si.mazi.rescu.RestProxyFactory;
//
//public class GeminiBaseService extends BaseExchangeService implements BaseService {
//
//    protected final String apiKey;
//    protected final GeminiAuthenticated gemini;
//    protected final ParamsDigest signatureCreator;
//    protected final ParamsDigest payloadCreator;
//
//    /**
//     * Constructor
//     *
//     * @param exchange
//     */
//    public GeminiBaseService(Exchange exchange) {
//
//        super(exchange);
//
//        this.gemini =
//                RestProxyFactory.createProxy(
//                        GeminiAuthenticated.class,
//                        exchange.getExchangeSpecification().getSslUri(),
//                        getClientConfig());
//        this.apiKey = exchange.getExchangeSpecification().getApiKey();
//        this.signatureCreator =
//                GeminiHmacPostBodyDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
//        this.payloadCreator = new GeminiPayloadDigest();
//    }
//
//    protected ExchangeException handleException(GeminiException e) {
//        if (e.getMessage().contains("due to insufficient funds")
//                || e.getMessage().contains("you do not have enough available"))
//            return new FundsExceededException(e);
//
//        return new ExchangeException(e);
//    }
//}
