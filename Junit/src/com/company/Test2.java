package com.company;

/**
 * Created by 咔咔吃萝卜 on 2018/3/22.
 */
public class Test2 {

    public String sayHello(int a ,int b, int c){

        if (a+b>c && a+c>b && b+c> a && a>0 && b>0 && c>0){
            //是三角形
            if (a==b && b == c && a==c){
                return"等边";
            }else if((a==b&&b!=c)||(b==c&&a!=c)||(a==c&&b!=c)){
                return"等腰";
            }else{
                return"普通";
            }
        }
        return "no";
    }
}
