package tdd.kata.calculator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Stream;

//public class StringCalculator {
//    public int sum(String numbersAsText){
//        if(numbersAsText.isEmpty())
//            return 0; // if the string is empty
//        else {//if (numbersAsText.contains(",")){
//            String[] numbers = numbersAsText.split("," | "\n");
            // sum of two numbers
            // return Integer.valueOf(numbers[0])+Integer.valueOf(numbers[1]);

            // this does not consider the empty string
//            return Arrays.stream(numbers).mapToInt(Integer::valueOf).sum();

            // but with lambda function it's too complicated...
            // return Arrays.stream(numbers).mapToInt(text -> text.isEmpty() ? 0: Integer::valueOf).sum();
//        }

        //else if (numbersAsText.contains("\n")){
        //    String[] numbers = numbersAsText.split("\n");
        //    return Integer.valueOf(numbers[0])+Integer.valueOf(numbers[1]);
        //}
        // return Integer.valueOf(numbersAsText); // if the string contains only one number
//    }
//}

public class StringCalculator {

    private void negativesNotAllowed(int[] values) throws Exception {
        int[] negatives = Arrays.stream(values).filter(x->x<0).toArray();
        if(negatives.length>0){  // this also handles the empty string
            throw new Exception("Negatives not allowed: " + Arrays.toString(negatives));
        }
    }

    private int[] filterBigger1000(String numbersAsText){
        return tokenize(numbersAsText).mapToInt(Integer::valueOf).filter(x->x<1000).toArray();
    }

    public int sum(String numbersAsText) throws Exception {
        int[] values = filterBigger1000(numbersAsText);

        negativesNotAllowed(values);

        return Arrays.stream(values).sum();
    }

    // only finds the delimiter
    private String findDelimiter (String numbersAsText){
        String delimiter = ",|\n";
        if (numbersAsText.startsWith("//")){
            String[] values = numbersAsText.split("\n");
            delimiter = values[0].substring(2); // substring from second value
        }
        return delimiter;
    }

    private Stream<String> tokenize(String numbersAsText) {
        if (numbersAsText.isEmpty()) {
            return Stream.empty();
        }

        String delimiter = findDelimiter(numbersAsText);

        // special case of custom delimiter
        if (numbersAsText.startsWith("//")){
            // removes "//newdelimiter\n"
            numbersAsText = numbersAsText.substring(3+delimiter.length());
        }

        // I'm tokenizing using delimiter
        return Arrays.stream(numbersAsText.split(delimiter));
    }


}
