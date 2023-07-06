package com.OfferAlgorithm.SecondWeek.greedy;

public class LemonadeChange {

    /**
     * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。
     * 你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     * 注意，一开始你手头没有任何零钱。
     * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     */

    public static void main(String[] args) {
        //假设顾客付钱的数组
        int[] bills = {5,5,10,20,5};
        //调用找钱算法,判断是否给每位顾客都能正确找零
        boolean flag = lemonadeChange(bills);
        System.out.println(flag);
    }

    private static boolean lemonadeChange(int[] bills) {
        //记录5元钞票的数量
        int five = 0;
        //记录10元钞票的数量
        int ten = 0;
        for (int i = 0; i < bills.length - 1; i++) {
            //付款付款判断
            if(bills[i] == 5){
                five++;
            }else if(bills[i] == 10){
                if(five == 0){
                    return false;
                }
                five--;
                ten++;
            }else if(bills[i] == 20){
                if(ten == 0 && five >= 3){
                    five -= 3;
                }else if(ten > 0 && five > 0){
                    ten--;
                    five--;
                }else {
                    return false;
                }
            }
        }
        return true;
    }


}
