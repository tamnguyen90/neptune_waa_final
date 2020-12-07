package edu.miu.cs.neptune.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotBlank
    private String username = "namnguyen";
    @Email
    @NotBlank
    private String email = "namco2011@gmail.com";
    @NotBlank
    private String password = "123456";
    @NotBlank
    private String firstName = "Truong Thanh Nam";
    @NotBlank
    private String lastName = "Nguyen";
//    @NotBlank
    private String licenseNumber;
    private ProfileVerificationType profileVerificationType;
    private Boolean isResetPassword;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles = new ArrayList<>();

    @OneToMany
    private List<Bid> bids = new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public ProfileVerificationType getProfileVerificationType() {
        return profileVerificationType;
    }

    public void setProfileVerificationType(ProfileVerificationType profileVerificationType) {
        this.profileVerificationType = profileVerificationType;
    }

    public Boolean getResetPassword() {
        return isResetPassword;
    }

    public void setResetPassword(Boolean resetPassword) {
        isResetPassword = resetPassword;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public void addBid(Bid bid) {
        this.bids.add(bid);
    }
}
