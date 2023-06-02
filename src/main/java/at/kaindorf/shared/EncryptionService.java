package at.kaindorf.shared;

import at.kaindorf.persistence.dto.UserDto;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.InternalServerErrorException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;

@RequestScoped
public class EncryptionService {
    public static String byteToHex(byte[] bytes) {
        return HexFormat.of().formatHex(bytes);
    }

    public static byte[] hexToByte(String hexString) {
        return HexFormat.of().parseHex(hexString);
    }

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new InternalServerErrorException("Failed to generate Encryption Service");
        }
        return messageDigest;
    }

    public static String getHashedPassword(String password, byte[] salt) {
        MessageDigest messageDigest = getMessageDigest();

        messageDigest.update(salt);
        messageDigest.update(password.getBytes(StandardCharsets.UTF_8));

        byte[] hashedBytes = messageDigest.digest();

        return byteToHex(salt) + ":" + byteToHex(hashedBytes);
    }

}
