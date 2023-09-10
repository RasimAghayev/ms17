package com.example.ms17.exception;

import java.io.Serial;

public class CustomerNotFound extends NotFoundException {

    private static final String MESSAGE="Customer %s does not exist.";
    @Serial
    private static final long serialVersionUID=58432132465811L;

    public CustomerNotFound(Long customerId){
        super(String.format(MESSAGE,customerId));
    }
}
