package com.example.otpgen.service;

import com.example.otpgen.model.OTP;
import com.example.otpgen.model.User;

public interface OTPService {
    public OTP createOTP(User user);
    public Boolean checkOtpValid(User user);
    public User checkOtp(String code, User user);
    public void purgeInvalidOtp();
}
