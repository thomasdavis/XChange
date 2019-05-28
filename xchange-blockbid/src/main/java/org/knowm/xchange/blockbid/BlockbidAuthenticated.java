package org.knowm.xchange.blockbid;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.*;

import org.knowm.xchange.blockbid.dto.account.results.BlockbidBalanceResult;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.blockbid.dto.marketdata.results.BlockbidTradeResult;
import org.knowm.xchange.blockbid.dto.trade.BlockbidOrdersRequest;
import org.knowm.xchange.blockbid.dto.trade.BlockbidLimitOrdersRequest;
import org.knowm.xchange.blockbid.dto.trade.results.BlockbidLimitOrderResult;
import org.knowm.xchange.blockbid.dto.trade.results.BlockbidOpenOrderResult;
import org.knowm.xchange.blockbid.dto.trade.results.BlockbidOrderResult;
import si.mazi.rescu.ParamsDigest;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BlockbidAuthenticated extends Blockbid {


    @GET
    @Path("balances")
    List<BlockbidBalanceResult> balances(
            @HeaderParam("x-blockbid-api-key") String apiKey,
            @HeaderParam("x-blockbid-signature") ParamsDigest signatureCreator,
            @HeaderParam("x-blockbid-nonce") String nonce)
            throws IOException;

    @POST
    @Path("orders")
    @Consumes(MediaType.APPLICATION_JSON)
    List<BlockbidOrderResult> makeMarketOrder(
            @HeaderParam("x-blockbid-api-key") String apiKey,
            @HeaderParam("x-blockbid-signature") ParamsDigest signatureCreator,
            @HeaderParam("x-blockbid-nonce") String nonce,
            BlockbidOrdersRequest generateMultiOrders)
            throws IOException;

    @POST
    @Path("orders")
    @Consumes(MediaType.APPLICATION_JSON)
    List<BlockbidLimitOrderResult> placeLimitOrder(
            @HeaderParam("x-blockbid-api-key") String apiKey,
            @HeaderParam("x-blockbid-signature") ParamsDigest signatureCreator,
            @HeaderParam("x-blockbid-nonce") String nonce,
            BlockbidLimitOrdersRequest generateMultiOrders)
            throws IOException;

    @GET
    @Path("orders")
    @Consumes(MediaType.APPLICATION_JSON)
    List<BlockbidOpenOrderResult> getOpenOrders(
            @HeaderParam("x-blockbid-api-key") String apiKey,
            @HeaderParam("x-blockbid-signature") ParamsDigest signatureCreator,
            @HeaderParam("x-blockbid-nonce") String nonce,
            @PathParam("currencyPair") String market)
            throws IOException;
    @DELETE
    @Path("orders/{uuid}")
    @Consumes(MediaType.APPLICATION_JSON)
    BlockbidLimitOrderResult cancelOrder(
            @HeaderParam("x-blockbid-api-key") String apiKey,
            @HeaderParam("x-blockbid-signature") ParamsDigest signatureCreator,
            @HeaderParam("x-blockbid-nonce") String nonce,
            @PathParam("uuid") String uuid)
            throws IOException;
    @GET
    @Path("trades/my?market={currencyPair}")
    List<BlockbidTradeResult> getUserTrades(
            @HeaderParam("x-blockbid-api-key") String apiKey,
            @HeaderParam("x-blockbid-signature") ParamsDigest signatureCreator,
            @HeaderParam("x-blockbid-nonce") String nonce,
            @PathParam("currencyPair") String currency) throws IOException;

}
//  const data = {
//          market: market,
//          orders: [
//          {
//          price: price,
//          side: side,
//          volume: amount,
//          orderType: "limit"
//          }
//          ]
//          };
//        price: price,
//                side: side,
//                volume: amount,
//                orderType: "limit"

//    @POST
//    @Path("orderbook?market={currencyPair}")
//    BlockbidOrderBookResult makeMarketOrder(@PathParam("currencyPair") String currency) throws IOException;


//    BlockbidBalanceResult balances(
//            @HeaderParam("x-blockbid-api-key") String apiKey)
//            throws IOException, BlockbidException;

//    @GET
//    @Path("balances")
//    BlockbidBalanceResult balance()
//            throws IOException;