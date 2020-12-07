package edu.miu.cs.neptune.domain;

import java.time.LocalDateTime;

public class UserVerification {

    private String verificationCode;
    private LocalDateTime endingTime;
    private Integer failedVerificationCount;
    private Long userId;
    private  UserVerificationType userVerificationType;

    public String getVerificationCode() {
        return verificationCode;
    }

    public UserVerification setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
        return this;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public UserVerification setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime;
        return this;
    }

    public Integer getFailedVerificationCount() {
        return failedVerificationCount;
    }

    public UserVerification setFailedVerificationCount(Integer failedVerificationCount) {
        this.failedVerificationCount = failedVerificationCount;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public UserVerification setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserVerificationType getUserVerificationType() {
        return userVerificationType;
    }

    public UserVerification setUserVerificationType(UserVerificationType userVerificationType) {
        this.userVerificationType = userVerificationType;
        return this;
    }
}
