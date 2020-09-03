package com.ljh.java;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author ljh
 * @date 2020-06-11 09:11
 */
public class StreamUtil {

    public static void main(String[] args) {
       /* Stream<String> stream3 = Stream.generate(() -> "love").limit(10);
        String[] strArr3 = stream3.toArray(String[]::new);
        System.out.println(Arrays.toString(strArr3));*/

        /*Stream<BigInteger> bigIntStream = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE)).limit(10);
        BigInteger[] bigIntArr = bigIntStream.toArray(BigInteger[]::new);
        System.out.println(Arrays.toString(bigIntArr));*/

        String sentence = "ma zhi chu is a Java wechat official account.";
        Stream<String> wordStream = Pattern.compile("\\W").splitAsStream(sentence);
        String[] wordArr = wordStream.toArray(String[]::new);
        System.out.println(Arrays.toString(wordArr));
        Double scusemoney  = 15.189;

        System.out.println(new DecimalFormat("#0.00").format(scusemoney));


    }
}
