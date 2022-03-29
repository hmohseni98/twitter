package repository.lmpl;

import database.SessionFactorySingleton;
import repository.base.BaseRepository;
import lombok.var;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public abstract class BaseRepositoryImpl<T, ID extends Serializable> implements BaseRepository<T,ID> {
    protected SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(T t) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(T t) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void delete(T t) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.delete(t);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public T findById(Class<T> tClass, ID id) {
        try (var session = sessionFactory.openSession()) {
            return session.get(tClass, id);
        }
    }

    @Override
    public List<T> findAll(Class<T> tClass) {
        try (var session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(tClass);
            return criteria.list();
        }
    }
}
