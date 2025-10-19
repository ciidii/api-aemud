package org.aemudapi.security;

import lombok.AllArgsConstructor;
import org.aemudapi.utils.ResponseVO;
import org.aemudapi.utils.ResponseVOBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtUtil jwtUtil;

    @PostMapping("authenticate")
    public ResponseEntity<ResponseVO<AuthenticationResponse>> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new ResponseVOBuilder<AuthenticationResponse>().addData(new AuthenticationResponse(jwt)).build());
    }
}