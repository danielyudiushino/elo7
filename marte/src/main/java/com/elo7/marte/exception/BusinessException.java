package com.elo7.marte.exception;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 6943454228496714097L;

	private Set<Error> errors;

	public BusinessException(Throwable cause, Set<Error> errors) {
		super(cause);
		this.errors = errors;
	}

	public BusinessException(Set<Error> errors) {
		this(null, errors);
	}

	public BusinessException(String msg) {
		super(msg);
	}

	public Set<Error> getErrors() {
		return errors;
	}

	@Override
	public String getMessage() {
		if (errors != null) {
			StringBuilder sb = new StringBuilder();
			for (Error error : errors) {
				sb.append(error.getMessage());
				sb.append("|");
			}
			return sb.toString();
		} else {
			return super.getMessage();
		}
	}

}
