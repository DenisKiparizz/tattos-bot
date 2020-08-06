package com.tatto.bot.service;

import java.util.List;

public interface CrudService<T, Q> {
    List<T> getAll();

    T create(T t);

    T update(Long id, Q q);

    void delete(Long id);
}
