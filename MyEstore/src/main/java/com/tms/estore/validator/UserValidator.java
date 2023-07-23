package com.tms.estore.validator;

import com.tms.estore.domain.User;
import com.tms.estore.dto.UserFormDto;
import com.tms.estore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.tms.estore.utils.Constants.ErrorMessage.*;
import static com.tms.estore.utils.Constants.UserVerifyField.*;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserFormDto user = (UserFormDto) target;
        checkUserLoginAndEmail(errors, user);
        checkPasswordInputVerify(errors, user);
    }

    private void checkUserLoginAndEmail(Errors errors, UserFormDto testUser) {
        Optional<User> user = userService.getVerifyUser(testUser.getLogin(), testUser.getEmail());
        if (user.isPresent()) {
            User foundUser = user.get();
            checkUserByLogin(errors, testUser, foundUser);
            checkUserByEmail(errors, testUser, foundUser);
        }
    }

    private void checkUserByEmail(Errors errors, UserFormDto testUser, User foundUser) {
        if (foundUser.getEmail().equals(testUser.getEmail())) {
            errors.rejectValue(EMAIL, "", EXISTING_EMAIL);
        }
    }

    private void checkUserByLogin(Errors errors, UserFormDto testUser, User foundUser) {
        if (foundUser.getLogin().equals(testUser.getLogin())) {
            errors.rejectValue(LOGIN, "", EXISTING_USER);
        }
    }

    private void checkPasswordInputVerify(Errors errors, UserFormDto user) {
        if (!user.getPassword().equals(user.getVerifyPassword())) {
            errors.rejectValue(VERIFY_PASSWORD, "", PASSWORDS_MATCHING);
        }
    }
}
