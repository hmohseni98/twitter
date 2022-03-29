package repository.base;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository<T,ID extends Serializable> {
    void save(T t);
    void update(T t);
    void delete(T t);
    T findById(Class<T> tClass , ID id);
    List<T> findAll(Class<T> tClass);
}
