package fr.unantes.sce.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES is a class of encryption and decipherment
 */
public class AES {

    /**
     * Encryption key
     */
    private final static String SECRET_KEY = "CodeSuperSecretTravelAgency";

    /**
     * Gets the AES key
     *
     * @return the AES key
     */
    @Nonnull
    private static SecretKeySpec getKey() {
        byte[] key = AES.SECRET_KEY.getBytes(StandardCharsets.UTF_8);

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new SecretKeySpec(key, "AES");
    }

    /**
     * Encrypts the string
     *
     * @param toEncrypt the string to be encrypted
     * @return the encrypted string
     */
    @Nonnull
    public static Optional<String> encrypt(@Nonnull String toEncrypt) {
        Optional<String> encrypted = Optional.empty();

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getKey());
            encrypted = Optional.of(Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encrypted;
    }

    /**
     * Decrypts the string
     *
     * @param toDecrypt the string to be decrypted
     * @return the decrypted string
     */
    @Nonnull
    public static Optional<String> decrypt(@Nonnull String toDecrypt) {
        Optional<String> decrypted = Optional.empty();

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, getKey());
            decrypted = Optional.of(new String(cipher.doFinal(Base64.getDecoder().decode(toDecrypt))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decrypted;
    }

}