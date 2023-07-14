package com.example.ms17.service.impl;

import com.example.ms17.service.Calculator;

public class CalculatorImpl implements Calculator {
    @Override
    public double sum(double a, double b) {
        return a+b;
    }

    @Override
    public double sub(double a, double b) {
        return a-b;
    }

    @Override
    public double divide(double a, double b) {
        if (b!=0){
            return a/b;
        }
        throw new ArithmeticException("b can not be 0");
    }

    @Override
    public double multiple(double a, double b) {
        return a*b;
    }
    @Override
    public boolean isOdd(int num) {
        return num%2!=0;
    }
}
