/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author sonnt-local
 */
public class Rect {

    private int x;
    private int y;
    private int h;
    private int w;
    private int i;

    public Rect(int i, int x, int y, int h, int w) {
        this.i=i;
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

}
