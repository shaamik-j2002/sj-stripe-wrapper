package com.trimble.stripewrapper.services;

import com.trimble.stripewrapper.dtos.AccountCreateDTO;
import com.trimble.stripewrapper.dtos.AccountResponseDTO;
import com.trimble.stripewrapper.dtos.AccountSignupDTO;

public interface IAccountService {

    AccountResponseDTO createConnectedAccount(AccountCreateDTO accountCreateDTO);
    AccountResponseDTO getSignupLink(AccountSignupDTO accountSignupDTO);
}
