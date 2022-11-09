package com.fibonachi;

public class Fib_1{

    private int am = 1;

    public int FactNaive(int n){
        for (int i = 2; i <= n; ++i)
            am *= i;
        return am;   
    }
}
