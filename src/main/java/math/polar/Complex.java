package math.polar;

import math.exception.MatrixDimensionMismatch;
import math.matrix.ArrayListMatrix;
import math.matrix.Matrix;

public class Complex extends ArrayListMatrix {

    public Complex() {
        this(0);
    }

    public Complex(Matrix matrix) {
        this(matrix.get(0, 0), matrix.get(0, 1));
    }

    public Complex(double real) {
        this(real, 0);
    }

    public Complex(double real, double imaginary) {
        set(0, 0, real);
        set(0, 1, imaginary);
    }

    public double imaginary() {
        return get(0, 1);
    }

    public double real() {
        return get(0, 0);
    }

    public Complex multiply(Complex complex) {
        double a = real();
        double b = imaginary();
        double c = complex.real();
        double d = complex.imaginary();

        return new Complex(
                a*c-b*d,
                b*c+a*d
        );
    }

    public Complex add(Complex complex) {
        try {
            return new Complex(((Matrix) this).add(complex));
        } catch (MatrixDimensionMismatch ignore) { }

        return null;
    }

    public Complex subtract(Complex complex) {
        return new Complex((this).add(complex.multiply(-1)));
    }

    public Complex multiply(double k) {
        return new Complex(((Matrix) this).multiply(k));
    }

    public Complex divide(Complex complex) {
        Complex numerator = this.multiply(complex.conjugate());
        Complex denominator = complex.multiply(complex.conjugate());
        return numerator.multiply(1/(denominator.real()));
    }

    public Complex conjugate() {
        return new Complex(real(), -imaginary());
    }

    @Override
    public String toString() {
        if(imaginary() > 0) {
            return String.format("%f+%fi", real(), imaginary());
        }
        else if(imaginary() < 0) {
            return String.format("%f%fi", real(), imaginary());
        }
        else {
            return String.format("%f", real());
        }
    }
}
