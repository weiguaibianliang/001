package com.OfferAlgorithm.FirstWeek.stack;

import java.util.Scanner;
import java.util.Stack;

public class IsValid {

    /**
     * 给定一个包含'(',')','{','}','[',']'的字符串s，判断字符串是否有效。
     * 有效字符串满足：
     * 1、左括号必须用相同类型的右括号封闭
     * 2、左括号必须以正确的顺序闭合
     */


    public boolean isValid(String s){
        //题目解析
        //1、字符串的长度一定是偶数。
        //2、括号的匹配遵循右括号和最近的一个左括号进行匹配，它们匹配成功才有可能是有效的括号。
        char[] chars = s.toCharArray();
        if(chars.length % 2 != 0){
            return false;
        }
        //创建一个栈来存储括号
        Stack<Character> stack = new Stack<>();
        //遍历你输入的字符串的字符
        for (char c : chars) {
            //此时的字符
            if (c == '(') {
                //把该括号入栈
                stack.push(c);
            } else if (c == '{') {
                stack.push(c);
            } else if (c == '[') {
                stack.push(c);
            } else {
                //如果栈里面数据为空，则直接不是有效字符串
                if (stack.isEmpty()) {
                    return false;
                }
                //如果栈里面数据不为空，那么就要判断即将输入的字符是否满足左右对称规则
                //找出栈的栈顶元素
                char top = stack.peek();
                if (top == '(' && c == ')' || top == '{' && c == '}' || top == '[' && c == ']') {
                    //这个时候就要从栈顶来删除元素进行匹配
                    stack.pop();
                } else {
                    //说明有不匹配的字符
                    return false;
                }
            }
        }
        //如果最后栈为空，说明是有效字符串
        //如果最后栈不为空，说明不是有效字符串
        return stack.isEmpty();

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //输出按题意要求的字符串，然后进行判断字符串是否有效。
        System.out.println("请输入指定的字符串：");
        String s = scanner.nextLine();
        IsValid isValid = new IsValid();
        boolean flag = isValid.isValid(s);
        System.out.println("该字符串有效："+flag);
    }
}
