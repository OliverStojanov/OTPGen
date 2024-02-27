package com.example.otpgen.service.impl;

import com.example.otpgen.model.OTP;
import com.example.otpgen.model.User;
import com.example.otpgen.model.exceptions.OTPDoesNotMatchException;
import com.example.otpgen.model.exceptions.OtpDoesNotExistException;
import com.example.otpgen.model.exceptions.TimeRunOutException;
import com.example.otpgen.repository.OTPRepository;
import com.example.otpgen.service.OTPService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class OTPServiceImpl implements OTPService {

    private final OTPRepository otpRepository;
    private final PasswordEncoder passwordEncoder;

    public OTPServiceImpl(OTPRepository otpRepository, PasswordEncoder passwordEncoder) {
        this.otpRepository = otpRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OTP createOTP(User user) {
            if(!checkOtpValid(user)) {
                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                StringBuilder stringBuilder = new StringBuilder();
                Random random = new Random();
                for (int i = 0; i < 6; i++) {
                    int randomIndex = random.nextInt(characters.length());
                    stringBuilder.append(characters.charAt(randomIndex));
                }
                OTP otp = new OTP(user.id, stringBuilder.toString(), LocalTime.now());
                otpRepository.save(otp);
                return otp;
            }
            return null;
    }

    @Override
    public Boolean checkOtpValid(User user) {
        if(otpRepository.findByIdEquals(user.id).isPresent()) {
            OTP otp = otpRepository.findByIdEquals(user.id).orElseThrow(OtpDoesNotExistException::new);
            if(otp.timestamp.isBefore(LocalTime.now().minus(30, ChronoUnit.SECONDS))){
                otpRepository.deleteById(user.id);
                return false;
            }
            else {
                return true;
            }
        }
        return false;
    }

    @Override
    public User checkOtp(String code, User user){
        if(otpRepository.findByIdEquals(user.id).isPresent()){
            OTP otp = otpRepository.findByIdEquals(user.id).orElseThrow(OtpDoesNotExistException::new);
            if(otp.timestamp.isBefore(LocalTime.now().minus(30, ChronoUnit.SECONDS))){
                System.out.println("time passed");
                throw new TimeRunOutException();
            }
            if (!otp.otp.equals(code)) {
                System.out.println("otp code is not correct");
                throw new OTPDoesNotMatchException();
            }
        }
        System.out.println("logged in successfully");
        return user;
    }

    @Override
    @Transactional
    public void purgeInvalidOtp() {
        otpRepository.deleteAllByTimestampBefore(LocalTime.now().minus(30, ChronoUnit.SECONDS));
    }
}
