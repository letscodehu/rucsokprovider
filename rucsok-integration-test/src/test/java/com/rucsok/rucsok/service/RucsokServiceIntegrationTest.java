package com.rucsok.rucsok.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.repository.dao.RucsokRepository;
import com.rucsok.rucsok.repository.domain.RucsokEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
public class RucsokServiceIntegrationTest {

	private static final int PAGE_NUMBER_THAT_LARGER_THAN_THE_DATA_SIZE = 10;
	
	@Value("${rucsok.fresh.page.size}")
	public int FRESH_PAGINATION_SIZE;

	@Value("${rucsok.hot.page.size}")
	public int HOT_PAGINATION_SIZE;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Autowired
	private RucsokService underTest;

	@Autowired
	private RucsokRepository rucsokRepository;

	@Test
	public void findFreshShouldReturnLimitedNumberOfRucsoksWithCorrectOrder() {
		// Given
		List<RucsokEntity> rucsoks = rucsokRepository.getAllRucsok();
		RucsokEntity lastRucsok = Collections.max(rucsoks, Comparator.comparing(c -> c.getCreatedAt()));
		// When
		List<Rucsok> result = underTest.findFresh(0);
		// Then
		Assert.assertEquals("Limit size", FRESH_PAGINATION_SIZE, result.size());
		Assert.assertEquals("Last element", lastRucsok.getId(), result.get(0).getId());
		Assert.assertTrue("First has larger date than Second", hasLargerDate(result.get(1), result.get(0)));
		Assert.assertTrue("Second has larger date than Third", hasLargerDate(result.get(2), result.get(1)));
	}

	private boolean hasLargerDate(Rucsok a, Rucsok b) {
		return a.getCreatedAt().compareTo(b.getCreatedAt()) == -1;
	}

	@Test
	public void findFreshShouldReturnEmptyResultSet() {
		// Given
		// When
		List<Rucsok> result = underTest.findFresh(PAGE_NUMBER_THAT_LARGER_THAN_THE_DATA_SIZE);
		// Then
		Assert.assertEquals(0, result.size());
	}

	@Test
	public void findFreshShouldThrowIllegalArgumentException() {
		// Given
		expectedException.expect(IllegalArgumentException.class);
		// When
		underTest.findFresh(-1);
		// Then
	}
}
