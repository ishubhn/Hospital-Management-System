package io.management.hospital.exception;

public class HospitalAlreadyPresentException extends RuntimeException {
	public HospitalAlreadyPresentException(String msg) {
		super(msg);
	}
}
