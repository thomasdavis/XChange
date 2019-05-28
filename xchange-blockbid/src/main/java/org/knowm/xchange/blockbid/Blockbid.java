package org.knowm.xchange.blockbid;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.blockbid.dto.Market;
import org.knowm.xchange.blockbid.dto.Ohlc;
import org.knowm.xchange.blockbid.dto.Currency;
import org.knowm.xchange.blockbid.dto.Trade;


import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidTickerResult;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidTradeResult;
import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidOrderBookResult;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface Blockbid {

	@GET
	@Path("tickers")
	List<BlockbidTickerResult> getTickers() throws IOException;

	@GET
	@Path("tickers/{currencyPair}")
	List<BlockbidTickerResult> getTicker(@PathParam("currencyPair") String currency) throws IOException;


	@GET
	@Path("trades?market={currencyPair}")
	List<BlockbidTradeResult> getMarketTrades(@PathParam("currencyPair") String currency) throws IOException;

	@GET
	@Path("orderbook?market={currencyPair}")
	BlockbidOrderBookResult getOrderBook(@PathParam("currencyPair") String currency) throws IOException;

	@GET
	@Path("ohlc")
	Ohlc []getOhlc(@QueryParam("market") String market) throws IOException;
	
	@GET
	@Path("currencies")
	Currency []getCurrencies() throws IOException;	
	
	@GET
	@Path("trades")
	Trade []getTrades(@QueryParam("market") String market) throws IOException;
}
