package io.management.pharmacy.exceptions;

public class NoSuchMedicineExistException extends RuntimeException {
    public NoSuchMedicineExistException(String msg) {
        super(msg);
    }
}
