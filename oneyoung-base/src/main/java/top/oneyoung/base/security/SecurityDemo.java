package top.oneyoung.base.security;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * SecurityDemo
 *
 * @author oneyoung
 * @since 2023/8/31 18:04
 */
public class SecurityDemo {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] publicKeyBytes = publicKey.getEncoded();
        String publicKeyString = Base64.getEncoder().encodeToString(publicKeyBytes);
        byte[] privateKeyEncoded = privateKey.getEncoded();
        String pr = Base64.getEncoder().encodeToString(privateKeyEncoded);

        String data = "123";
        String data1 = "1234";
        byte[] sign = sign(data, pr);
        boolean dataResult = veryFly(data, sign, publicKeyString);
        boolean data1Result = veryFly(data1, sign, publicKeyString);
        System.out.println(dataResult); // true
        System.out.println(data1Result); // false

    }

    public static byte[] sign(String data, String privateKeyString) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);

        signature.update(data.getBytes());

        byte[] signatureBytes = signature.sign();
        return signatureBytes;

    }

    public static boolean veryFly(String data, byte[] signatureBytes, String publicKeyString) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);

        signature.update(data.getBytes());

        boolean isValid = signature.verify(signatureBytes);
        return isValid;
    }
}
