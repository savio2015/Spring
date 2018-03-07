import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.beerhouse.controller.*;
import com.beerhouse.model.Beer;

import junit.framework.Assert;

import static org.junit.Assert.*;


public class TestBeer {
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void shouldReturnBeers() {
		//given
		BeerController beerController = new BeerController();
		
		Beer c = new Beer(4L, "cerveja 04", "ingredientes 04", "alcoholContent 04",
				10.5, "category C"),
			d = new Beer(5L, "cerveja 05", "ingredientes 05", "alcoholContent 05",
					10.5, "category D"),
			e = new Beer(5L, "cerveja 06", "ingredientes 06", "alcoholContent 06",
					10.5, "category E");
		
		beerController.postCerveja(c);
		beerController.postCerveja(d);
		beerController.postCerveja(e);
		
		//when
		ResponseEntity<List<Beer>> beersReceived = beerController.getCervejas();
		
		//then
		Assert.assertTrue(beersReceived.getBody().contains(c));
		Assert.assertTrue(beersReceived.getBody().contains(d));
		Assert.assertTrue(beersReceived.getBody().contains(e));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void shouldReturnBeersById() {
		
		///given
		BeerController beerController = new BeerController();// Reduzir para um teste
		
		Beer c = new Beer(4L, "cerveja 04", "ingredientes 04", "alcoholContent 04",
				10.5, "category C");
			
		
		beerController.postCerveja(c);
		Long id = c.getId();
		//when
		ResponseEntity <Beer> beersReceived = beerController.getCervejaById(id);
		
		//then
		assertEquals(id,beersReceived.getBody().getId());
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void shouldReturnPostBeer() {
		//given
		BeerController beerController = new BeerController();
		
		Beer c = new Beer(10L, "cerveja 10", "ingredientes 02", "alcoholContent 28",
				7.5, "category H");
		
		beerController.postCerveja(c);
		
		//when
		ResponseEntity<List<Beer>> beersReceived = beerController.getCervejas();
		
		//then
		Assert.assertTrue(beersReceived.getBody().add(c));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void shouldReturnDeletetBeer() {
		//given
		BeerController beerController = new BeerController();
		
		Beer c = new Beer(3L, "cerveja 3", "ingredientes 03", "alcoholContent 22",
				3.5, "category B");
		
		beerController.postCerveja(c);
		
		//when
		ResponseEntity<List<Beer>> beersReceived = beerController.getCervejas();
		
		//then
		Assert.assertTrue(beersReceived.getBody().remove(c));
	}
	
	


}




