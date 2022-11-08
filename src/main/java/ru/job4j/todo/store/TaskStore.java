package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskStore {

    private final SessionFactory sf;

    public TaskStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Task create(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        session.close();
        return task;
    }

    public boolean update(int id, Task task) {
        Session session = sf.openSession();
        int rsl = 0;
        try {
            session.beginTransaction();
            rsl = session.createQuery(
                            "UPDATE Task SET description = :fDesc, done = false WHERE id = :fId")
                    .setParameter("fDesc", task.getDescription())
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return rsl != 0;
    }

    public boolean delete(Integer id) {
        boolean rsl = false;
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery(
                        "DELETE Task WHERE id = :fId")
                .setParameter("fId", id)
                .executeUpdate();
        session.getTransaction().commit();
        rsl = true;
        session.close();
        return rsl;
    }

    public void setTaskIsDone(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        try {
            session.createQuery("UPDATE Task SET done = :fDone WHERE id = :fId")
                    .setParameter("fDone", true)
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public List<Task> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> result = session.createQuery("from Task").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Task> findByDone(boolean isDone) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<Task> query = session.createQuery("FROM Task WHERE done = :fDone")
                .setParameter("fDone", isDone);
        List<Task> rsl = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    public Optional<Task> findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<Task> query = session.createQuery("from Task i where i.id = :fId")
                .setParameter("fId", id);
        Optional<Task> result = query.uniqueResultOptional();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
