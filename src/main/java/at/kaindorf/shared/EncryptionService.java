package at.kaindorf.shared;

import javax.enterprise.context.RequestScoped;
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
}
