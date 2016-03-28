# Password


## Password Guidelines

**Passwords are intrinsically weak. Therefore, your application should encourage good password practices:**

- Credentials should only traverse encrypted links
- **Store the password in a strongly hashed and salted format to prevent rainbow table attacks.**
- Pass phrases (long passwords over 20 characters in length) should be encouraged
- Short passwords should be prohibited
- Do not force folks to change passwords frequently as this results in the users writing the passwords down insecurely
- Where suitable, try to share the credential with as many low value systems as possible to encourage one single high quality password
- Allow expert users to store strong passwords in approved password managers. Encourage them to use unique random passwords for each service

### **Protect passwords**

> Passwords are the primary means of authenticating users on the Web today. It is important that any Web site guard the passwords of its users carefully. This is especially important since users, when faced with many Web sites requiring passwords, tend to reuse passwords across sites.

- Compromise of a password completely compromises a user. A site should never reveal a password to a user.
- A valid user should already know the password; **sending it unnecessarily over the network gives the eavesdropping adversary more opportunity to sniff the password.**
- Do **not store passwords in cleartext in your database**. User databases are routinely hacked, leaked or gleaned through SQL injection, and if you are storing raw, plaintext passwords, that is instant game over for your login security.

- **[stack exchange: How to securely hash passwords?](http://security.stackexchange.com/questions/211/how-to-securely-hash-passwords/31846#31846)**
- **[stack exchange: Token-based authentication - Securing the token](http://security.stackexchange.com/questions/19676/token-based-authentication-securing-the-token)**
- **[stack overflow: How token-based authentication works](http://stackoverflow.com/questions/26777083/best-practice-for-rest-token-based-authentication-with-jax-rs-and-jersey)**


## Salted Password Hashing

### [Salted Password Hashing - Doing it Right](https://crackstation.net/hashing-security.htm)

> Hash algorithms are one way functions. They turn any amount of data into a fixed-length "fingerprint" that cannot be reversed.

The general workflow for account registration and authentication in a hash-based account system is as follows:

1. The user creates an account.
2. Their password is hashed and stored in the database. At no point is the plain-text (unencrypted) password ever written to the hard drive.
3. When the user attempts to login, the hash of the password they entered is checked against the hash of their real password (retrieved from the database).
4. If the hashes match, the user is granted access. If not, the user is told they entered invalid login credentials.
5. Steps 3 and 4 repeat every time someone tries to login to their account.

> In step 4, never tell the user if it was the username or password they got wrong. Always display a generic message like "Invalid username or password." This prevents attackers from enumerating valid usernames without knowing their passwords.


### To Store a Password

- Generate a long random salt using a CSPRNG.
    - ie. Java	java.security.SecureRandom
        - `byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);`
- Prepend the salt to the password and hash it with a standard cryptographic hash function such as SHA256.
- Save both the salt and the hash in the user's database record.
- To Validate a Password

### Retrieve the user's salt and hash from the database.
- Prepend the salt to the given password and hash it using the same hash function.
- Compare the hash of the given password with the hash from the database. If they match, the password is correct. Otherwise, the password is incorrect.


```java
final int keyLength = 256;
public byte[] hashPassword(final char[] password, final byte[] salt, final int iterations,
        final int keyLength) throws Exception {
    try {
        SecretKeyFactory skf  = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        PBEKeySpec       spec = new PBEKeySpec(password, salt, iterations, keyLength);
        SecretKey        key  = skf.generateSecret(spec);
        byte[]           res  = key.getEncoded();
        return res;
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        throw new RuntimeException(e);
    }
}
```

#### **PBKDF2** (Password-Based Key Derivation Function 2) 

is a key derivation function that is part of RSA Laboratories' Public-Key Cryptography Standards (PKCS) series

> PBKDF2 applies a pseudorandom function, such as a cryptographic hash, cipher, or HMAC, to the input password or passphrase along with a salt value and repeats the process many times to produce a derived key, which can then be used as a cryptographic key in subsequent operations. The added computational work makes password cracking much more difficult, and is known as key stretching.
> [wikipedia: PBKDF2](https://en.wikipedia.org/wiki/PBKDF2)

#### a keyed-Hash Message Authentication Code (**HMAC**) 

> HMAC is a specific type of message authentication code (MAC) involving a cryptographic hash function (hence the 'H') in combination with a secret cryptographic key. As with any MAC, it may be used to simultaneously verify both the data integrity and the authentication of a message. Any cryptographic hash function, such as MD5 or SHA-1, may be used in the calculation of an HMAC; the resulting MAC algorithm is termed HMAC-MD5 or HMAC-SHA1 accordingly. The cryptographic strength of the HMAC depends upon the cryptographic strength of the underlying hash function, the size of its hash output, and on the size and quality of the key.

#### [The Secure Hash Algorithm](https://en.wikipedia.org/wiki/Secure_Hash_Algorithm)
 
is a family of cryptographic hash functions published by the National Institute of Standards and Technology (NIST) as a U.S. Federal Information Processing Standard (FIPS), including:

- SHA-0: A retronym applied to the original version of the 160-bit hash function published in 1993 under the name "SHA". It was withdrawn shortly after publication due to an undisclosed "significant flaw" and replaced by the slightly revised version SHA-1.
- SHA-1: A **160-bit hash function** (Internal state size (bits) 160 (5 × 32)) which resembles the earlier MD5 algorithm. This was designed by the National Security Agency (NSA) to be part of the Digital Signature Algorithm. Cryptographic weaknesses were discovered in SHA-1, and the standard was no longer approved for most cryptographic uses after 2010.
- [SHA-2](https://en.wikipedia.org/wiki/SHA-2): A family of two similar hash functions, with different block sizes, known as SHA-256 and **SHA-512**. They differ in the word size; SHA-256 uses 32-bit words where **SHA-512 uses 64-bit words** (Internal state size (bits) 512 (8 × 64)	). There are also truncated versions of each standard, known as SHA-224, SHA-384, SHA-512/224 and SHA-512/256. These were also designed by the NSA.
- SHA-3: A hash function formerly called Keccak, chosen in 2012 after a public competition among non-NSA designers. It supports the same hash lengths as SHA-2, and its internal structure differs significantly from the rest of the SHA family.


```java
SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
```

The `PBKDF2WithHmacSHA1` will produce a hash length of 160 bits.

```java
SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
```

The `PBKDF2WithHmacSHA512` will produce a hash length of 512 bits

```java
final int saltLen       = 32;
byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
```

> pseudo-random number generator (PRNG), which means they use a deterministic algorithm to produce a pseudo-random sequence from a true random seed. 
> [docs.oracle](https://docs.oracle.com/cd/E17802_01/j2se/j2se/1.5.0/jcp/beta1/apidiffs/java/security/SecureRandom.html)


salted password hashing Java example:

```java
private final int iterations = 20 * 1000;
private final int saltLen = 32;
private final int desiredKeyLen = 256;

/**
 * salt and hash a given password
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
 * @param password the password
 * @param stored   the stored
 * @return the boolean
 * @throws Exception the exception
 */
public boolean validatePassword(String password, String stored) throws Exception {
    String[] saltAndPass = stored.split("\\$");
    if (saltAndPass.length != 2) {
        throw new IllegalStateException("The stored password have the form 'salt$hash'");
    }
    String hashOfInput = hashPassword(password, Base64.decodeBase64(saltAndPass[0]));
    return hashOfInput.equals(saltAndPass[1]);
}

/**
 * Gets salted hash password for storing in database.
 * @param password the string password
 * @param salt     the salt
 * @return the salted hash string password
 * @throws Exception the exception
 */
private String hashPassword(String password, byte[] salt) throws Exception {
    if (password == null || password.length() == 0) {
        throw new IllegalArgumentException("password cannot be empty!");
    }
    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
    PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, desiredKeyLen);
    return Base64.encodeBase64String(skf.generateSecret(spec).getEncoded());
}
```