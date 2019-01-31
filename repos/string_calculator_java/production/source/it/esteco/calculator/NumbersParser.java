package it.esteco.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NumbersParser {

    private Pattern delimiterDefinitionPattern;
    private Matcher delimiterDefinitionMatcher;
    private String delimiterRegex;

    public NumbersParser() {
        this.delimiterRegex = "[\n,]";
        this.delimiterDefinitionPattern = Pattern.compile("//(\\[.*\\])\n(.*)");
    }

    public List<Integer> parse(String numbers) {
        if (hasCustomDelimiterDefinition(numbers)) {
            delimiterRegex = buildDelimiterRegex();
            numbers = getNumbers();
        }
        return Arrays.stream(numbers.split(delimiterRegex))
                .map(stringToInteger())
                .collect(Collectors.toList());
    }

    private boolean hasCustomDelimiterDefinition(String numbers) {
        delimiterDefinitionMatcher = delimiterDefinitionPattern.matcher(numbers);
        return delimiterDefinitionMatcher.find();
    }

    private String buildDelimiterRegex() {
        return new DelimitersRegex(delimiterDefinitionMatcher.group(1)).toString();
    }

    private String getNumbers() {
        return delimiterDefinitionMatcher.group(2);
    }

    private Function<String, Integer> stringToInteger() {
        return number -> {
            if (number.isEmpty()) {
                return 0;
            } else {
                return Integer.valueOf(number);
            }
        };
    }
}
