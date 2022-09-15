package com.caonam.qlbn.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GeneralService<T> {
    List<T> findAll();

    Optional<T> findById(UUID id);

    void save(T t);

    void update(T t, UUID id);

    void deleteById(UUID id);
}
