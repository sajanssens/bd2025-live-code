package com.infosupport.mvc.model.domain;

public class House {
    private long id;

    public House(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                '}';
    }
}
