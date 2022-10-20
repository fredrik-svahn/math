package math.function;

import java.util.stream.Stream;

public abstract class Expression {
     public abstract Expression evaluate();

     public abstract Expression bind(String variableName, Expression value);

     public Double asDouble() {
          Expression evaluated = evaluate();
          if(evaluated instanceof RealConstant) {
               return ((RealConstant) evaluated).value;
          }
          else {
               return null;
          }
     }

     public abstract Stream<Variable> variables();

     public void randomize() {

     }

     public boolean equals(Expression expression) {
          for (int i = 0; i < 1e6; i++) {
               expression.randomize();
               randomize();

               if(!evaluate().equals(expression.evaluate())) return false;
          }

          return true;
     }

     public abstract Expression clone();
     public abstract String toString();
}
