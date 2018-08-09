package card.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import card.model.CreditCard;
import card.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("personService")
@Transactional
public class PersonService {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * Retrieves all persons
	 * 
	 * @return a list of persons
	 */
	public List<Person> getAll() {
			
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Person.class).list();
		
	}
	
	/**
	 * Retrieves a single person
	 */
	public Person get( Integer id ) {
		
		Session session = sessionFactory.getCurrentSession();		
		
		return(Person) session.get(Person.class,id);
	}
	
	/**
	 * Adds a new person
	 */
	public void add(Person person) {
			
		Session session = sessionFactory.getCurrentSession();
		
		session.save(person);
	}
	
	/**
	 * Deletes an existing person
	 * @param id the id of the existing person
	 */
	public void delete(Integer id) {
				
		Session session = sessionFactory.getCurrentSession();
		
		Person person=(Person)session.get(Person.class, id);
		
		Set<CreditCard> creditCards =person.getCreditCards();
		
		// Delete person
		session.delete(person);
		
		// Delete associated credit cards
		for (CreditCard creditCard: creditCards) {
			session.delete(creditCard);
		}
	}
		
	/**
	 * Edits an existing person
	 */
	public void edit(Person person) {
		
		Session session = sessionFactory.getCurrentSession();
		
		// Retrieve existing person via id
		Person existingPerson = (Person) session.get(Person.class, person.getId());
		
		// Assign updated values to this person
		existingPerson.setFirstName(person.getFirstName());
		existingPerson.setLastName(person.getLastName());
		existingPerson.setMoney(person.getMoney());

		// Save updates
		session.merge(existingPerson);
	}

}
