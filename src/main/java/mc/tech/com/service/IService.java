package mc.tech.com.service;

import java.util.Optional;

public interface IService<T,ID>  {

    T create(T t);
    Optional<T > read(ID id);
    void delete(Long Id);
}