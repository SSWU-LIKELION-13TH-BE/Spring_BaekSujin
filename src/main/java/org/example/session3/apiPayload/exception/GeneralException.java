package org.example.session3.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.session3.apiPayload.code.BaseErrorCode;
import org.example.session3.apiPayload.dto.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;
    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }
    public ErrorReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
