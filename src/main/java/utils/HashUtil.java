package utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash util
 * @author A.Shporta
 *
 */
public class HashUtil {
	/**
	 * Hash string by SHA-256
	 * @param input
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    }

	public static String bin2hex(byte[] data) {
		return String.format("%0" + (data.length*2) + "X", new BigInteger(1, data));
	}
}
