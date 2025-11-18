package com.infosupport.jeedemo;

import java.util.List;

public interface Dao<T> {
    T create(T t);

    List<T> findAll();
}
