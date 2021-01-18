/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import static token.RandomString.upper;

/**
 *
 * @author user
 */
public class TokenGenerate {

    static String token = "";

    public static void main(String[] args) {

        //String easy = RandomString.digits + RandomString.upper + RandomString.lower;
        //RandomString tickets = new RandomString(8, new SecureRandom(), easy);
        //StringTokenizer st1 = new StringTokenizer(generateString());
        // create a string of all characters
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = upperAlphabet.toLowerCase(Locale.ROOT);
        String numbers = "1234567890";

        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        int length = 7;

        for (int i = 0; i < length; i++) {

            int index = random.nextInt(alphaNumeric.length());
            char randomChar = alphaNumeric.charAt(index);
            sb.append(randomChar);
        }

        String randomString = sb.toString();

        StringTokenizer st1 = new StringTokenizer(randomString);

        for (int i = 1; st1.hasMoreTokens(); i++) {
            token = st1.nextToken();

            System.out.println("Token " + i + ": " + token);
        }

    }

    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

}
