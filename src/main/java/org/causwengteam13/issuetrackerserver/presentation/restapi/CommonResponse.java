package org.causwengteam13.issuetrackerserver.presentation.restapi;

import org.causwengteam13.issuetrackerserver.common.Problem;
import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonResponse<T> {

	private HttpStatus httpStatus;
	private String message;
	private String errorCode;
	private T data;

	public static <T>CommonResponse success(String message, T data) {
		return CommonResponse.builder()
			.httpStatus(HttpStatus.OK)
			.message(message)
			.data(data)
			.build();
	}

	public static <T>CommonResponse fail(HttpStatus httpStatus, Exception e) {
		return CommonResponse.builder()
			.httpStatus(httpStatus)
			.message(e.getMessage())
			.data(e.getStackTrace())
			.build();
	}

	public static <T>CommonResponse fail(HttpStatus httpStatus, Problem e) {
		return CommonResponse.builder()
			.httpStatus(httpStatus)
			.message(e.getMessage())
			.errorCode(e.getErrorCode())
			.data(e.getCause())
			.build();
	}
}