package ru.geel.fastest.mvc.orm;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.geel.fastest.mvc.bean.User;
import ru.geel.fastest.mvc.entity.Event;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ivangeel on 25.03.17.
 */

@Repository
@Transactional
public class ORMService {

    @PersistenceContext
    private EntityManager entityManager;

    public int createEvent(Event event){
        System.out.println("createEvent is called");

        String qStr = "insert into event (creator, eventName, description, cover, " +
                "ageMIN, ageMAX, amount, date, place) values(?,?,?,?,?,?,?,?,?)";
        Query query = entityManager.createNativeQuery(qStr);
        query.setParameter(1, event.getCreator());
        query.setParameter(2, event.getEventName());
        query.setParameter(3, event.getDescription());
        query.setParameter(4, event.getCover());
        query.setParameter(5, event.getAgeMIN());
        query.setParameter(6, event.getAgeMAX());
        query.setParameter(7, event.getAmount());
        query.setParameter(8, event.getCalendar());
        query.setParameter(9, event.getPlace());

        int result = query.executeUpdate();

        return result;
    }

    public int addEventParticipant(int idEvent, int idParticipant){
        String qString = "insert into eventParticipants (idEvent, idUsername) values(?,?)";
        Query query = entityManager.createNativeQuery(qString);
        query.setParameter(1, idEvent);
        query.setParameter(2, idParticipant);
        int result = query.executeUpdate();
        return result;
    }

    public int deleteEventParticipant(int idEvent, int idParticipant){
        String qString = "delete from eventParticipants where idEvent=? and idUsername=?";
        Query query = entityManager.createNativeQuery(qString);
        query.setParameter(1, idEvent);
        query.setParameter(2, idParticipant);
        int result = query.executeUpdate();
        return result;
    }

    public Event findEventById (int id) {
        System.out.println("findEventById is called");
        return entityManager.find(Event.class, id);
    }

    public List<Event> findAllEvents () {
        System.out.println("findAllEvent is called");
        String qSQL = "from Event order by id";
        TypedQuery<Event> typedQuery = entityManager.createQuery(qSQL, Event.class);
        return typedQuery.getResultList();
    }

    public int selectLastEventId(){
        return (Integer) entityManager.createNativeQuery("select max(id) from event").getSingleResult();
    }
}
