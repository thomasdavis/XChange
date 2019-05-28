package org.knowm.xchange.blockbid.service;

import java.io.IOException;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.blockbid.dto.account.results.BlockbidBalanceResult;

public class BlockbidAccountServiceRaw extends BlockbidBaseService {

  public BlockbidAccountServiceRaw(Exchange exchange) {

    super(exchange);
  }

  public List<BlockbidBalanceResult> getBlockbidBalance() throws IOException {
    List<BlockbidBalanceResult> balanceResult =
        blockbid.balances(apiKey, signatureCreator, exchange.getNonceFactory().createValue());
    return balanceResult;
  }
}
