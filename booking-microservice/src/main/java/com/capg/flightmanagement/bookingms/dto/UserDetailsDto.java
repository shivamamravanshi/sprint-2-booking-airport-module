package com.capg.flightmanagement.bookingms.dto;

import java.math.BigInteger;

/***
 * @author Shivam Amravanshi
 *
 * This class is use to transfer the User Details
 */
public class UserDetailsDto {
    private BigInteger userId;
    private String userName;
    private BigInteger userPhone;
    private String email;

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigInteger getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(BigInteger userPhone) {
        this.userPhone = userPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
