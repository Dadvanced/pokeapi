package com.pokeapi.dadvanced.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.pokeapi.dadvanced.models.Pokemon;
import com.pokeapi.dadvanced.services.PokeapiService;

@RestController
@RequestMapping("/app")
public class MainController {	
	
	
	@Autowired
	PokeapiService pokeapi;
	
	Gson gson = new Gson();
	
	@RequestMapping(value = "/pokemonlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getAllPokemon() throws IOException {
	    return ResponseEntity.ok(gson.toJson(pokeapi.getAllRedVersionPokemons()));
	}
	
	
	@RequestMapping(value = "/highest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getHighest() {
		return ResponseEntity.ok(gson.toJson(pokeapi.getHighestPokemon()));
	}
	
	
	@RequestMapping(value = "/heaviest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getHeaviest() {
		return ResponseEntity.ok(gson.toJson(pokeapi.getHeaviestPokemon()));
	}
	
	@RequestMapping(value = "/base", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getBaseExperience() {
		return ResponseEntity.ok(gson.toJson(pokeapi.getBaseExperiencePokemon()));
	}
	
}
