package card.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import card.model.Person;
import card.dto.PersonDTO;
import card.dao.CreditCardService;
import card.dao.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@Autowired
	private PersonService personService;	
	@Autowired
	private CreditCardService creditCardService;		
	
	/**
	 * Retrieves the "Records" page
	 */
	
	@RequestMapping(value = { "/", "/list" })  
    public String getRecords(Map<String,Object> map) {
        	
    	// Retrieve all persons
    	List<Person> personList = personService.getAll();
    	
    	// Prepare model object
    	List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();
    	
    	for (Person person: personList) {
    		// Create new data transfer object
    		PersonDTO dto = new PersonDTO();
    		
			dto.setId(person.getId());
			dto.setFirstName(person.getFirstName());
			dto.setLastName(person.getLastName());
			dto.setMoney(person.getMoney());
			dto.setCreditCards(creditCardService.getAll(person.getId()));
			
			// Add to model list
			personsDTO.add(dto);
    	}
    	
    	map.put("persons", personsDTO);

    	// This will resolve to /WEB-INF/jsp/records.jsp
		return "records";
	}
    
    /**
     *  Retrieves the "Add New Record" page
     */
    @RequestMapping(value = "/addNewRecord")
    public String getAdd() {	    
    	
    	// This will resolve to /WEB-INF/jsp/add-record.jsp
    	return "add-record";
	}
 
    /**
     * Adds a new record
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@ModelAttribute("person") Person person) {
	
		// Delegate to service
		personService.add(person);

		// Redirect to url
		return "redirect:/list";
	}
    
    /**
     * Deletes a record including all the associated credit cards
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer personId) {
        	
    	// Delete person
		personService.delete(personId);

		// Redirect to url
		return "redirect:/list";
	}
    
    /**
     * Retrieves the "Edit Existing Record" page
     */
    @RequestMapping(value = "/editRecord")
    public String getEdit(@RequestParam("id") Integer personId, Map<String, Object> map) {
       	
    	// Retrieve person by id
    	Person existingPerson = personService.get(personId);

    	// Add to model
    	map.put("currentPerson", existingPerson);

    	// This will resolve to /WEB-INF/jsp/edit-record.jsp
    	return "edit-record";
	}
 
    /**
     * Edits an existing record
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@ModelAttribute("person") Person person, BindingResult result) {
			
		// Delegate to service
		personService.edit(person);

		// Redirect to url
		return "redirect:/list";
	}    
}
