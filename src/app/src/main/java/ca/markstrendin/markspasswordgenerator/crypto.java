package ca.markstrendin.markspasswordgenerator;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class crypto {

    public static String createPassword_Alpha(String input, String salt) {
        char[] characterArray_Alpha = {'1','2','3','4','5','6','7','8','9','0','a','b','c','d','e','f',
                'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
                'w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L',
                'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        try {
            return genPasswordWithThisHash(characterArray_Alpha, SHA512(input.concat(salt)));
        } catch(Exception ex) {
            return "SOMETHING BROKE: " + ex.toString();
        }
    }

    public static String createPassword_Special(String input, String salt)
    {
        char[] characterArray_Special = {'1','2','3','4','5','6','7','8','9','0','a','b','c','d','e','f',
                'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v',
                'w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L',
                'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','!','@','#',
                '$','%','^','*','(',')','_','+','?'};

        try {
            return genPasswordWithThisHash(characterArray_Special, SHA512(input.concat(salt)));
        } catch(Exception ex) {
            return "SOMETHING BROKE: " + ex.toString();
        }
    }

    private static String genPasswordWithThisHash (char[] characterSet, byte[] input) {
        String returnMe = "";

        for (byte x : input) {
            int byteConvertedToInt =(int)x & 0xff;

            while (byteConvertedToInt > characterSet.length -1) {
                byteConvertedToInt -= characterSet.length;
            }

            returnMe += characterSet[byteConvertedToInt];
        }
        return returnMe;
    }

    @SuppressWarnings("unused")
    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static byte[] SHA256(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-256");
        byte[] sha1hash = new byte[40];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        sha1hash = md.digest();
        return sha1hash;
    }

    public static byte[] SHA512(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-512");
        byte[] sha1hash = new byte[40];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        sha1hash = md.digest();
        return sha1hash;
    }

    public static String genRandomKey() {
        Long currentMillis = System.currentTimeMillis();
        return currentMillis.toString();
    }

}