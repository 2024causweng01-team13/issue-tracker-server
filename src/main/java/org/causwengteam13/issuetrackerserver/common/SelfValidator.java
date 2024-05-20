package org.causwengteam13.issuetrackerserver.common;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public abstract class SelfValidator<T> {

	static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@SuppressWarnings("unchecked")
	protected void validateAndIfViolatedThrows() {
		Set<ConstraintViolation<T>> violations = validator.validate((T) this);

		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
	}

	@SuppressWarnings("unchecked")
	protected Set<ConstraintViolation<T>> validate() {
		return validator.validate((T) this);
	}
}
