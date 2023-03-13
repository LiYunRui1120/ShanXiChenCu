package com.example.springbootdemo1.controller;

/**
 * @author 李云瑞
 * @date 2023/2/2 17:33
 */
public class Test5 {
    public static String output="";

    public static void main(String[] args) {
//        foo(0);   //134
//        foo(1);   //23
//        System.out.println(output);//13423
    }



    public static void foo(int i){
        try{
            if(i==1){
                throw new Exception();
            }
            output+="1";
        }catch(Exception e){

            output+="2";
            return;
        }finally{
            output+="3";
        }
        output+="4";
    }

}
