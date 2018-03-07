package com.beerhouse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.model.Beer;

@RestController
public class BeerController {
	List<Beer> listaCervejas = new ArrayList<Beer>();

	public BeerController() {
		super();
		Beer cerveja = new Beer(1L, "Skol", "milho", "17%", 6.0, "A");
		listaCervejas.add(cerveja);
		listaCervejas.add(new Beer(2L, "Itaipava", "trigo", "20%", 3.0, "E"));
		listaCervejas.add(new Beer(3L, "Brahma", "malte", "13%", 5.0, "B"));
	}

	@RequestMapping(value = "/beers", method = RequestMethod.GET)
	public ResponseEntity<List<Beer>> getCervejas() {
		return new ResponseEntity<List<Beer>>(listaCervejas, HttpStatus.OK);
	}

	@RequestMapping(value = "/beers/{id}", method = RequestMethod.GET)
	public ResponseEntity<Beer> getCervejaById(@PathVariable Long id) {
		for (int i = 0; i < listaCervejas.size(); i++) {
			if (listaCervejas.get(i).getId() == id) {
				return new ResponseEntity<Beer>(listaCervejas.get(i), HttpStatus.OK);
			}
		}
		return null;
	}

	// http://www.baeldung.com/spring-requestmapping

	@RequestMapping(value = "/beers/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCervejaById(@PathVariable Long id) {
		for (int i = 0; i < listaCervejas.size(); i++) {
			if (listaCervejas.get(i).getId() == id) {
				listaCervejas.remove(i);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}
		return null;
	}

	@RequestMapping(value = "/beers", method = RequestMethod.POST)
	public ResponseEntity<Beer> postCerveja(@RequestBody Beer cerveja) {
		listaCervejas.add(cerveja);
		return new ResponseEntity<Beer>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/beers/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Beer> putCerveja(@PathVariable long id, @RequestBody Beer cerveja) {

		for (int i = 0; i < listaCervejas.size(); i++) {
			if (listaCervejas.get(i).getId() == id) {
				listaCervejas.get(i).setName(cerveja.getName());
				listaCervejas.get(i).setAlcoholContent(cerveja.getAlcoholContent());
				listaCervejas.get(i).setIngredients(cerveja.getIngredients());
				listaCervejas.get(i).setPrice(cerveja.getPrice());
				listaCervejas.get(i).setCategory(cerveja.getCategory());
			}
		}
		return new ResponseEntity<Beer>(HttpStatus.OK);
	}

}
