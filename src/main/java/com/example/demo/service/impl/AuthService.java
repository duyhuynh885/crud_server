package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.messages.MessageHandle;
import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.payload.request.ForgetPasswordRequest;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService {

    private final AuthenticationManager authenticationManager;


    private final UserRepository userRepository;


    private final RoleRepository roleRepository;


    private final PasswordEncoder encoder;


    private final JwtUtils jwtUtils;

    private final JavaMailSender mailSender;

    /**
     * @param authenticationManager
     * @param userRepository
     * @param roleRepository
     * @param encoder
     * @param jwtUtils
     * @param mailSender
     */
    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils, JavaMailSender mailSender) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.mailSender = mailSender;
    }

    /**
     * Sign In Function
     *
     * @param loginRequest LoginRequest
     * @return User
     * @throws ResponseEntity Exceiption
     */

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        try {
            // Find user
            Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());

            // Check user is deleted logic
            if (user.get().isDetele()) return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(MessageHandle.USER_DELETE));
            // check user name and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Generate Jwt Token
            String jwt = jwtUtils.generateJwtToken(authentication);
            // get user details
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            // get role
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(MessageHandle.SIGN_IN_FAIL));
        }

    }

    /**
     * Sign Up Function
     *
     * @param signUpRequest SignupRequest
     * @return Message
     * @throws ResponseEntity Exceiption
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        // check duplicate by user name
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(MessageHandle.USERNAME_TAKEN));
        }
        // check duplicate by email
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(MessageHandle.EMAIL_TAKEN));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException(MessageHandle.ROLE_NOT_FOUND));
            roles.add(userRole);
        } else {
            // set role for user
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(MessageHandle.ROLE_NOT_FOUND));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException(MessageHandle.ROLE_NOT_FOUND));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(MessageHandle.ROLE_NOT_FOUND));
                        roles.add(userRole);
                }
            });
        }
        user.setRegistrationDate(new Timestamp(System.currentTimeMillis()));
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse(MessageHandle.SIGNUP_SUCCESS));
    }


    /**
     * Forget Password Function
     *
     * @param forgetPasswordRequest ForgetPasswordRequest
     * @return Message
     * @throws ResponseEntity Exceiption
     */
    public ResponseEntity<?> forgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        String email = forgetPasswordRequest.getEmail();
        // general token
        String token = RandomString.make(30);
        try {
            // update reset password tokne
            updateResetPasswordToken(token, email);
            // set link send email
            String resetPasswordLink = "http://localhost:3000/reset-password/" + token;
            // call send email
            sendEmail(email, resetPasswordLink);
        } catch (UnsupportedEncodingException | MessagingException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(MessageHandle.SEND_MAIL_ERROR));
        }
        return ResponseEntity.ok(new MessageResponse(MessageHandle.SEND_MAIL_SUCCESS));
    }

    /**
     * Reset Password Function
     *
     * @param forgetPasswordRequest ForgetPasswordRequest
     * @return ResponseEntity
     */
    public ResponseEntity<?> resetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        String password = forgetPasswordRequest.getPassword();
        String token = forgetPasswordRequest.getToken();
        // find user by token
        User user = userRepository.findByResetPasswordToken(token);

        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(MessageHandle.INVALID_TOKEN));
        } else {
            // update password
            updatePassword(user, password);
            return ResponseEntity.ok(new MessageResponse(MessageHandle.RESET_PASS_SUCCESS));
        }
    }

    /**
     * Send Email to reset password for User
     *
     * @param recipientEmail String,link String
     * @return void
     * @throws org.springframework.mail.MailException Exceiption
     */
    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("duyhuynh.2015@gmail.com", "CURD Support");
        helper.setTo(recipientEmail);

        // set content for send email form
        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    /**
     * Save reset password token to DB ,
     *
     * @param token String,email String
     * @return void
     * @throws ResourceNotFoundException Exceiption
     */
    public void updateResetPasswordToken(String token, String email) {
        // find user by email
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // set reset password token for user
            user.setReset_password_token(token);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException(MessageHandle.NOTFOUND_USER + email);
        }
    }

    /**
     * Update password for forget password function
     *
     * @param user User , newPassword String
     * @return void
     * @throws org.springframework.mail.MailException Exception
     */
    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // encode password
        String encodedPassword = passwordEncoder.encode(newPassword);
        // save new password
        user.setPassword(encodedPassword);
        user.setReset_password_token(null);
        userRepository.save(user);
    }
}
