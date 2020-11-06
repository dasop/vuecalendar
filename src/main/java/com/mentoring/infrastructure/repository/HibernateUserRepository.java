package com.mentoring.infrastructure.repository;

import com.mentoring.domain.model.event.Event;
import com.mentoring.domain.model.event.EventRepository;
// import org.hibernate.query.Query;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class HibernateUserRepository extends HibernateSupport implements EventRepository {

  public HibernateUserRepository(EntityManager entityManager) {
    super(entityManager);
  }

  // @Override
  // public User findByUsername(String username) {
  //   Query<User> query = getSession().createQuery("from User where username = :username", User.class);
  //   query.setParameter("username", username);
  //   return query.uniqueResult();
  // }

  // @Override
  // public User findByEmailAddress(String emailAddress) {
  //   Query<User> query = getSession().createQuery("from User where emailAddress = :emailAddress", User.class);
  //   query.setParameter("emailAddress", emailAddress);
  //   return query.uniqueResult();
  // }

  @Override
  public void save(Event event) {
    entityManager.persist(event);
    entityManager.flush();
  }

  @Override
  public List<Event> getevents(String start, String end) {
    String sql =
      " SELECT * FROM calendar" +
      " WHERE start >= :start And end <= :end ";

    NativeQuery<Event> query = getSession().createNativeQuery(sql, Event.class);
    query.setParameter("start", start);
    query.setParameter("end", end);
    return query.list();
  }
}