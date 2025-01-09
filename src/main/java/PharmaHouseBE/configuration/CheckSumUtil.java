package PharmaHouseBE.configuration;

import org.springframework.util.DigestUtils;

public class CheckSumUtil {
    public static String calculateCheckSum(String token, boolean paymentStatus, String apiKey) {
        return DigestUtils.md5DigestAsHex((token + paymentStatus + apiKey).getBytes());
    }
}
