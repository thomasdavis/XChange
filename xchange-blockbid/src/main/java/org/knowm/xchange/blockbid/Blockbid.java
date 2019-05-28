package org.knowm.xchange.blockbid;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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

}
