package com.rucsok.test.user.service.transform;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.user.domain.UserRegistration;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.transform.UserRegistrationTransformer;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserRegistrationTransformer.class)
public class UserRegistrationTransformerTest {

	private String username = "testname";
	private String password = "testpassword";
	private String email = "testemail";
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Autowired
	private UserRegistrationTransformer transformer;
	
	
	@Test
	public void shouldTransformFromRegistration() {
		// GIVEN
		final UserRegistration registration = new UserRegistration(email, username, password);
		// WHEN

		UserEntity entity = transformer.transformFromRegistration(registration);
		
		// THEN
		Assert.assertEquals(username, entity.getName());
		Assert.assertEquals(email, entity.getEmail());
		Assert.assertEquals(password, entity.getPassword());
	}
	
	
	@Test
	public void shouldTransformFromEntity() {
		// GIVEN
		final UserEntity entity = new UserEntity(username, email, password);
		
		// WHEN
		
		UserRegistration reg = transformer.transformFromEntity(entity);

		// THEN
		Assert.assertEquals(username, reg.getUsername());
		Assert.assertEquals(email, reg.getEmail());
		Assert.assertEquals(password, reg.getPassword());
		
	}
	
	@Test
	public void shouldThrowIllegalArgumentOnNullEntity() {
		// GIVEN
		expectedEx.expect(IllegalArgumentException.class);
		final UserEntity entity = null;
		// WHEN
		transformer.transformFromEntity(entity);
		
		// THEN 
	}
	
	@Test
	public void shouldThrowIllegalArgumentOnNullRegistration() {
		// GIVEN
		expectedEx.expect(IllegalArgumentException.class);
		final UserRegistration registration = null;
		// WHEN

		transformer.transformFromRegistration(null);
		// THEN
	}

}

