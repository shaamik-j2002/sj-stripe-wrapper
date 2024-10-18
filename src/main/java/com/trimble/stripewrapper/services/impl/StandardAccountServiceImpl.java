package com.trimble.stripewrapper.services.impl;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Account;
import com.stripe.model.AccountLink;
import com.stripe.param.AccountCreateParams;
import com.stripe.param.AccountLinkCreateParams;
import com.trimble.stripewrapper.dtos.AccountCreateDTO;
import com.trimble.stripewrapper.dtos.AccountResponseDTO;
import com.trimble.stripewrapper.dtos.AccountSignupDTO;
import com.trimble.stripewrapper.dtos.ErrorResponseDTO;
import com.trimble.stripewrapper.services.IAccountService;
import org.springframework.stereotype.Service;

@Service
public class StandardAccountServiceImpl implements IAccountService {

    @Override
    public AccountResponseDTO createConnectedAccount(AccountCreateDTO accountCreateDTO) {
        Stripe.apiKey = accountCreateDTO.getSecretKey();

        // do req validation here for accountCreateDTO
        // if email key is present, don't give invalid string or else don't have email field


        try {
            Account account = Account.create(AccountCreateParams.builder()
                    .setEmail(accountCreateDTO.getEmail())
                    .setCountry(accountCreateDTO.getCountry())
                    .setType(AccountCreateParams.Type.STANDARD)
                    .build()
            );


            return new AccountResponseDTO(account.getId(), account.getCreated().toString());
        } catch (StripeException e) {
            e.printStackTrace();
            return new AccountResponseDTO(new ErrorResponseDTO(e.getStatusCode().toString(), e.getMessage()));
        }
    }

    @Override
    public AccountResponseDTO getSignupLink(AccountSignupDTO accountSignupDTO) {
        Stripe.apiKey = accountSignupDTO.getSecretKey();

        // do req validation on accountSignupDTO for returnUrl, refreshUrl, accountId

        try {
            Account account = Account.retrieve(accountSignupDTO.getAccountId());

            AccountLinkCreateParams build = AccountLinkCreateParams.builder()
                    .setAccount(account.getId())
                    .setReturnUrl(accountSignupDTO.getReturnUrl())
                    .setRefreshUrl(accountSignupDTO.getRefreshUrl())
                    .setType(AccountLinkCreateParams.Type.ACCOUNT_ONBOARDING)
                    .build();

            AccountLink accountLink = AccountLink.create(
                    build
            );
            return new AccountResponseDTO(account.getId(), account.getCreated().toString(), accountLink.getUrl());
        } catch (StripeException e) {
            e.printStackTrace();
            return new AccountResponseDTO(new ErrorResponseDTO(e.getStatusCode().toString(), e.getMessage()));
        }
    }
}
