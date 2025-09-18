package com.infosupport.oo.inheritance;

public class Teacher extends Person {

    private final String specialism;

    public Teacher(String name, String sp) {
        super(name);
        this.specialism = sp;
    }

    public void walk() {
        System.out.println("Im thinking and walking");
    }

    @Override
    public int compareTo(Person o) {
        return specialism.compareTo(((Teacher) o).specialism);
    }
}
