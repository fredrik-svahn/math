package math.function;

import java.util.Set;
import java.util.stream.Collectors;
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

     protected abstract Stream<Variable> variables();

     public Set<Variable> unboundVariables() {
          return variables().collect(Collectors.toSet());
     }

     public void randomize() {

     }

     @Override
     public boolean equals(Object object) {
          if(!(object instanceof Expression expression)) return false;

          if (!expression.unboundVariables().equals(unboundVariables())) return false;

          Set<Variable> variables = unboundVariables();

          for (int i = 0; i < 1e6; i++) {
               Expression expr1 = this;
               Expression expr2 = expression;

               for (Variable unboundVariable : variables) {
                    double maxValue = 100000;
                    double randomValue = Math.random() * maxValue / 2 - maxValue;
                    expr1 = expr1.bind(unboundVariable.toString(), new RealConstant(randomValue));
                    expr2 = expr2.bind(unboundVariable.toString(), new RealConstant(randomValue));

               }

               if(!expr1.asDouble().equals(expr2.asDouble())) return false;
          }

          return true;
     }

     public abstract Expression clone();
     public abstract String toString();

}
