package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.List;

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

    public void update(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(task);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(task);
        session.getTransaction().commit();
        session.close();
    }

    public void setTaskIsDone(Task task) {
        Session session = sf.openSession();
        session.beginTransaction();
        task.setDone(true);
        session.update(task);
        session.getTransaction().commit();
        session.close();
    }

    public List<Task> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> result = session.createQuery("from Task").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Task> findNew() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> result = session.createQuery("from Task i where i.done = false").list();
        session.close();
        return result;
    }

    public List<Task> findCompleted() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Task> result = session.createQuery("from Task i where i.done = true").list();
        session.close();
        return result;
    }

    public Task findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Task i where i.id = :fId")
                .setParameter("fId", id);
        Task result = (Task) query.uniqueResult();
        session.close();
        return result;
    }
}
