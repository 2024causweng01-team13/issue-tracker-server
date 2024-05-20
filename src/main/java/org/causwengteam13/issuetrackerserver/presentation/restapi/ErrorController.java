package org.causwengteam13.issuetrackerserver.presentation.restapi;

import org.causwengteam13.issuetrackerserver.common.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorController {

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ConstraintViolationException.class)
	public CommonResponse onConstraintViolationException(ConstraintViolationException e) {
		return CommonResponse.fail(HttpStatus.BAD_REQUEST, e);
	}

	@ResponseBody
	@ExceptionHandler(value = Problem.class)
	public CommonResponse onProblem(Problem e) {
		HttpStatus httpStatus = switch (e.getCategory()) {
			case INVALID_REQUEST -> HttpStatus.BAD_REQUEST;
			case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
			case FORBIDDEN -> HttpStatus.FORBIDDEN;
			case NOT_FOUND -> HttpStatus.NOT_FOUND;
			case UNPROCESSABLE -> HttpStatus.UNPROCESSABLE_ENTITY;
			default -> HttpStatus.SERVICE_UNAVAILABLE;
		};

		return CommonResponse.fail(httpStatus, e);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public CommonResponse onException(Exception e) {
		return CommonResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR, e);
	}
}
