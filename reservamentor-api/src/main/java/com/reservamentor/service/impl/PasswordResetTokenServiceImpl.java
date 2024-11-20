package com.reservamentor.service.impl;

import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.integration.notification.email.dto.Mail;
import com.reservamentor.integration.notification.email.service.EmailService;
import com.reservamentor.model.entity.PasswordResetToken;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.repository.PasswordResetTokenRepository;
import com.reservamentor.repository.UsuarioRepository;
import com.reservamentor.service.PasswordResetTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UsuarioRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Transactional
    @Override
    public void createAndSendPasswordResetToken(String email) throws Exception {
        Usuario user = userRepository.findByCorreo(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con email: " + email));

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(UUID.randomUUID().toString());
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiration(10);
        passwordResetTokenRepository.save(passwordResetToken);

        Map<String, Object> model =new  HashMap<>();
        String resetUrl = "http://localhost:4200/forgot/" + passwordResetToken.getToken();
        model.put("user", user.getCorreo());
        model.put("resetUrl", resetUrl);

        Mail mail = emailService.createMail(
                user.getCorreo(),
                "Restablecer contraseña",
                model,
                mailFrom
        );

        emailService.sendEmail(mail,"email/password-reset-template");
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Token no encontrado"));
    }

    @Override
    public void removeResetToken(PasswordResetToken passwordResetToken) {
        passwordResetTokenRepository.delete(passwordResetToken);
    }

    @Override
    public boolean isValidToken(String token) {
        return passwordResetTokenRepository.findByToken(token)
                .filter(t->!t.isExpired())
                .isPresent();
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token)
                .filter(t->!t.isExpired())
                .orElseThrow(() -> new ResourceNotFoundException("Token invalido"));

        Usuario user = resetToken.getUser();
        user.setContrasenia(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        passwordResetTokenRepository.delete(resetToken);
    }
}