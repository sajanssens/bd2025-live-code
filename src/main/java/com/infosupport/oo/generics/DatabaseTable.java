package com.infosupport.oo.generics;

import java.util.List;

public class DatabaseTable<T> {
    private List<T> contents;

    public List<T> list() { return contents; }
    public T retrieve(int index) { return contents.get(index); }
    public void store(T item) { contents.add(item); }
    public void delete(int index) { contents.remove(index); }
}
