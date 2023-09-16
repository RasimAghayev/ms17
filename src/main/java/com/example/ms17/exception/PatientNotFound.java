package com.example.ms17.exception;

public class PatientNotFound extends NotFoundException {

    private static final String MESSAGE="Patient %s does not exist.";
    private static final long serialVersionUID=58432132465811L;

    public PatientNotFound(Long patientId){
        super(String.format(MESSAGE,patientId));
    }
}
