package com.example.ms17.exception;

public class PatientNotFound extends NotFoundException {

    private static final String MESSAGE="Customer %s does not exist.";
    private static final long serialVersionUID=58432132465811L;

    public PatientNotFound(Long customerId){
        super(String.format(MESSAGE,customerId));
    }
}
