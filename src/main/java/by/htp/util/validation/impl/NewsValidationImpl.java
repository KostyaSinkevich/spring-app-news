package by.htp.util.validation.impl;

import by.htp.util.validation.ValidationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsValidationImpl {

    private final List<String> errors;

    private NewsValidationImpl(NewsValidationBuilder e) {
        this.errors = e.errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors);
    }

    public static class NewsValidationBuilder implements ValidationBuilder<NewsValidationImpl> {
        private static final String TITLE = "Title is incorrect";
        private static final String BRIEF = "Brief is incorrect";
        private static final String CONTENT = "Content is incorrect";

        private List<String> errors = new ArrayList<>();

        private static final String REGEX_FOR_TITLE = "^[a-zA-Z0-9- ]{1,25}$";
        private static final String REGEX_FOR_BRIEF = "^[a-zA-Z0-9- ]{1,80}$";
        private static final String REGEX_FOR_CONTENT = "^[a-zA-Z][a-zA-Z0-9-_. ]{1,200}$";

        public NewsValidationBuilder checkTitle(String title) {
            Matcher titleMatcher = Pattern.compile(REGEX_FOR_TITLE).matcher(title);
            if (!titleMatcher.matches()) {
                errors.add(TITLE);
            }
            return this;
        }

        public NewsValidationBuilder checkBrief(String brief) {
            Matcher briefMatcher = Pattern.compile(REGEX_FOR_BRIEF).matcher(brief);
            if (!briefMatcher.matches()) {
                errors.add(BRIEF);
            }
            return this;
        }

        public NewsValidationBuilder checkContent(String content) {
            Matcher contentMatcher = Pattern.compile(REGEX_FOR_CONTENT).matcher(content);
            if (!contentMatcher.matches()) {
                errors.add(CONTENT);
            }
            return this;
        }

        @Override
        public NewsValidationImpl validateAll() {
            return new NewsValidationImpl(this);
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
