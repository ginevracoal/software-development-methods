package it.esteco.calculator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculator {
    public static int add(String string) throws Exception {
        String[] tokens = tokenize(string);
        List<Integer> integers = convertToIntegers(tokens);
        if (hasNegatives(integers)) {
            throw new Exception("Negatives not allowed " + filterNegatives(integers));
        } else {
            return integers.stream().reduce(0, (a, b) -> a + b);
        }
    }

    private static String[] tokenize(String string) {
        Pattern patter = Pattern.compile("//(\\[\\D*\\])\n([\\s\\S]*)");
        Matcher matcher = patter.matcher(string);
        if (matcher.find()) {
            return split(matcher.group(2), buildDelimiterRegex(matcher.group(1)));
        } else {
            return split(string, ",|\n");
        }
    }

    private static String buildDelimiterRegex(String delimitersDefinition) {
        Pattern pattern = Pattern.compile("\\[([^\\d\\[\\]]+)\\]");
        Matcher matcher = pattern.matcher(delimitersDefinition);
        matcher.find();
        String regex = matcher.group(1);
        while (matcher.find()) {
            regex += "|" + matcher.group(1);
        }
        return regex;
    }

    private static List<Integer> convertToIntegers(String[] tokens) {
        return Arrays.stream(tokens).mapToInt(Integer::parseInt).boxed()
                .filter(integer -> integer < 1001).collect(Collectors.toList());
    }

    private static boolean hasNegatives(List<Integer> integers) {
        return integers.stream().anyMatch(integer -> integer < 0);
    }

    private static List<Integer> filterNegatives(List<Integer> integers) {
        return integers.stream().filter(integer -> integer < 0).collect(Collectors.toList());
    }

    private static String[] split(String string, String regex) {
        if (string.isEmpty()) {
            return new String[0];
        } else {
            return string.split(regex);
        }
    }
}
