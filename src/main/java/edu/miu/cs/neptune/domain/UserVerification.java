package edu.miu.cs.neptune.domain;

import java.time.LocalDateTime;

public class UserVerification {
    private String verificationCode;
    private LocalDateTime endingTime;
    private Integer failedVerificationCount;
    private Long userId;
    private  UserVerificationType userVerificationType;
}
