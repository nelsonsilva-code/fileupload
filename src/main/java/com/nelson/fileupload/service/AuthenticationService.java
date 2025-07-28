package com.nelson.fileupload.service;

import com.nelson.fileupload.dto.*;

public interface AuthenticationService {
    JwtAuthResponse login(LoginDto loginDto);

    String createUser(String username);
}
