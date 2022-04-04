package math.function;

import java.util.function.Consumer;



public class Product extends Expression{

    private final Expression c1;
    private final Expression c2;

    public Product(Expression c1, Expression c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public void withConstantValue(Consumer<Double> consumer) {
        if(c1 instanceof Constant) {
            c1.withConstantValue(val -> {
                System.out.println(val);
                
                if(val.equals(0d)) {
                    consumer.accept(0d);
                }
            });
        }
        else if(c2 instanceof Constant) {
            c2.withConstantValue(val -> {
                if(val.equals(0d)) {
                    consumer.accept(0d);
                }
            });
        }
        else {
            c1.withConstantValue(v1 -> {
                c2.withConstantValue(v2 -> {
                    consumer.accept(v1*v2);
                });
            });
        }

    }

    @Override
    public void bindAll(String x, double i) {
        c1.bindAll(x, i);
        c2.bindAll(x, i);
    }

    @Override
    public void withDerivative(String variable, Consumer<Expression> consumer) {
        c1.withDerivative(variable, d1 -> {
            c2.withDerivative(variable, d2 -> {
                Expression d3 = new Sum(new Product(d1, c2), new Product(d2, c1));
                consumer.accept(d3);
            });
        });
    }

    @Override
    public String toString() {
        return "(" + c1 + "*" + c2 + ")";
    }
}
