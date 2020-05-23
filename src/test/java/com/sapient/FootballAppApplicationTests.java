package com.sapient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sapient.exception.InvalidPeopleException;
import com.sapient.service.PeopleService;

@SpringBootTest
class FootballAppApplicationTests {
	@Autowired
	private PeopleService peopleService;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void whenGivenValidData_thenReturnMaxPeopleLived() throws InvalidPeopleException{
		Integer[] birthYears= {1915,1900,1945,1985,1910};
		Integer[] deathYears= {1958,1970,2000,2030,2005};
		
		int expectedYear=1945;
		assertEquals(expectedYear, peopleService.getYearWhenMaxPeopleLived(birthYears, deathYears));
	}
	
	@Test
	public void whenGivenDataExceedsLimit_thenReturnException() throws InvalidPeopleException{
		Integer[] birthYears= {1915,1900,1945,1985,1910,1915,1900,1945,1985,1910,1915,1900,1945,1985,1910,1915,1900,1945,1985,1910};
		Integer[] deathYears= {1958,1970,2000,2030,2005,1915,1900,1945,1985,1910,1915,1900,1945,1985,1910,1915,1900,1945,1985,1910};
		assertThrows(InvalidPeopleException.class, () -> {
			peopleService.getYearWhenMaxPeopleLived(birthYears, deathYears);
		});
	}
	
	@Test
	public void whenGivenInvalidData_thenReturnException() throws InvalidPeopleException{
		Integer[] birthYears= {1915,1900,1945};
		Integer[] deathYears= {1958,1970,2000,2030,2005};
		assertThrows(InvalidPeopleException.class, () -> {
			peopleService.getYearWhenMaxPeopleLived(birthYears, deathYears);
		});
	}
	

}
