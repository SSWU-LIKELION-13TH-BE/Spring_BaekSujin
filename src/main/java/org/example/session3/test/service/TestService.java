package org.example.session3.test.service;

import org.example.session3.apiPayload.exception.GeneralException;
import org.springframework.stereotype.Service;
import org.example.session3.apiPayload.code.ErrorStatus;

@Service
public class TestService {
    public void checkFlag(Integer flag) {
        if (flag != null && flag == 1) {
            throw new GeneralException(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
