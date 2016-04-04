package com.junjunguo.infosec.password;

/**
 * This file is part of informationSecurity.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 28/03/16.
 */

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.UUID;

/**
 * The type Encrypt password.
 */
public class EncryptPassword {

    private final int iterations    = 2 * 1000;
    private final int desiredKeyLen = 256;

    private final byte[] SALT = {
            (byte) 0xce, (byte) 0x33, (byte) 0x12, (byte) 0x12,
            (byte) 0xae, (byte) 0x13, (byte) 0x10, (byte) 0x10,
    };
    private byte[] iv;
    private String property = "my-security-key";

    private SecretKey getSecretKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec          spec    = new PBEKeySpec(property.toCharArray(), SALT, iterations, desiredKeyLen);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }

    private byte[] getIv() {
        return iv;
    }

    private void setIv(byte[] iv) {
        this.iv = iv;
    }

    /**
     * Encrypt string.
     *
     * @param plaintext the plaintext
     * @return the string
     * @throws GeneralSecurityException     the general security exception
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public String encrypt(String plaintext) throws GeneralSecurityException, UnsupportedEncodingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
        AlgorithmParameters params = cipher.getParameters();
        setIv(params.getParameterSpec(IvParameterSpec.class).getIV());
        return Base64.encodeBase64String(cipher.doFinal(plaintext.getBytes("UTF-8")));
    }

    /**
     * Decrypt string.
     *
     * @param ciphertext the ciphertext
     * @return the string
     * @throws GeneralSecurityException the general security exception
     * @throws IOException              the io exception
     */
    public String decrypt(String ciphertext) throws GeneralSecurityException, IOException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), new IvParameterSpec(getIv()));
        String plaintext = new String(cipher.doFinal(Base64.decodeBase64(ciphertext)), "UTF-8");
        return plaintext;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        EncryptPassword en               = new EncryptPassword();
        String          originalPassword = en.generateToken("guojunjun@junjunguo.com");
        System.out.println("Original  password: " + originalPassword);
        String encryptedPassword = en.encrypt(originalPassword);
        System.out.println("Encrypted password: " + encryptedPassword);
        String decryptedPassword = en.decrypt(encryptedPassword);
        System.out.println("Decrypted password: " + decryptedPassword);
    }

    public String generateToken(String userId) {
        String token = UUID.randomUUID().toString().toUpperCase() +
                       "$" + userId +
                       "$" + System.currentTimeMillis();
        return token;
    }
}
