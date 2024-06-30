package com.tabletrack.table_track_API.service.impl;

import com.tabletrack.table_track_API.constant.ERole;
import com.tabletrack.table_track_API.model.dto.request.AuthRequest;
import com.tabletrack.table_track_API.model.dto.request.CustomerRequest;
import com.tabletrack.table_track_API.model.dto.response.LoginResponse;
import com.tabletrack.table_track_API.model.dto.response.RegisterResponse;
import com.tabletrack.table_track_API.model.entity.authentication.AppUser;
import com.tabletrack.table_track_API.model.entity.order.Customer;
import com.tabletrack.table_track_API.model.entity.authentication.Role;
import com.tabletrack.table_track_API.model.entity.authentication.UserCredential;
import com.tabletrack.table_track_API.repository.UserCredentialRepository;
import com.tabletrack.table_track_API.security.JwtUtil;
import com.tabletrack.table_track_API.service.AuthService;
import com.tabletrack.table_track_API.service.CustomerService;
import com.tabletrack.table_track_API.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CustomerService customerService;
    private final RoleService roleService;
    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerCustomer(AuthRequest authRequest) {
        if(authRequest == null || authRequest.getUsername() == null){
            throw new IllegalArgumentException("Username cannot be null");
        }

        String username = authRequest.getUsername().toLowerCase();

        try {
            //TODO 1 set the role
            Role role = roleService.getOrSave(ERole.USER);

            //TODO 2 set Credential
            UserCredential userCredential = UserCredential.builder()
                    .username(username)
                    .password(passwordEncoder.encode(authRequest.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            //TODO 3 set Customer
            Customer customer = Customer.builder()
                    .name(authRequest.getName())
                    .address(authRequest.getAddress())
                    .email(authRequest.getEmail())
                    .phoneNum(authRequest.getMobilePhone())
                    .userCredential(userCredential)
                    .build();

            CustomerRequest customerRequest = CustomerRequest.builder()
                    .name(customer.getName())
                    .address(customer.getAddress())
                    .email(customer.getEmail())
                    .phoneNum(customer.getPhoneNum())
                    .build();
            customerService.create(customerRequest);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString())
                    .build();

        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"user already exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername().toLowerCase(),
                authRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Object of the AppUser
        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}
