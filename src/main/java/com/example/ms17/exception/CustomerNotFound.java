package com.example.ms17.exception;

public class CustomerNotFound extends NotFoundException {

    private static final String MESSAGE="Customer %s does not exist.";
    private static final String serialVersionUID=58432132465811L;

    public CustomerNotFound(Long customerId){
        super(String.format(MESSAGE,customerId));
    }
}
