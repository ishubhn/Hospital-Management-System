package io.management.hospital.exception;

public class NoSuchDoctorEntityException extends RuntimeException {
    public NoSuchDoctorEntityException(String errMessage) {
        super(errMessage);
    }
}
