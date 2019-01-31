package it.esteco.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DelimitersRegex {

    private final List<String> delimiters = new ArrayList<>();

    public DelimitersRegex(String string) {
        Pattern patter = Pattern.compile("\\[([^\\d\\[\\]]+)\\]");
        Matcher matcher = patter.matcher(string);
        while (matcher.find()) {
            delimiters.add(matcher.group(1));
        }
    }

    @Override
    public String toString() {
        String delimitersAsText = delimiters.remove(0);
        for (String delimiter : delimiters) {
            delimitersAsText += "|" + delimiter;
        }
        return delimitersAsText;
    }
}
