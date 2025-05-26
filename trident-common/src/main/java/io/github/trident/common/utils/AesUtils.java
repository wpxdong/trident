package io.github.trident.common.utils;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import io.github.trident.common.exception.TridentException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @projectName: trident
 * @package: io.github.trident.common.utils
 * @className: AesUtils
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/2 13:28
 * @version: 1.0
 */
public class AesUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AesUtils.class);

    /**
     * AES/CBC/Pkcs7Padding encrypt data.
     *
     * @param secretKeyStr secretKeyStr
     * @param ivStr        ivStr
     * @param data         data
     * @return cbcEncrypt str.
     */
    public static String cbcEncrypt(final String secretKeyStr, final String ivStr, final String data) {
        Security.addProvider(new BouncyCastleProvider());
        String encryptStr;
        byte[] secretKeyBytes = secretKeyStr.getBytes(StandardCharsets.UTF_8);
        byte[] ivBytes = ivStr.getBytes(StandardCharsets.UTF_8);
        try {
            SecretKey secretKey = new SecretKeySpec(secretKeyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/Pkcs7Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            encryptStr = Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception ex) {
            LOGGER.error("aes encrypt fail. cause:{}", ex.getMessage());
            throw new TridentException(ex);
        }
        return encryptStr;
    }

    /**
     * AES/CBC/Pkcs7Padding decrypt data.
     *
     * @param secretKeyStr secretKeyStr
     * @param ivStr        ivStr
     * @param data         data
     * @return cbcDecrypt str.
     */
    public static String cbcDecrypt(final String secretKeyStr, final String ivStr, final String data) {
        Security.addProvider(new BouncyCastleProvider());
        String decryptStr;
        byte[] secretKeyBytes = secretKeyStr.getBytes(StandardCharsets.UTF_8);
        byte[] ivBytes = ivStr.getBytes(StandardCharsets.UTF_8);
        try {
            SecretKey secretKey = new SecretKeySpec(secretKeyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/Pkcs7Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(data));
            decryptStr = new String(decryptedBytes);
        } catch (Exception e) {
            LOGGER.error("aes decrypt fail. cause:{}", e.getMessage());
            throw new TridentException(e);
        }
        return decryptStr;
    }
}
