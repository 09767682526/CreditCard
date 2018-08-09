package card.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import card.model.CreditCard;
import card.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("creditCardService")
@Transactional
public class CreditCardService {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all credit cards
	 */
	public List<CreditCard> getAll(Integer personId) {
				
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Person person=(Person)session.get(Person.class,personId);
		
		// Retrieve all
		return  new ArrayList<CreditCard>(person.getCreditCards());
	}
	
	/**
	 * Retrieves all credit cards
	 */
	public List<CreditCard> getAll() {
				
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(CreditCard.class).list();
	}
	
	/**
	 * Retrieves a single credit card
	 */
	public CreditCard get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing credit card
		CreditCard creditCard = (CreditCard) session.get(CreditCard.class, id);
		
		// Persists to db
		return creditCard;
	}
	
	/**
	 * Adds a new credit card
	 */
	public void add(Integer personId, CreditCard creditCard) {
				
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
	
		// Persists to db
		session.save(creditCard);
		
		// Add to person as well
		// Retrieve existing person via id
		Person existingPerson = (Person) session.get(Person.class, personId);
		
		// Assign updated values to this person
		existingPerson.getCreditCards().add(creditCard);

		// Save updates
		session.save(existingPerson);
	}
	
	/**
	 * Deletes an existing credit card
	 */
	public void delete(Integer id) {
			
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
    	// Delete reference to foreign key credit card first
		// We need a SQL query instead of HQL query here to access the third table
    	Query query = session.createSQLQuery("DELETE FROM PERSON_CREDIT_CARD " +
    			"WHERE creditCards_ID="+id);
    	
    	query.executeUpdate();
    	
		// Retrieve existing credit card
		CreditCard creditCard = (CreditCard) session.get(CreditCard.class, id);
		
		// Delete 
		session.delete(creditCard);
	}	
	
	
	/**
	 * Edits an existing credit card
	 */
	public void edit(CreditCard creditCard) {
			
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Save updates
		session.merge(creditCard);
	}
}
