package com.rucsok.rucsok.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.service.exception.IllegalRucsokArgumentException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
public class ListedRucsokServiceIntegrationTest {
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Autowired
	private ListedRucsokService underTest;
	
	@Test
	public void itShouldReturnRucsokWithCorrectId() {
		// Given
		LocalDate date = LocalDate.of(2016, 8, 30);	
		// When
		List<Rucsok> result = underTest.getListedRucsok(date);
		// Then
		Assert.assertEquals("Limit size", ListedRucsokService.HOT_PAGINATION_SIZE, result.size());
		Assert.assertEquals("1st Id", 9L, result.get(0).getId());
		Assert.assertEquals("2nd Id", 8L, result.get(1).getId());
		Assert.assertEquals("3rd Id", 7L, result.get(2).getId());
	}
	
	@Test
	public void itShouldThrowIllegalRucsokArgumentException_When_DateIsNull() {
		// Given
		expectedException.expect(IllegalRucsokArgumentException.class);
		// When
		underTest.getListedRucsok(null);
		// Then
	}
	
}
