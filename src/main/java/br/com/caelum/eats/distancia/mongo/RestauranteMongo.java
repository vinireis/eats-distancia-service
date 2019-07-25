package br.com.caelum.eats.distancia.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Document
@Data
@AllArgsConstructor
@Builder
@ToString
public class RestauranteMongo {
	@Id 
	private Long id;

	private String cep;
	
	private Boolean aprovado;

	private Long tipoDeCozinhaId;
}