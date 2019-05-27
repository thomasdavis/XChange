package org.knowm.xchange.examples.blockbid;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.blockbid.BlockbidExchange;
import org.knowm.xchange.coinbase.CoinbaseExchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.examples.kraken.KrakenExampleUtils;
import org.knowm.xchange.examples.util.AccountServiceTestUtil;
import org.knowm.xchange.hitbtc.v2.service.HitbtcAccountServiceRaw;
import org.knowm.xchange.kraken.service.KrakenAccountServiceRaw;
import org.knowm.xchange.blockbid.service.BlockbidAccountServiceRaw;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.HistoryParamsFundingType;
import org.knowm.xchange.service.trade.params.TradeHistoryParamCurrencies;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsTimeSpan;

public class BlockbidAccountDataDemo {
    public static void main(String[] args) throws IOException {

        ExchangeSpecification exSpec = new BlockbidExchange().getDefaultExchangeSpecification();
        exSpec.setApiKey("11762e7c-fc4f-4675-8b68-2fdc0573ea3f");
        exSpec.setSecretKey("USaQxCPeZgO/DO6vJIW/g8xKTwq+0eD40jMejeVxNzsgVOv3XKpwlpHuu/iUFBluNyx1msVHe1vgS+IFGO601A==");
        Exchange blockbidExchange = ExchangeFactory.INSTANCE.createExchange(exSpec);

        AccountService accountService = blockbidExchange.getAccountService();

        raw((BlockbidAccountServiceRaw) accountService);
//        generic(accountService);
    }
    private static void raw(BlockbidAccountServiceRaw rawBlockbidAcctService) throws IOException {

//        BlockbidAccountServiceRaw rawBlockbidAcctService =
//            (BlockbidAccountServiceRaw) blockbidExchange.getAccountService();
        System.out.println("Balance Info: " + rawBlockbidAcctService.getBlockbidBalance());
    }
    private static void generic(AccountService accountService) throws IOException {

        AccountInfo accountInfo = accountService.getAccountInfo();
        System.out.println("Account Info: " + accountInfo.toString());

    }
}


//    private static void fundingHistory(AccountService accountService) throws IOException {
//        // Get the funds information
//        TradeHistoryParams params = accountService.createFundingHistoryParams();
//        if (params instanceof TradeHistoryParamsTimeSpan) {
//            final TradeHistoryParamsTimeSpan timeSpanParam = (TradeHistoryParamsTimeSpan) params;
//            timeSpanParam.setStartTime(
//                    new Date(System.currentTimeMillis() - (1 * 12 * 30 * 24 * 60 * 60 * 1000L)));
//        }
//
//        if (params instanceof HistoryParamsFundingType) {
//            ((HistoryParamsFundingType) params).setType(FundingRecord.Type.DEPOSIT);
//        }
//
//        if (params instanceof TradeHistoryParamCurrencies) {
//            final TradeHistoryParamCurrencies currenciesParam = (TradeHistoryParamCurrencies) params;
//            currenciesParam.setCurrencies(new Currency[] {Currency.BTC, Currency.USD});
//        }
//
//        List<FundingRecord> fundingRecords = accountService.getFundingHistory(params);
//        AccountServiceTestUtil.printFundingHistory(fundingRecords);
//    }
//}
