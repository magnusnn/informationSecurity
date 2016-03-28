package com.junjunguo.infosec.password;


import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;

/**
 * This file is part of informationSecurity.
 * <p/>
 * Created by <a href="http://junjunguo.com">GuoJunjun</a> on 27/03/16.
 */
public class PasswordHash {
    // The higher the number of iterations the more
    // expensive computing the hash is for us and also for an attacker.
    private final int iterations    = 2 * 1000;
    private final int saltLen       = 32;
    private final int desiredKeyLen = 256;

    /**
     * Computes a salted PBKDF2 hash of given plaintext password suitable for storing in a database. Empty passwords are
     * not supported.
     *
     * @param password the password
     * @return the salted hash password
     * @throws Exception the exception
     */
    public String getSaltedHashPassword(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        return Base64.encodeBase64String(salt) + "$" + hashPassword(password, salt);
    }

    /**
     * Checks whether given plaintext password corresponds to a stored salted hash of the password.
     *
     * @param password the password
     * @param stored   the stored
     * @return the boolean
     * @throws Exception the exception
     */
    public boolean check(String password, String stored) throws Exception {
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException(
                    "The stored password have the form 'salt$hash'");
        }
        String hashOfInput = hashPassword(password, Base64.decodeBase64(saltAndPass[0]));
        return hashOfInput.equals(saltAndPass[1]);
    }

    /**
     * Gets salted hash password for storing in database.
     *
     * @param password the string password
     * @param salt     the salt
     * @return the salted hash string password
     * @throws Exception the exception
     */
    public String hashPassword(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0) {
            throw new IllegalArgumentException("Empty passwords are not supported.");
        }
        return Base64.encodeBase64String(hashPassword(password.toCharArray(), salt, iterations, desiredKeyLen));
    }

    /**
     * https://www.owasp.org/index.php/Hashing_Java
     *
     * @param password   the password
     * @param salt       the salt
     * @param iterations the iterations
     * @param keyLength  the key length
     * @return byte [ ]
     * @throws Exception the exceptionR
     */
    public byte[] hashPassword(final char[] password, final byte[] salt, final int iterations,
            final int keyLength) throws Exception {
        // PBKDF2WithHmacSHA512 not supported in java version 1.7
        // PBKDF2WithHmacSHA1   for java 1.7
        SecretKeyFactory skf  = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec       spec = new PBEKeySpec(password, salt, iterations, keyLength);
        SecretKey        key  = skf.generateSecret(spec);
        byte[]           res  = key.getEncoded();
        return res;
    }

    public static void main(String[] args) {
        String p1 = "password";
        String p2 = "wodemima";
        String p3 = "passord";
        String p4 = "mypass";
        String p5 = "123456789";

        PasswordHash ph = new PasswordHash();
        String       h1, h2, h3, h4, h5;
        try {
            h1 = ph.getSaltedHashPassword(p1);
            h2 = ph.getSaltedHashPassword(p2);
            h3 = ph.getSaltedHashPassword(p3);
            h4 = ph.getSaltedHashPassword(p4);
            h5 = ph.getSaltedHashPassword(p5);
            System.out.println(p1 + "\n" + p2 + "\n" + p3 + "\n" + p4 + "\n" + p5);
            System.out.println(h1 + "\n" + h2 + "\n" + h3 + "\n" + h4.length() + "\n" + h5.length());
            System.out.println(
                    ph.check(p1, h1) + "\n" + ph.check(p2, h2) + "\n" + ph.check(p2, h3) + "\n" + ph.check(p4, h1) +
                    "\n" + ph.check(p5, h2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
