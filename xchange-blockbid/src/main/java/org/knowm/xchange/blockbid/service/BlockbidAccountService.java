package org.knowm.xchange.blockbid.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.blockbid.dto.account.results.BlockbidBalanceResult;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.service.account.AccountService;

public class BlockbidAccountService extends BlockbidAccountServiceRaw implements AccountService {

  public BlockbidAccountService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {
    List<BlockbidBalanceResult> balances = getBlockbidBalance();
    List<Balance> wallets = new ArrayList<>(balances.size());
    for (BlockbidBalanceResult blc : balances) {
      final Currency currency = Currency.getInstance(blc.getCurrency());
      wallets.add(new Balance(currency, blc.getTotal(), blc.getAvailable()));
    }
    Wallet blockbidWallets = new Wallet(wallets);
    return new AccountInfo(blockbidWallets);
  }
}
