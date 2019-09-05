package com.example.annotation;

/**
 * @author：张鸿建
 * @time：2019/7/29 11:12
 * @desc：
 **/
@MyAnnotation(hello = "beijing", world="shanghai",array={},style=int.class)
public class MyTest
{
    @MyAnnotation( world = "shanghai",array={1,2,3})
    @Deprecated
    public void output()
    {
        System.out.println("output something!");
    }

    @PassTerm
    String bb;

    String aa;

    @PassTerm
    int a;

    @PassTerm
    double d;

    @PassTerm
    float f;

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "MyTest{" +
                "bb='" + bb + '\'' +
                ", aa='" + aa + '\'' +
                ", a=" + a +
                ", d=" + d +
                ", f=" + f +
                '}';
    }
}
