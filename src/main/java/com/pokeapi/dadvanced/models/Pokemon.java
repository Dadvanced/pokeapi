package com.pokeapi.dadvanced.models;

import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonSerialize
@Builder
@Getter
@Setter
public class Pokemon implements Comparable<Pokemon> {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("base_experience")
	private String baseExperience;
	
	@JsonProperty("weight")
	private String weight;
	
	public String toString() {
		String s = "id: " + this.id + " - ";
		s += "name: " + this.name + " - ";
		s += "base experience: " + this.baseExperience + " - ";
		s += "weight: " + this.weight + " - ";
		
		return s;
	}

	/*
	@Override
	public int compareTo(Pokemon o) {
		
		if (Integer.parseInt(this.weight) < Integer.parseInt(o.weight)) {
			return -1;
		} else if (Integer.parseInt(this.weight) > Integer.parseInt(o.weight)) {
			return 1;
		}
		
		return 0;
	}
	*/
	
	@Override
	public int compareTo(Pokemon o) {		
		return Comparators.weight.compare(this,  o);
	}
	
	public static class Comparators {
		public static Comparator<Pokemon> baseExperience = new Comparator<Pokemon>() {
			@Override
			public int compare (Pokemon o1, Pokemon o2) {
				if (Integer.parseInt(o1.baseExperience) < Integer.parseInt(o2.baseExperience)) {
					return -1;
				} else if (Integer.parseInt(o1.baseExperience) > Integer.parseInt(o2.baseExperience)) {
					return 1;
				}
				
				return 0;
			}
		};
			
		public static Comparator<Pokemon> weight = new Comparator<Pokemon>() {
			@Override
			public int compare(Pokemon o1, Pokemon o2) {
				if (Integer.parseInt(o1.weight) < Integer.parseInt(o2.weight)) {
					return -1;
				} else if (Integer.parseInt(o1.weight) > Integer.parseInt(o2.weight)) {
					return 1;
				}
				
				return 0;
			}
		};
	}
	
}
