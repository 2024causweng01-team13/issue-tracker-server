package org.causwengteam13.issuetrackerserver.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProblemCategory {
	INVALID_REQUEST,
	UNAUTHORIZED,
	FORBIDDEN,
	NOT_FOUND,
	UNPROCESSABLE,
	SERVICE_UNAVAILABLE;
}
