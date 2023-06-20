package io.management.hospital.exception;

public class NoSuchOwnerEntityException extends RuntimeException {
	public NoSuchOwnerEntityException(String message) {
		super(message);
	}
}
