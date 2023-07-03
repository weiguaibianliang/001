package com.OfferAlgorithm.FirstWeek.stack;

import java.util.Scanner;
import java.util.Stack;

public class Calculate {

    /**
     * 给你一个字符串表达式s，请你实现一个基本计算器并返回它的值。
     * 提示：
     * 1<=s.length<=3*105
     * s由数字、'+'、'-'、'('、')'、和''组成
     * s表示一个有效的表达式
     * 1+2+(2-3+4)
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //输入一个字符串表达式s
        System.out.println("输入字符串表达式s:");
        String s = scanner.nextLine();
        Calculate calculate = new Calculate();
        int a = calculate.calculate(s);
        System.out.println("输出计算的结果："+a);
    }

    private  int calculate(String s) {
        //使用栈来存储字符串表达式中的数字
        Stack<Integer> stack = new Stack<Integer>();
        //为了方便计算，所有的操作都视为加法操作
        int sign = 1;
        //保存计算的结果
        int result = 0;
        //获取字符串的长度，然后获取里面的每个字符
        int length = s.length();
        //获取字符串里面的每个字符
        for (int i = 0; i < length; i++){
            //获取此时的字符
            char ch = s.charAt(i);
            //如果当前字符时数字的话
            if(Character.isDigit(ch)){
                //相当于转换成了ascii码进行的数字运算
                int value = ch - '0';
                //去查看当前字符的后一位是否存在
                //如果操作并且后一位依旧是数字，那么就需要把后面的数字累加上来
                while (i + 1 < length && Character.isDigit(s.charAt(i+1))){
                    //i向后移动，直到遇到非数字为止
                    i++;
                    //利用计算规则
                    value = value*10 + s.charAt(i) - '0';
                }
                //把获取的数累加到res上
                result = result + sign * value;
            }
            //如果是'+'
            else if(ch == '+'){
                //那么说明直接加一个整数
                sign = 1;
            }else if(ch == '-'){
                //那么说明直接加一个负数
                sign = -1;
            }
            //如果是‘(’
            else if(ch == '('){
                //需要先计算括号里面的数字
                //先把（之前的结果放入到栈中
                stack.push(result);
                //重新初始化result为0
                result = 0;
                //把（左边的操作符号存放到栈中
                stack.push(sign);
                //为了计算方便，所有的操作都视为加法操作
                sign = 1;
            }else if(ch == ')'){
                //先获取栈顶元素，即左括号外面的符号，查看是+还是-
                int formerSign = stack.peek();
                //把栈顶元素弹出
                stack.pop();
                //再获取栈顶元素，即左括号结果
                int formerRes = stack.peek();
                //把栈顶元素弹出
                stack.pop();
                //那结果就是括号外面的结果+括号里面的结果，至于是加整数还是负数，取决于左括号外面的符号
                result = formerRes + formerSign * result;
            }
        }

        //返回计算好的结果
        return result;
    }
}
