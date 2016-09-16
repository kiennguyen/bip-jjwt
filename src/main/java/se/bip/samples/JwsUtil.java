package se.bip.samples;

import com.sun.media.sound.InvalidDataException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;

import java.text.ParseException;

/**
 * @author kien.nguyen
 */
public class JwsUtil {
    private HttpsJwks httpsJkws;

    public JwsUtil(String keysUrl){
        this.httpsJkws = new HttpsJwks(keysUrl);
    }

    /**
     * Use JSON Web Signature (JWS) algorithm to encrypt the ID token based on the secret key and S+ public key
     * @param rawIdToken content to be verified the signature and decrypted
     * @return signed content
     */
    public ClaimsSet verifyContent(String rawIdToken, String clientId) {
        try {
            String idToken = verifySignatureWithRsa(rawIdToken, clientId);
            System.out.println(idToken);
            return parseClaimsSet(idToken);
        } catch (Exception e) {

        }

        return null;
    }

    /**
     * Use JSON Web Signature (JWS) algorithm to verify the signature and get the payload based on the public key
     * @param signedIdToken
     * @return payload
     */
    private String verifySignatureWithRsa(String signedIdToken, String clientId) throws InvalidDataException {
        try {
            HttpsJwksVerificationKeyResolver httpsJwksKeyResolver = new HttpsJwksVerificationKeyResolver(httpsJkws);
            JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                    .setRequireExpirationTime() // the JWT must have an expiration time
                    .setRequireSubject() // the JWT must have a subject claim
                    .setExpectedAudience(clientId) // the JWT must have a audience claim with client_id
                    .setVerificationKeyResolver(httpsJwksKeyResolver)
                    .build();

            //  Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(signedIdToken);
            return jwtClaims.toJson();
        } catch (InvalidJwtException e) {
            throw new InvalidDataException("Cannot verify the signature based on the public key");
        }
    }

    public static ClaimsSet parseClaimsSet(String jsonIdToken) {
        try {
            return new IdTokenClaimsSet(new JSONObject(jsonIdToken));
        } catch (ParseException e) {
            return null;
        } catch (JSONException e) {
            return null;
        }
    }
}
