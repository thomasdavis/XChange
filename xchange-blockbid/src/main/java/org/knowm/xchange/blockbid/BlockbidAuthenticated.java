package org.knowm.xchange.blockbid;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.*;
import org.knowm.xchange.blockbid.dto.BlockbidException;
import org.knowm.xchange.blockbid.dto.account.BlockbidBalancesRequest;
import org.knowm.xchange.blockbid.dto.account.BlockbidBalancesResponse;

import org.knowm.xchange.blockbid.dto.account.results.BlockbidBalanceResult;
import javax.ws.rs.core.MediaType;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface BlockbidAuthenticated extends Blockbid {


    @GET
    @Path("balances")
    List<BlockbidBalanceResult> balances(
            @HeaderParam("x-blockbid-api-key") String apiKey,
            @HeaderParam("x-blockbid-signature") ParamsDigest signatureCreator,
            @HeaderParam("x-blockbid-nonce") String nonce)
            throws IOException;
}


//    BlockbidBalanceResult balances(
//            @HeaderParam("x-blockbid-api-key") String apiKey)
//            throws IOException, BlockbidException;

//    @GET
//    @Path("balances")
//    BlockbidBalanceResult balance()
//            throws IOException;