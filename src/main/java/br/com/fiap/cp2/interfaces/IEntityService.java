package br.com.fiap.cp2.interfaces;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface IEntityService<T> {

    @Transactional
    List<T> getAll();

    @Transactional
    void deleteById(Long id);

    @Transactional
    Optional<T> findById(Long id);

}
