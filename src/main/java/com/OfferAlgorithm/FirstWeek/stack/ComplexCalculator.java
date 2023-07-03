package com.OfferAlgorithm.FirstWeek.stack;

public class ComplexCalculator {

    /**
     * java用栈实现复杂计算器+ - * /
     */


    public static class Calculator {
        public static void main(String[] args) {
            //先创建两个栈，一个存放数字，一个存放运算符
            ArrayStack2 numStack = new ArrayStack2(10);
            ArrayStack2 operateStack = new ArrayStack2(10);
            //这是指针，指向当前表达式的位置
            int index = 0;
            int num1 = 0;
            int num2 = 0;
            String expression = "10-2-2*(5+10)+8-(21+3)*2";
            //入栈
            while (true) {
                //得到当前指针所指的字符串内容
                char ch = expression.substring(index, index + 1).charAt(0);
                //判断是符号还是数字
                if (operateStack.isOperate(ch)) {
                    //如果符号栈不为空
                    if (!operateStack.isEmpty()) {
                        //如果是'('号，则直接将'('号入栈
                        if (ch == '(') {
                            operateStack.push(ch);
                            //跳出循环
                            index++;
                            if (index >= expression.length()) {
                                break;
                            }
                            continue;
                        }
                        //如果是')'号，需要将')'号之前的字符都进行计算，将结果压入栈
                        else if (ch == ')') {
                            //返回操作栈的栈顶数据
                            int operation = operateStack.peek();
                            while (operation != '(') {
                                //小于就要从数栈中出两个数据，和栈顶元素进行运算
                                num1 = numStack.pop();
                                num2 = numStack.pop();
                                int operate = operateStack.pop();
                                int result = operateStack.calculate(num1, num2, operate);
                                //把运算的结果再入栈
                                numStack.push(result);
                                operation = operateStack.peek();
                            }
                            operateStack.pop();
                            //跳出循环
                            index++;
                            if (index >= expression.length()) {
                                break;
                            }
                            continue;
                        }
                            //如果当前符号优先级小于或者等于栈顶的符号，这里要反复的进行比较，
                            //原因： 2-2*5+10 这个表达式进行运算，如果这里的最后一个'+'号，如果只与前面的'*'号比较，
                            //那进行运算的后的结果是 2-10+10，这时'+'号入符号栈，在最后出栈进行运算时，数栈中此时
                            //数据为 [2 10 10] 符号栈数据为 [- +]，进行运算时首先出来的 是 10+10，计算结果20压入数栈
                            // ，然后是2-20,这显然不符合运算规则
                            while (!operateStack.isEmpty() && operateStack.priority(ch) <= operateStack.priority(operateStack.peek())) {
                                //小于就要从数栈中出两个数据，和栈顶元素进行运算
                                num1 = numStack.pop();
                                num2 = numStack.pop();
                                int operate = operateStack.pop();
                                int result = operateStack.calculate(num1, num2, operate);
                                //把运算的结果再入栈
                                numStack.push(result);
                            }
                            operateStack.push(ch);
                        } else {  //符号栈为空则直接入栈
                            operateStack.push(ch);
                        }
                    } else {  //如果是数字，那就直接入栈
                        if (index + 1 == expression.length()) {
                            numStack.push(ch - 48);
                        } else {
                            //如果是多位数
                            int temp = index + 1;   //获取当前表达式的下一个索引
                            char multi = expression.substring(temp, temp + 1).charAt(0);  //多位数进行拼接的元素
                            StringBuilder num = new StringBuilder(String.valueOf(ch));    //数字的第一个
                            while (!operateStack.isOperate(multi)) {    //当不是运算符时，需要拼接字符串
                                num.append(multi);   //这里进行拼接
                                temp = temp + 1;    //下一个索引
                                index++;    //这个相应的全局指针也要后移！！！！！！！！！
                                if (temp < expression.length()) { //小于则直接拆分赋值
                                    multi = expression.substring(temp, temp + 1).charAt(0);
                                } else {  //到了表达式的末尾，直接退出循环
                                    break;
                                }
                            }
                            numStack.push(Integer.parseInt(num.toString()));
                        }

                    }

                    //跳出循环
                    index++;
                    if (index >= expression.length()) {
                        break;
                    }
                }

                //入栈结束后，就要逐个的进行计算了
                while (true) {
                    //当运算符栈空时，代表计算结束
                    if (operateStack.isEmpty()) {
                        int result = numStack.pop();    //这时的栈顶是计算后的结果
                        System.out.printf("%s = %d", expression, result);
                        System.out.println();
                        break;
                    }
                    num1 = numStack.pop();
                    num2 = numStack.pop();
                    int operate = operateStack.pop();
                    int result = operateStack.calculate(num1, num2, operate);
                    //把运算的结果再入栈
                    numStack.push(result);
                }
            }

        }

        //创建栈类和栈类方法
        static class ArrayStack2 {
            //栈的大小
            private final int maxSize;
            //定义栈
            private final int[] stack;
            //栈顶，一定要初始化为-1，不然就有默认值0了
            private int top = -1;

            //初始化栈的大小
            public ArrayStack2(int maxSize) {
                this.maxSize = maxSize;
                stack = new int[maxSize];
            }

            //栈满
            public boolean ifFull() {
                return top == maxSize - 1;
            }

            //栈空
            public boolean isEmpty() {
                return top == -1;
            }

            //入栈
            public void push(int data) {
                //栈满时，不能入栈
                if (ifFull()) {
                    System.out.println("栈满不能入栈");
                }
                top++;
                stack[top] = data;
            }

            //出栈
            public int pop() {
                //栈空，不能出栈
                if (isEmpty()) {
                    throw new RuntimeException("栈空，没有数据");
                }
                //定义中间值接收栈顶元素
                int temp = stack[top];
                top--;
                return temp;
            }

            //输出栈（遍历栈），不过是反向遍历数组
            public void stackList() {
                //当栈为空时，不需要遍历
                if (isEmpty()) {
                    System.out.println("栈空，没有数据");
                }
                for (int i = top; i >= 0; i--) {
                    System.out.printf("stack[%d]=%d\n", i, stack[i]);
                }
            }

            //返回栈顶的数据，但不出栈，也就是说top指针并不移动
            public int peek() {
                return stack[top];
            }

            //判断是否是运算符
            public boolean isOperate(int operate) {
                return operate == '+' || operate == '-' || operate == '*' || operate == '/' || operate == '(' || operate == ')';
            }

            //加减乘除的优先级
            public int priority(int operate) {
                if (operate == '*' || operate == '/') {
                    //优先级高的
                    return 1;
                } else if (operate == '+' || operate == '-') {
                    //优先级低的
                    return 0;
                } else {
                    //错误的运算符
                    return -1;
                }
            }

            //进行运算
            public int calculate(int num1, int num2, int operate) {
                int result = 0;
                switch (operate) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num2 - num1;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num2 / num1;
                        break;
                    default:
                        break;
                }
                return result;
            }
        }
    }


