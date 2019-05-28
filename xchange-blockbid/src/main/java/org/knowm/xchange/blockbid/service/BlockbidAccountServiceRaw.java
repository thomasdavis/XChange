package org.knowm.xchange.blockbid.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.blockbid.dto.account.BlockbidBalancesRequest;
import org.knowm.xchange.blockbid.dto.account.BlockbidBalancesResponse;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.blockbid.dto.BlockbidException;
import org.knowm.xchange.blockbid.dto.account.results.BlockbidBalanceResult;
import org.knowm.xchange.utils.DateUtils;

public class BlockbidAccountServiceRaw extends BlockbidBaseService {

    /**
     * Constructor
     *
     * @param exchange
     */
    public BlockbidAccountServiceRaw(Exchange exchange) {

        super(exchange);
    }

    /**
     * @return Map of Kraken Assets to account balance
     * @throws IOException
     */
    public List<BlockbidBalanceResult> getBlockbidBalance() throws IOException {
        System.out.println("Balance Info: ");
//        BlockbidBalanceResult balanceResult =  blockbid.balance();
//                        exchange.getExchangeSpecification().getApiKey(),
//                        signatureCreator);
        BlockbidBalancesRequest request = new BlockbidBalancesRequest();
        System.out.println(payloadCreator);
        System.out.println(signatureCreator);
        try {
            List<BlockbidBalanceResult> balanceResult =
                    blockbid.balances(apiKey, signatureCreator, "111");
            System.out.println(balanceResult);
            return balanceResult;

        } catch (BlockbidException e) {
            throw (e);
        }
    }
}