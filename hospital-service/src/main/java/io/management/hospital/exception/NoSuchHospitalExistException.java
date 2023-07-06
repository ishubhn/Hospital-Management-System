package io.management.hospital.exception;

public class NoSuchHospitalExistException extends RuntimeException {
    public NoSuchHospitalExistException(String errMessage) {
        super(errMessage);
    }
}
