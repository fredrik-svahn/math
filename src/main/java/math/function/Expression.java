package math.function;

public abstract class Expression {
     public abstract Expression evaluate();
     public abstract void bind(String variableName, Expression value);

     public Double asDouble() {
          Expression evaluated = evaluate();
          if(evaluated instanceof RealConstant) {
               return ((RealConstant) evaluated).value;
          }
          else {
               return null;
          }
     }
}
