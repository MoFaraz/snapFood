package fourthproject.snapfood.Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordToHash {

    public static String toHash (String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(password.getBytes());
            byte byteData[] = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < byteData.length ; i++)
                stringBuilder.append(Integer.toString(byteData[i] & 0xff  + 0x100, 16).substring(1));

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger("SHA-1").log(Level.SEVERE , null , e);
            return null;
        }
    }
}