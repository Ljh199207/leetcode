package com.ljh.java;

/**
 * @author ljh
 * @date 2019-11-19 09:08
 */
public class StringContrat {

    public static void main(String[] args) {
     //   long start = System.currentTimeMillis();
        //3538
       /* String result = "";
        for (int i = 0; i < 100000; i++) {
            result += "六六六";
        }*/
        //6
       /* StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append("六六六");
        }*/
    //   long end = System.currentTimeMillis();
    //    System.out.println(end-start);

        String a  ="前卫";
        String b = "克隆姆";
        String c  = String.join("-",a,b,"有限公司");
        System.out.println(c);



    }
}
