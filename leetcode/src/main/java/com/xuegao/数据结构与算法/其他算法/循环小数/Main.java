package com.xuegao.数据结构与算法.其他算法.循环小数;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String[] inputArr = input.split(",");
        int numerator = Integer.valueOf(inputArr[0]);
        int denominator = Integer.valueOf(inputArr[1]);
        double quotient = (double) numerator / (double) denominator;
        //如果是循环小数
        if (isRepeating(numerator, denominator)) {
            //纯循环小数,eg:1/3,1/7
            if (isPureRepeating(numerator, denominator)) {
                ArrayList<Integer> remainderStack = new ArrayList<Integer>();
                ArrayList<Integer> quotientStack = new ArrayList<Integer>();
                int index = 0;
                remainderStack.add((numerator * 10) % denominator);
                quotientStack.add((numerator * 10) / denominator);
                while (true) {
                    int back = remainderStack.get(index) * 10;
                    while (back < numerator) {
                        back = back * 10;
                        //remainderStack.add(0);
                        quotientStack.add(0);
                    }
                    if (remainderStack.indexOf(back % denominator) != -1) {
                        //quotientStack.add(back/denominator);
                        //remainderStack.add(back%denominator);
                        int back1 = remainderStack.get(index) * 10;
                        while (back1 < numerator) {
                            back1 = back1 * 10;
                            //remainderStack.add(0);
                            quotientStack.add(0);
                        }
                        index = quotientStack.indexOf(back1 / denominator);
                        break;
                    } else {
                        quotientStack.add(back / denominator);
                        remainderStack.add(back % denominator);
                    }
                    index++;

                }

                System.out.print(remainderStack);
                System.out.print(quotientStack);
                System.out.println(quotientStack.get(index));

                System.out.print("0.");
                for (int i = 0; i < index; i++)
                    System.out.print(quotientStack.get(i));
                System.out.print("[");
                for (int i = index; i < quotientStack.size(); i++)
                    System.out.print(quotientStack.get(i));
                System.out.print("]");
            }
            //混合循环小数,1/6,34/45
            else {
                ArrayList<Integer> remainderStack = new ArrayList<Integer>();
                ArrayList<Integer> quotientStack = new ArrayList<Integer>();
                int tenNum = numerator * 10;
                int index = 0;
                int rindex = 0;
                while (tenNum < denominator) {
                    remainderStack.add(0);
                    quotientStack.add(0);
                    index++;
                    tenNum *= 10;
                }
                remainderStack.add(tenNum % denominator);
                quotientStack.add(tenNum / denominator);
                while (true) {
                    int tenR = remainderStack.get(index) * 10;
                    while (tenR < denominator) {
                        remainderStack.add(0);
                        quotientStack.add(0);
                        index++;
                        tenR *= 10;
                    }
                    if (remainderStack.indexOf(tenR % denominator) != -1) {
                        index = remainderStack.indexOf(tenR % denominator);
                        //特殊处理1/6的情况
                        if (index == 0) {
                            remainderStack.add(tenR % denominator);
                            quotientStack.add(tenR / denominator);
                            index = remainderStack.lastIndexOf(tenR % denominator);
                        }
                        break;
                    } else {
                        remainderStack.add(tenR % denominator);
                        quotientStack.add(tenR / denominator);
                        index++;
                        //rindex++;
                    }
                }

                System.out.print("0.");
                for (int i = 0; i < index; i++)
                    System.out.print(quotientStack.get(i));
                System.out.print("[");
                for (int i = index; i < quotientStack.size(); i++)
                    System.out.print(quotientStack.get(i));
                System.out.print("]");
            }
        }
        //如果不是循环小数
        else {
            System.out.print(quotient);
        }

    }

    //判断numerator/denominatoe是否是循环小数
    public static Boolean isRepeating(int numerator, int denominator) {
        int simpleNum = 0;
        int simpleDen = 0;
        if (numerator < denominator) {
            for (int i = numerator; i > 0; i--) {
                if (numerator % i == 0 && denominator % i == 0) {
                    simpleNum = numerator / i;
                    simpleDen = denominator / i;
                    break;
                }
            }
        } else {
            for (int i = denominator; i > 0; i--) {
                if (numerator % i == 0 && denominator % i == 0) {
                    simpleNum = numerator / i;
                    simpleDen = denominator / i;
                    break;
                }
            }
        }
        //如果分数化简后分母除了2,5之外还有素因数，就是无限循环小数
        for (int i = 2; i <= simpleDen; i++) {
            if (simpleDen % i == 0) {
                if (isPrime(i)) {
                    if (i != 2 && i != 5) return true;
                }
            }
        }
        return false;
    }

    //判断是否是素数
    public static boolean isPrime(int n) {
        if (n == 1 || n == 2 || n == 3) return true;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    //判断是否是纯循环小数，其中numerator<denominator
    public static boolean isPureRepeating(int numerator, int denominator) {
        int simpleNum = 0;
        int simpleDen = 0;
        if (numerator < denominator) {
            for (int i = numerator; i > 0; i--) {
                if (numerator % i == 0 && denominator % i == 0) {
                    simpleNum = numerator / i;
                    simpleDen = denominator / i;
                    break;
                }
            }
        } else {
            for (int i = denominator; i > 0; i--) {
                if (numerator % i == 0 && denominator % i == 0) {
                    simpleNum = numerator / i;
                    simpleDen = denominator / i;
                    break;
                }
            }
        }
        //如果化简后的分母能分解成两个或两个以上素数的乘积，则是混合无限循环小数
        if (isResolveToTwoPrime(simpleDen)) return false;
        else return true;
    }

    //判断分母是否能分解成两个以上素数的乘积，如果是返回false，否则true
    public static boolean isResolveToTwoPrime(int num) {
        int i;
        int count = 0;
        for (i = 2; i < num; i++) {
            while (i != num) {
                if (num % i == 0) {
                    count++;
                    num /= i;
                } else
                    break;
            }
        }
        if (count >= 1) return true;
        else return false;
    }
}