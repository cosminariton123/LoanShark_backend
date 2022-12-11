package com.loansharkmss.LoanShark.v1.util;

import com.loansharkmss.LoanShark.v1.config.EncryptionConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryption {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(EncryptionConfig.ENCRYPTION_STRENGTH);

    public String encryptPassword(String password) {
        return encoder.encode(password);
    }

    public Boolean verifyIfMatches(String password, String encryptedPassword) {
        return encoder.matches(password, encryptedPassword);
    }

}
