/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author user
 */
public class TokenCheckDao {

    static String token = "";
    String pattern = "mm";

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("da", "DK"));
    //Date date1 = new Date(System.currentTimeMillis());
    String date = simpleDateFormat.format(calendar.getTime());

    public TokenModel getToken(TokenModel model) {

        TokenModel outModel = new TokenModel();

        String username = "admin";
        String password = "admin";

        if (username.equals(model.getUserName()) && password.equals(model.getPassword())) {
            tokenPrint();

            outModel.setTockenNo(token);
            outModel.setOutCode("0");
            outModel.setOutMessage("Token Generate Successfull");
            outModel.setSessionTime(date);
            outModel.setUserName(username);

        } else {
            outModel.setOutCode("1");
            outModel.setOutMessage("No Token Found");

        }

        return outModel;
    }

    public void tokenPrint() {
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

    public static void main(String[] args) {

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

        // Value Check
        TokenCheckDao tokenCheckDao = new TokenCheckDao();
        TokenModel model = new TokenModel();
        model.setUserName("admin");
        model.setPassword("admin");
        System.out.println("model = " + model);
        TokenModel token1 = tokenCheckDao.getToken(model);
        System.out.println("Token No = " + token1.getTockenNo());
        System.out.println("Out Message = " + token1.getOutMessage());
        System.out.println("Out Code = " + token1.getOutCode());
        System.out.println("Session Time = " + token1.getSessionTime());
        System.out.println("Session Time = " + token1.getUserName());

    }

}
