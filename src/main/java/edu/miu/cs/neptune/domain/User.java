package edu.miu.cs.neptune.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String licenseNumber;
    private ProfileVerificationType profileVerificationType;
    private Boolean isResetPassword;

    public Long getUserId() {
        return userId;
    }

    public User setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public User setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
        return this;
    }

    public ProfileVerificationType getProfileVerificationType() {
        return profileVerificationType;
    }

    public User setProfileVerificationType(ProfileVerificationType profileVerificationType) {
        this.profileVerificationType = profileVerificationType;
        return this;
    }

    public Boolean getResetPassword() {
        return isResetPassword;
    }

    public User setResetPassword(Boolean resetPassword) {
        isResetPassword = resetPassword;
        return this;
    }
}
