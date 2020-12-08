package edu.miu.cs.neptune.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
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
    private ProfileVerificationType profileVerificationType = ProfileVerificationType.VERIFIED;
    private Boolean isResetPassword;
    private Boolean enable = true;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Enumerated (EnumType.STRING)
    private Role role;

    @OneToMany
    private List<Bid> bids = new ArrayList<>();

    @OneToMany
    private List<Product> products = new ArrayList<>();

    public User(Long userId, @NotBlank String username, @Email @NotBlank String email, @NotBlank String password, @NotBlank String firstName, @NotBlank String lastName, String licenseNumber, ProfileVerificationType profileVerificationType, Boolean isResetPassword,Boolean enable, Address address) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenseNumber = licenseNumber;
        this.profileVerificationType = profileVerificationType;
        this.isResetPassword = isResetPassword;
        this.enable = enable;
        this.address = address;

    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

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

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public void addBid(Bid bid) {
        this.bids.add(bid);
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public Boolean getEnable() {
        return enable;
    }

    public User setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }
}
