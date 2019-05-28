package org.knowm.xchange.examples.blockbid;

import java.io.IOException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.blockbid.BlockbidExchange;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.blockbid.service.BlockbidAccountServiceRaw;
import org.knowm.xchange.service.account.AccountService;

public class BlockbidAccountDataDemo {
    public static void main(String[] args) throws IOException {
        ExchangeSpecification exSpec = new BlockbidExchange().getDefaultExchangeSpecification();
        exSpec.setApiKey("11762e7c-fc4f-4675-8b68-2fdc0573ea3f");
        exSpec.setSecretKey("USaQxCPeZgO/DO6vJIW/g8xKTwq+0eD40jMejeVxNzsgVOv3XKpwlpHuu/iUFBluNyx1msVHe1vgS+IFGO601A==");
        Exchange blockbidExchange = ExchangeFactory.INSTANCE.createExchange(exSpec);
        AccountService accountService = blockbidExchange.getAccountService();
        raw((BlockbidAccountServiceRaw) accountService);
        generic(accountService);
    }
    private static void raw(BlockbidAccountServiceRaw rawBlockbidAcctService) throws IOException {
        System.out.println("Balance Info: " + rawBlockbidAcctService.getBlockbidBalance());
    }
    private static void generic(AccountService accountService) throws IOException {
        AccountInfo accountInfo = accountService.getAccountInfo();
        System.out.println("Account Info: " + accountInfo.toString());
    }
}
