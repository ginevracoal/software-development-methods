/*
IDEA:
- write a test and see it fail
- make it pass (even in a very bad way)
- refactor the code
*/

package tdd.kata.calculator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

//import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
//import org.hamcrest.core.IsEqual;
import static org.junit.Assert.*;

public class AddNumbersTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    // 1.
    @Test
    public void emptyString() throws Exception{ // do not begin with capital letters!
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum(""), is(equalTo(0)));
    }

    @Test
    public void oneNumber() throws Exception{
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("1"), is(equalTo(1)));

    }

    @Test
    public void twoNumbersSeparatedByComma() throws Exception{
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("1,2"), is(equalTo(3)));
    }

    @Test
    public void manyNumbersSeparatedByComma() throws Exception{
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("1,2,5,3"), is(equalTo(11)));
    }

    @Test
    public void twoNumbersNewline() throws Exception{
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("2\n3"), is(equalTo(5)));
    }

    @Test
    // this cannot fail anyways
    public void newLine() throws Exception{
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("1\n6\n8"), is(equalTo(15)));
    }

    @Test
    public void mixCommasAndLines() throws Exception{
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("2,6\n8"), is(equalTo(16)));
    }

    //2. + 5.

    // TODO correct these 2 tests with [] around the new delimiter
    // the old delimiters do not work anymore
    @Test
    public void customDelimiter() throws Exception{
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("//\\[;\\]\n2;6;8"), is(equalTo(16)));
    }

    @Test
    public void longerCustomDelimiter() throws Exception{
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("//\\[&&\\]\n2&&6&&8"), is(equalTo(16)));
    }

    //3.

    @Test
    public void singleNegative() throws Exception{
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Negatives not allowed: [-8]");

        StringCalculator calculator = new StringCalculator();
        calculator.sum("-8");
    }

    @Test
    public void multipleNegatives() throws Exception{
        expectedException.expect(Exception.class);
        expectedException.expectMessage("Negatives not allowed: [-8, -10]");

        StringCalculator calculator = new StringCalculator();
        calculator.sum("-8,4,-10");
    }

    //4.
    @Test
    public void biggerThan1000() throws Exception{
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("6,1200\n1"), is(equalTo(7)));
    }

    @Test
    public void onlyBigNumbers() throws Exception{
        StringCalculator calculator = new StringCalculator();
        assertThat(calculator.sum("1200\n3000"), is(equalTo(0)));
    }

    // TODO complete the exercise from readme.md

    //6.

}
