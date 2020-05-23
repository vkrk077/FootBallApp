package com.sapient.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.sapient.exception.InvalidPeopleException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PeopleService {
	private static int MAX_LIMIT=15;
	
	//Returns year at Max People Lived for valid input otherwise throws Exception
	public Integer getYearWhenMaxPeopleLived(Integer[] birthYears,Integer[] deathYears) throws InvalidPeopleException{
		int yearAtMaximumPeopleLived=0;
		int currentPeopleLived=0;
		int currentYear=0;
		int maxPeopleLived=0;
		int birthCount=0;
		int deathCount=0;
		
		validatePeopleList(birthYears,deathYears);
		
		//Arrays are sorted to check the people lived from the list of birth years 
		Arrays.sort(birthYears);
		Arrays.sort(deathYears);
		
		while(birthCount<birthYears.length || deathCount<deathYears.length) {
			if(birthCount<birthYears.length && birthYears[birthCount] < deathYears[deathCount]) {
				//This case is used to check the number of people living
				
				currentYear=birthYears[birthCount];
				
				birthCount++;
				currentPeopleLived++;
				
				if(maxPeopleLived<currentPeopleLived) {
					maxPeopleLived=currentPeopleLived;
					yearAtMaximumPeopleLived=currentYear;
				}
			}
			else {
				deathCount++;
				currentPeopleLived--;
			}
		}
		
		return yearAtMaximumPeopleLived;
	}

	private void validatePeopleList(Integer[] birthYears, Integer[] deathYears) throws InvalidPeopleException {
		log.info("Validating input data");
		
		if(birthYears.length>MAX_LIMIT || deathYears.length>MAX_LIMIT || birthYears.length==0 || deathYears.length==0 || birthYears.length!=deathYears.length) {
			log.error("Validation failed for input data");
			throw new InvalidPeopleException("Validation failed for input data");
		}
		
		log.info("Validation successful for input data");
	}

}
