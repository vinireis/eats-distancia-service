package br.com.caelum.eats.distancia;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.caelum.eats.distancia.exception.ResourceNotFoundException;
import br.com.caelum.eats.distancia.mongo.RestauranteMongo;
import br.com.caelum.eats.distancia.mongo.RestauranteMongoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class RestaurantesController {
	private RestauranteMongoRepository repo;
	
	@PostMapping("/restaurantes")
	public ResponseEntity<RestauranteMongo> atualiza(@RequestBody RestauranteMongo restaurante, UriComponentsBuilder uriBuilder) {
		log.info("Insere novo restaurante: " + restaurante);
		RestauranteMongo restauranteSalvo = repo.insert(restaurante);
		UriComponents uriComponents = uriBuilder.path("restaurantes/{id}").buildAndExpand(restauranteSalvo.getId());
		
		URI uri = uriComponents.toUri();
		return ResponseEntity.created(uri).contentType(MediaType.APPLICATION_JSON).body(restauranteSalvo);
	}

	@PutMapping("/restaurantes/{id}")
	public RestauranteMongo atualiza(@PathVariable Long id, @RequestBody RestauranteMongo restaurante) {
		log.info("Insere novo restaurante: " + restaurante);
		if(!repo.existsById(id)) {
			throw new ResourceNotFoundException();
		}
		return repo.save(restaurante);
	}
}
