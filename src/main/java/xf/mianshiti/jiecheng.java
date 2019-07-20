package xf.mianshiti;

import java.util.Scanner;

/**
 * 作者:小爱艾
 * 2019/7/17 20:06
 */
public class jiecheng {

    public static void main(String[] args) {

        System.out.println("请输入一个大于1的整数：");
        Scanner s = new Scanner(System.in);
        int x = s.nextInt();

        int result = 1;
        for(int i = 1;i<=x;i++ ){

            result = result * i;


        }

        System.out.println(x+"!="+result);
    }





}
