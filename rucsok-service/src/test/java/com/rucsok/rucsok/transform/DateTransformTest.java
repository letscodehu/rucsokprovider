package com.rucsok.rucsok.transform;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.rucsok.transform.config.TransformConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DateTransformer.class, TransformConfig.class })
public class DateTransformTest {
	
	@Rule
	public ExpectedException expectedException  = ExpectedException.none();

	@Autowired
	private DateTransformer underTest;
	
	@Test
	public void getCurrentDayShouldThrowNullpointerException_When_CalledWithNull() {
		// Given
		expectedException.expect(NullPointerException.class);
		// When
		underTest.getCurrentDay(null);
		// Then
	}
	
	@Test
	public void getCurrentDayShouldReturnCurrentDayAsString() {
		// Given
		LocalDate date = LocalDate.of(2016, 8, 30);
		// When
		String result = underTest.getCurrentDay(date);
		// Then
		Assert.assertEquals("2016-08-30", result);
	}
	
	@Test
	public void getNextDayShouldReturnNextDayAsString() {
		// Given
		LocalDate date = LocalDate.of(2016, 8, 30);
		// When
		String result = underTest.getNextDay(date);
		// Then
		Assert.assertEquals("2016-08-31", result);
	}
}
