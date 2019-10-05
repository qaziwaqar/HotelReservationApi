package com.waqar.reservation.web.api;

import com.waqar.reservation.web.security.ApiSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/principal")
public class UserController {

    @Autowired
    ApiSecurity apiSecurity;

    @GetMapping
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }

    @GetMapping("/generatePassword")
    public String generatePassword(@RequestParam("value") String value) {
        return apiSecurity.passwordEncoder().encode(value);
    }
}
