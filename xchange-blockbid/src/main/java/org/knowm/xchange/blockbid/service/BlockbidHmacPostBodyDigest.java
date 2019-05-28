package org.knowm.xchange.blockbid.service;

import java.util.Base64;
import javax.crypto.Mac;
import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

public class BlockbidHmacPostBodyDigest extends BaseParamsDigest {
  protected final String apiKey;
  protected final String secretKeyBase64;
  private static final String HMAC_SHA1_ALGORITHM = "HmacSHA384";

  private BlockbidHmacPostBodyDigest(String secretKeyBase64, String apiKey) {
    super(secretKeyBase64, HMAC_SHA_384);
    this.apiKey = apiKey;
    this.secretKeyBase64 = secretKeyBase64;
  }

  public static BlockbidHmacPostBodyDigest createInstance(String secretKeyBase64, String apiKey) {
    return secretKeyBase64 == null ? null : new BlockbidHmacPostBodyDigest(secretKeyBase64, apiKey);
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {

    String postBody = restInvocation.getRequestBody();
    Mac mac = getMac();
    String btoaApikey = Base64.getEncoder().encodeToString(this.apiKey.getBytes());
    String btoaNonce =
        Base64.getEncoder()
            .encodeToString(
                restInvocation.getHttpHeadersFromParams().get("x-blockbid-nonce").getBytes());
    String btoaBody = "";
    if (postBody != null) {
      btoaBody = Base64.getEncoder().encodeToString(postBody.getBytes());
    }
    String fullUnencodedSignature = btoaApikey + btoaNonce + btoaBody;
    byte[] fullUnencodedSignature2 = fullUnencodedSignature.getBytes();
    mac.update(fullUnencodedSignature2);
    String signature = Base64.getEncoder().encodeToString(mac.doFinal());
    return signature;
  }
}
