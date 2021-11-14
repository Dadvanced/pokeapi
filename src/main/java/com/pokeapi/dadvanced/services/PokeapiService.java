package com.pokeapi.dadvanced.services;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.comparator.Comparators;
import org.springframework.web.client.RestTemplate;

import com.pokeapi.dadvanced.models.Pokemon;

@Component
public class PokeapiService {
	private final static Logger logger = Logger.getLogger(PokeapiService.class.getName()); 
	
	private static final String URL_API = "https://pokeapi.co/api/v2/pokemon/";
	
	
	public List<Pokemon> getAllRedVersionPokemons() {
		logger.info("getAllRedVersionPokemons --> START");
		
		RestTemplate rest = new RestTemplate();
		List<Pokemon> pokemonList = new ArrayList<>();
		
		for (int i = 1; i <= 151; i++) {
			ResponseEntity<String> responseEntity = rest.exchange(
					 URL_API + String.valueOf(i), 
					 HttpMethod.GET, 
					 createRequestEntity(createHeaders()), 
					 String.class);
			
			JSONObject myObject = new JSONObject(responseEntity.getBody());
			
			Pokemon pokemon = createPokemon(myObject);
			pokemonList.add(pokemon);
			
		}
		
		logger.info("getAllRedVersionPokemons --> END");			
		return pokemonList;
	}
	
	/**
	 * get the pokemon list order by highest weight
	 * @return List<Pokemon>
	 */
	public List<Pokemon> getHighestPokemon() {
		final List<Pokemon> pokemonList = getAllRedVersionPokemons();
		Collections.sort(pokemonList);
		List<Pokemon> pokemonFiltered = new ArrayList<>();
		
		int i = 0;
		for (Pokemon p : pokemonList) {
			pokemonFiltered.add(p);
			i++;
			
			if (i == 5) {
				break;
			}
		}
		
		return pokemonFiltered;
	}
	
	/**
	 * get the pokemon list order by heaviest weight
	 * @return List<Pokemon>
	 */
	public List<Pokemon> getHeaviestPokemon() {
		final List<Pokemon> pokemonList = getAllRedVersionPokemons();
		Collections.sort(pokemonList);
		Collections.reverse(pokemonList);
		List<Pokemon> pokemonFiltered = new ArrayList<>();
		
		int i = 0;
		for (Pokemon p : pokemonList) {
			pokemonFiltered.add(p);
			i++;
			
			if (i == 5) {
				break;
			}
		}
		
		return pokemonFiltered;
	}
	
	/**
	 * get the list of pokemons order by base experience
	 * @return List<Pokemon>
	 */
	public List<Pokemon> getBaseExperiencePokemon() {
		final List<Pokemon> pokemonList = getAllRedVersionPokemons();
		Collections.sort(pokemonList, Pokemon.Comparators.baseExperience);
		Collections.reverse(pokemonList);
		List<Pokemon> pokemonFiltered = new ArrayList<>();
		
		int i = 0;
		for (Pokemon p : pokemonList) {
			pokemonFiltered.add(p);
			i++;
			
			if (i == 5) {
				break;
			}
		}
		
		return pokemonFiltered;		
	}
	
	private HttpURLConnection opeConnection(URL url) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");		
		
		return con;
	}
	
	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
	    headers.add("Accept", "*/*");	  
	    
	    return headers;
	}
	
	private HttpEntity<String> createRequestEntity(HttpHeaders headers) {
		return new HttpEntity<String>("", headers);
	}
	
	/**
	 * create a Pokemon object from JSONObject
	 * @param myObject
	 * @return Pokemon
	 */
	private Pokemon createPokemon(JSONObject myObject) {		
			Pokemon pokemon = Pokemon.builder()
					.name(myObject.get("name").toString())
					.id(myObject.get("id").toString())
					.baseExperience(myObject.get("base_experience").toString())
					.weight(myObject.get("weight").toString())
				.build();
		
		return pokemon;
	}
	
}
