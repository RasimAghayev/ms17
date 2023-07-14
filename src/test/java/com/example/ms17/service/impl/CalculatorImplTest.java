package com.example.ms17.service.impl;

import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class CalculatorImplTest {
    CalculatorImpl calculator;

    @BeforeEach
    void setUp(){
        calculator=new CalculatorImpl();
    }

    @AfterEach
    void tarDown(){
        calculator=null;
    }
    @Test
    void givenAAndBThenSumSuccess() {
        double sum=calculator.sum(10,10);
        assertThat(sum).isEqualTo(20.0);
    }
    @Test
    void givenAAndBThenSumNotSuccess() {
        double sum=calculator.sum(10,10);
        assertThat(sum).isNotEqualTo(30.0);
    }

    @Test
    void givenAAndBThenSubSuccess() {
        double sum=calculator.sub(10,10);
        assertThat(sum).isEqualTo(0);
    }
    @Test
    void givenAAndBThenSubNotSuccess() {
        double sum=calculator.sub(10,10);
        assertThat(sum).isNotEqualTo(10.0);
    }

    @Test
    void givenAAndBThenDivideSuccess() {
        double sum=calculator.divide(10,10);
        assertThat(sum).isEqualTo(1.0);
    }
    @Test
    void givenAAndBThenDivideNotSuccess() {
        double sum=calculator.divide(10,10);
        assertThat(sum).isNotEqualTo(2.0);
    }
    @Test
    void givenAAndBThenDivideThrowSuccess() {
        AbstractThrowableAssert<?, ? extends Throwable> o = assertThatThrownBy(() -> calculator.divide(1, 0))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("b can not be 0");
    }

    @Test
    void givenAAndBThenMultipleSuccess() {
        double sum=calculator.multiple(10,10);
        assertThat(sum).isEqualTo(100.0);
    }
    @Test
    void givenAAndBThenMultipleNotSuccess() {
        double sum=calculator.multiple(10,10);
        assertThat(sum).isNotEqualTo(10.0);
    }

    @ParameterizedTest
    @DisplayName("IsOdd num list check")
    @ValueSource(ints={1,5,3,-3,15, Integer.MAX_VALUE})
    void givenNumbersThenIsOddThenSuccess(int number){
        assertThat(calculator.isOdd(number)).isTrue();
    }
}