package Service;


import CustomException.RecordDoesNotExist;
import Repository.BaseDaoImpl;
import java.io.Serializable;
import java.util.List;

public abstract class BaseService<R extends BaseDaoImpl<E, ID>, E, ID extends Serializable> {
    private R r;
    private Class<E> eClass;

    public BaseService(R r,Class<E> e) {
        this.r = r;
        eClass = e;
    }

    public void save(E e) {
        r.save(e);
    }

    public void update(E e) {
        r.update(e);
    }

    public void delete(E e) {
        r.delete(e);
    }

    public E findById(ID id) {
        E e = r.findById(eClass,id);
        if (e == null)
            throw new RecordDoesNotExist();
        return r.findById(eClass, id);
    }

    public List<E> findAll() {
        return r.findAll(eClass);
    }


}
