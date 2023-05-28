package by.htp.util.validation.impl;

import by.htp.util.validation.ValidationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidationImpl {

    private final List<String> errors;

    private UserValidationImpl(UserValidationBuilder b) {
        this.errors = b.errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
    }

    public static class UserValidationBuilder implements ValidationBuilder<UserValidationImpl> {
        private static final String LOGIN = "Login is incorrect";
        private static final String PASSWORD = "Password is incorrect";
        private static final String EMAIL = "Email is incorrect";
        private static final String NAME = "Name is incorrect";
        private static final String SURNAME = "Surname is incorrect";
        private static final String BIRTHDAY_DATE = "Birthday date is incorrect";

        private List<String> errors = new ArrayList<>();

        private static final String REGEX_FOR_LOGIN = "^[a-zA-Z][a-zA-Z0-9-_.]{1,15}$";
        private static final String REGEX_FOR_PASSWORD = "^[a-zA-Z]{1,12}$";
        private static final String REGEX_FOR_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][a-z]+(\\.[a-z]+)*(\\.[a-z]{2,})$";
        private static final String REGEX_FOR_NAME = "^[a-zA-Z]{1,12}$";
        private static final String REGEX_FOR_SURNAME = "^[a-zA-Z]{1,20}$";

        public UserValidationBuilder checkLogin(String login) {
            Matcher loginMatcher = Pattern.compile(REGEX_FOR_LOGIN).matcher(login);
            if (!loginMatcher.matches()) {
                errors.add(LOGIN);
            }
            return this;
        }

        public UserValidationBuilder checkPassword(String password) {

            Matcher passwordMatcher = Pattern.compile(REGEX_FOR_PASSWORD).matcher(password);
            if (!passwordMatcher.matches()) {
                errors.add(PASSWORD);
            }
            return this;
        }

        public UserValidationBuilder checkEmail(String email) {
            Matcher emailMatcher = Pattern.compile(REGEX_FOR_EMAIL).matcher(email);
            if (!emailMatcher.matches()) {
                errors.add(EMAIL);
            }
            return this;
        }

        public UserValidationBuilder checkName(String name) {
            Matcher nameMatcher = Pattern.compile(REGEX_FOR_NAME).matcher(name);
            if (!nameMatcher.matches()) {
                errors.add(NAME);
            }
            return this;
        }

        public UserValidationBuilder checkSurname(String surname) {
            Matcher surnameMatcher = Pattern.compile(REGEX_FOR_SURNAME).matcher(surname);
            if (!surnameMatcher.matches()) {
                errors.add(SURNAME);
            }
            return this;
        }

        @Override
        public UserValidationImpl validateAll() {
            return new UserValidationImpl(this);
        }
    }

    public String errorMessage() {
        StringBuilder message = new StringBuilder();
        for (String error : errors) {
            message.append(error).append(";");
        }
        message.deleteCharAt(message.lastIndexOf(";"));
        return message.toString();
    }
}
