import org.junit.Test;
import se.bip.samples.ClaimsSet;
import se.bip.samples.JwsUtil;

import static org.junit.Assert.assertEquals;

/**
 * @author kien.nguyen
 */
public class JwsUtilTest {
    @Test
    public void testVerifyIdToken() throws Exception {
        JwsUtil jwsUtil = new JwsUtil("http://api.qa.newsplus.se/v1/oauth2/keys");
        ClaimsSet claimsSet = jwsUtil.verifyIdToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IjNidEtucEpRNnRkNW5XZEJZamFJTEUifQ.eyJpZCI6IjdCT3ZmWkFmb0xGWmdvQUhHRVdKNUIiLCJpc3MiOiJodHRwczpcL1wvYWNjb3VudC5zdGFnZS5ib25uaWVyLm5ld3MiLCJzdWIiOiJCSVAzTnVraU83cmF4amoyN1Vpa1l3OFZHIiwiYXVkIjpbIjNNYkFUZFdQTDhzeTZRZEt0YmhxNzEiLCJ1cm46dXNlcnM6b3duZXI6NnZ2OVFzRjZyZmJkQjlub29HeExrRSJdLCJhenAiOiIzTWJBVGRXUEw4c3k2UWRLdGJocTcxIiwiZXhwIjoxNDc0MDQzNjQzMjYyLCJpYXQiOjE0NzQwMTQ4NDMyNjIsImVtYWlsIjoia2llbi5uZ3V5ZW5Abml0ZWNvLnNlIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJLaWVuIE5ndXllbiIsImdpdmVuX25hbWUiOiJLaWVuIiwiZmFtaWx5X25hbWUiOiJOZ3V5ZW4ifQ.nyooml3UNxj8Zibrk9u5kuT-EzwYkD7O9l9QMm4zKXf-oG9tK7BUw7R21ZsXQGc0p_56JV3XmkDkxRVAj1orbNpQWP-vMILImAgDTmAmTEuBO6yKzhLqn7UKcxPFRPvCuKTcG1ctRnqh2COtK4DQAuQgQxv22td0wkTyRvnZsfVfmiYEfw3S8W1ho--09RCgIs_v9_MGRtrvRlKobOgr23m2dPKJoSKE6Nkk7JIAt4STizaDDtE59g1hrSu2M0DgYXcaO4NimCzbGyCkKTFtomcQWAMfDYNCFStZ1bB_KQPW1xFShN7fO_wO5cRrYWER-ra0-c6TsdUPjcGYTiRIUQ", "3MbATdWPL8sy6QdKtbhq71");
        if (claimsSet != null) {
            assertEquals("https://account.stage.bonnier.news", claimsSet.getClaim("iss").toString());
            assertEquals("BIP3NukiO7raxjj27UikYw8VG", claimsSet.getClaim("sub").toString());
            assertEquals("Kien", claimsSet.getClaim("given_name").toString());
            assertEquals("Nguyen", claimsSet.getClaim("family_name").toString());
        } else {
            throw new Exception("Failed to verify the ID token");
        }
    }
}
