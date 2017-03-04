package com.algo_ds.math;

/**
 * 复数类，实部与虚部为长整型
 */
public class ComplexLong implements Cloneable {
    long real;  // 实部
    long image; // 虚部

    ComplexLong(long real, long image) { // 带参数的构造方法
        this.real = real;
        this.image = image;
    }

    public long getReal() {
        return real;
    }

    public void setReal(long real) {
        this.real = real;
    }

    public long getImage() {
        return image;
    }

    public void setImage(long image) {
        this.image = image;
    }

    public ComplexLong setComplex(ComplexLong c) {
        this.real = c.real;
        this.image = c.image;
        return this;
    }

    ComplexLong add(ComplexLong a) { // 复数相加
        long real2 = a.getReal();
        long image2 = a.getImage();
        this.real += real2;
        this.image += image2;
        return this;
    }

    ComplexLong sub(ComplexLong a) { // 复数相减
        long real2 = a.getReal();
        long image2 = a.getImage();
        this.real -= real2;
        this.image -= image2;
        return this;
    }

    ComplexLong mul(ComplexLong a) { // 复数相乘
        long real2 = a.getReal();
        long image2 = a.getImage();
        long real3 = real * real2 - image * image2;
        long image3 = image * real2 + real * image2;
        this.real = real3;
        this.image = image3;
        return this;
    }

    /**
     * 采用了long型除法
     *
     * @param a
     * @return
     */
    ComplexLong div(ComplexLong a) { // 复数相除
        if (a.real == 0 && a.image == 0)
            throw new ArithmeticException("Diveide zero complex!");
        long real2 = a.getReal();
        long image2 = a.getImage();
        long real3 = (real * real2 + image * image2) / (real2 * real2 + image2 * image2);
        long newImage = (image * real2 - real * image2) / (real2 * real2 + image2 * image2);
        this.real = real3;
        this.image = newImage;
        return this;
    }

    public ComplexLong print() { // 输出
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
        ComplexLong data1 = new ComplexLong(1, 2);
        ComplexLong data1_back = (ComplexLong) data1.clone();
        ComplexLong data2 = new ComplexLong(3, 4);

        // 以下分别为加减乘除
        data1.add(data2).print().setComplex(data1_back);
        data1.sub(data2).print().setComplex(data1_back);
        data1.mul(data2).print().setComplex(data1_back);
        data1.div(data2).print().setComplex(data1_back);
    }
}
