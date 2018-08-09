package card.controller;

import card.model.CreditCard;
import card.dao.CreditCardService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/creditcard")
public class CreditCardController {
	
	@Autowired
	private CreditCardService creditCardService;
	
    /**
     * Retrieves the "Add New Credit Card" page
     */
    @RequestMapping(value = "/addCreditCard", method = RequestMethod.GET)
    public String getAdd(@RequestParam("id") Integer personId, Map<String,Object> map) {
    	        	
    	map.put("personId", personId);

    	// This will resolve to /WEB-INF/jsp/add-credit-card.jsp
    	return "add-credit-card";
	}
 
    /**
     * Adds a new credit card
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(@RequestParam("id") Integer personId, 
    						    @ModelAttribute("creditCard") CreditCard creditCard) {
			
		// Delegate to service
		creditCardService.add(personId, creditCard);

		// Redirect to url
		return "redirect:/list";
	}
    
    
    /**
     * Deletes a credit card
     */
    @RequestMapping(value = "/delete")
    public String getDelete(@RequestParam("id") Integer creditCardId) {
       	
    	// Delegate to service
		creditCardService.delete(creditCardId);

		// Redirect to url
		return "redirect:/list";
	}
   
    /**
     * Retrieves the "Edit Existing Credit Card" page
     */
    @RequestMapping(value = "/editCreditCard")
    public String getEdit(@RequestParam("pid") Integer personId, 
    		@RequestParam("cid") Integer creditCardId, Map<String,Object> map) {
      	
    	// Retrieve credit card by id
    	CreditCard existingCreditCard = creditCardService.get(creditCardId);
    	
    	map.put("personId", personId);
    	map.put("existingCreditCard", existingCreditCard);

    	// This will resolve to /WEB-INF/jsp/edit-credit-card.jsp
    	return "edit-credit-card";
	}
 
    /**
     * Edits an existing credit card
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEdit(@ModelAttribute("creditCardAttribute") CreditCard creditCard, BindingResult result) {
		
		// Delegate to service
		creditCardService.edit(creditCard);

		// Redirect to url
		return "redirect:/list";
	}
}
