package com.trimble.stripewrapper.controllers;

import com.stripe.exception.StripeException;

import com.trimble.stripewrapper.dtos.AccountCreateDTO;
import com.trimble.stripewrapper.dtos.AccountResponseDTO;
import com.trimble.stripewrapper.dtos.AccountSignupDTO;
import com.trimble.stripewrapper.services.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounts")
public class AccountsRestController {

    private final IAccountService accountService;

    public AccountsRestController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/create-account")
    public ResponseEntity<AccountResponseDTO> createConnectedAccount(@RequestBody AccountCreateDTO accountCreateDTO) throws StripeException {
        AccountResponseDTO accountResponseDTO = accountService.createConnectedAccount(accountCreateDTO);

        if (accountResponseDTO.getErrorDTO() != null) {
            return ResponseEntity.badRequest().body(accountResponseDTO);
        } else {
            return ResponseEntity.ok().body(accountResponseDTO);
        }
    }

    @GetMapping("/signup-link")
    public ResponseEntity<AccountResponseDTO> createSignupLink(@RequestBody AccountSignupDTO accountSignupDTO) throws StripeException {
        AccountResponseDTO accountResponseDTO = accountService.getSignupLink(accountSignupDTO);

        if (accountResponseDTO.getErrorDTO() != null) {
            return ResponseEntity.badRequest().body(accountResponseDTO);
        } else {
            return ResponseEntity.ok().body(accountResponseDTO);
        }
    }
}