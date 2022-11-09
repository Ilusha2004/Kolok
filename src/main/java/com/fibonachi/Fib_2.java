package com.fibonachi;

public class Fib_2 {
    
    public int Fact(int n){
        if (n == 0){
            return 1;
        }
        return n * Fact(n - 1);
    }

}
