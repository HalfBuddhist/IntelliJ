package com.algo_ds.math;

/**
 * 复数类
 */
public class Complex implements Cloneable{
    double real;  // 实部
    double image; // 虚部

    Complex(double real, double image) { // 带参数的构造方法
        this.real = real;
        this.image = image;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImage() {
        return image;
    }

    public void setImage(double image) {
        this.image = image;
    }

    public Complex setComplex(Complex c) {
        this.real = c.real;
        this.image = c.image;
        return this;
    }

    Complex add(Complex a) { // 复数相加
        double real2 = a.getReal();
        double image2 = a.getImage();
        this.real += real2;
        this.image += image2;
        return this;
    }

    Complex sub(Complex a) { // 复数相减
        double real2 = a.getReal();
        double image2 = a.getImage();
        this.real -= real2;
        this.image -= image2;
        return this;
    }

    Complex mul(Complex a) { // 复数相乘
        double real2 = a.getReal();
        double image2 = a.getImage();
        double real3 = real * real2 - image * image2;
        double image3 = image * real2 + real * image2;
        this.real = real3;
        this.image = image3;
        return this;
    }

    Complex div(Complex a) { // 复数相除
        if (a.real == 0 && a.image == 0)
            throw new ArithmeticException("Diveide zero complex!");
        double real2 = a.getReal();
        double image2 = a.getImage();
        double real3 = (real * real2 + image * image2) / (real2 * real2 + image2 * image2);
        double newImage = (image * real2 - real * image2) / (real2 * real2 + image2 * image2);
        this.real = real3;
        this.image = newImage;
        return this;
    }

    public Complex print() { // 输出
        if (image > 0) {
            System.out.println(real + " + " + image + "i");
        } else if (image < 0) {
            System.out.println(real + "" + image + "i");
        } else {
            System.out.println(real);
        }
        return this;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Complex data1 = new Complex(1, 2);
        Complex data1_back = (Complex) data1.clone();
        Complex data2 = new Complex(3, 4);

        // 以下分别为加减乘除
        data1.add(data2).print().setComplex(data1_back);
        data1.sub(data2).print().setComplex(data1_back);
        data1.mul(data2).print().setComplex(data1_back);
        data1.div(data2).print().setComplex(data1_back);
    }
}
