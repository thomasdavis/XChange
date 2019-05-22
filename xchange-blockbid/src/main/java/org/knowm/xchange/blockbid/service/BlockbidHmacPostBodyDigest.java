package org.knowm.xchange.blockbid.service;

import java.math.BigInteger;
import java.util.Base64;
import javax.crypto.Mac;

import org.knowm.xchange.blockbid.BlockbidAuthenticated;
import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestInvocation;
import si.mazi.rescu.SynchronizedValueFactory;

public class BlockbidHmacPostBodyDigest extends BaseParamsDigest {
    protected final String apiKey;
    protected final String secretKeyBase64;
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA384";
    /**
     * Constructor
     *
     * @param secretKeyBase64
     * @throws IllegalArgumentException if key is invalid (cannot be base-64-decoded or the decoded
     *     key is invalid).
     */
    private BlockbidHmacPostBodyDigest(String secretKeyBase64, String apiKey) {

        super(secretKeyBase64, HMAC_SHA_384);
        System.out.println("setting api key stuff");
        this.apiKey = apiKey;
        this.secretKeyBase64 = secretKeyBase64;
        System.out.println(secretKeyBase64);
        System.out.println(apiKey);


    }

    public static BlockbidHmacPostBodyDigest createInstance(String secretKeyBase64, String apiKey) {

        return secretKeyBase64 == null ? null : new BlockbidHmacPostBodyDigest(secretKeyBase64, apiKey);
    }

    @Override
    public String digestParams(RestInvocation restInvocation) {

        String postBody = restInvocation.getRequestBody();
        Mac mac = getMac();
        String btoaApikey = Base64.getEncoder().encodeToString(this.apiKey.getBytes());
        String btoaNonce = Base64.getEncoder().encodeToString("111".getBytes());
        String btoaBody = Base64.getEncoder().encodeToString(postBody.getBytes());
        String fullUnencodedSignature = btoaApikey + btoaNonce + btoaBody;
        byte[] fullUnencodedSignature2 = fullUnencodedSignature.getBytes();
        mac.update(fullUnencodedSignature2);
//        String signature = String.format("%096x", new BigInteger(1, mac.doFinal()));
        String signature = Base64.getEncoder().encodeToString(mac.doFinal());
        System.out.println("what is the signature");
        System.out.println(postBody);
        System.out.println("111");
        System.out.println(btoaNonce);
        System.out.println(btoaApikey);
        System.out.println(btoaBody);

//        MTEx
        System.out.println(this.apiKey);
        System.out.println(signature);

        return signature;
    }
}
//package org.knowm.xchange.quoine.service;
//
//        import com.auth0.jwt.JWT;
//        import com.auth0.jwt.JWTCreator;
//        import com.auth0.jwt.algorithms.Algorithm;
//        import si.mazi.rescu.ParamsDigest;
//        import si.mazi.rescu.RestInvocation;
//        import si.mazi.rescu.SynchronizedValueFactory;
//
//public class QuoineSignatureDigest implements ParamsDigest {
//
//    private final JWTCreator.Builder builder;
//    private final String tokenID;
//    private final byte[] userSecret;
//    private final SynchronizedValueFactory<Long> nonceFactory;
//
//    public QuoineSignatureDigest(
//            String tokenID, String userSecret, SynchronizedValueFactory<Long> nonceFactory) {
//        this.tokenID = tokenID;
//        this.userSecret = userSecret.getBytes();
//        this.nonceFactory = nonceFactory;
//
//        this.builder = JWT.create();
//    }
//
//    @Override
//    public String digestParams(RestInvocation restInvocation) {
//
//        String path = "/" + restInvocation.getMethodPath();
//
//        String queryString = restInvocation.getQueryString();
//        if (queryString != null && queryString.length() > 0)
//            path += "?" + restInvocation.getQueryString();
//
//        return builder
//                .withClaim("path", path)
//                .withClaim("nonce", String.valueOf(nonceFactory.createValue()))
//                .withClaim("token_id", tokenID)
//                .sign(Algorithm.HMAC256(userSecret));
//    }
//}
