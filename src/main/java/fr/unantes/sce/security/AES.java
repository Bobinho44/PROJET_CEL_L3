package fr.unantes.sce.security;

import javax.annotation.Nonnull;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

/**
 * AES is a class of encryption and decipherment
 */
public class AES {

    /**
     * Encryption key
     */
    private static final String SECRET_KEY = "CodeSuperSecretTravelAgency";

    /**
     * AES key size
     */
    private static final int AES_KEY_SIZE = 16;

    /**
     * GC% Tag length
     */
    private static final int GCM_TAG_LENGTH = 128;

    /**
     * Random GCM nonce length
     */
    private static final int GCM_NONCE_LENGTH = 12;

    /**
     * Creates a new AES encryptor
     */
    private AES() {
    }

    /**
     * Gets the AES key
     *
     * @return the AES key
     */
    @Nonnull
    private static SecretKeySpec getKey() throws NoSuchAlgorithmException {
        byte[] key = AES.SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, GCM_TAG_LENGTH);
        return new SecretKeySpec(key, "AES");
    }

    /**
     * Gets the AES spec
     *
     * @return the AES spec
     */
    @Nonnull
    public static GCMParameterSpec getSpec() throws NoSuchAlgorithmException {
        byte[] nonce = AES.SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        nonce = sha.digest(nonce);
        nonce = Arrays.copyOf(nonce, GCM_NONCE_LENGTH);
        return new GCMParameterSpec(AES_KEY_SIZE, nonce);
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
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, getKey(), getSpec());
            encrypted = Optional.of(Base64.getEncoder().encodeToString(cipher.doFinal(toEncrypt.getBytes())));
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
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, getKey(), getSpec());
            decrypted = Optional.of(new String(cipher.doFinal(Base64.getDecoder().decode(toDecrypt))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decrypted;
    }

}