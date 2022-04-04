package math.function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {
    private String formula;

    public Function(String i) {
        formula = i;
        }

    public Function(double value) {
        formula = String.valueOf(value);
    }

    public double evaluate() {
        return Double.parseDouble(formula);
    }

    public void bind(String x, double value) {
        Pattern pattern = Pattern.compile("([0-9]+)?" + x);
        Matcher matcher = pattern.matcher(formula);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                formula = formula.replace(
                        matcher.group(),
                        String.valueOf(Integer.parseInt(matcher.group(1))
                                * value)
                );
            } else {
                formula = formula.replace(matcher.group(), String.valueOf(value));
            }
        }

        formula = formula.replace(x, "" + value);
    }
}
