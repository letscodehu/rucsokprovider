package com.rucsok.user.service.transform;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.user.domain.UserRegistration;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.transform.UserRegistrationServiceTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserRegistrationServiceTransformer.class)
public class UserRegistrationTransformerTest {

	private String username = "testname";
	private String password = "testpassword";
	private String email = "testemail";

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Autowired
	private UserRegistrationServiceTransformer transformer;

	@Test
	public void transformFromRegistrationShouldTransformFromRegistration() {
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
	public void transformFromEntityShouldTransformFromEntity() {
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
	public void transformFromEntityShouldThrowIllegalArgument_On_NullEntity() {
		// GIVEN
		expectedEx.expect(IllegalArgumentException.class);
		// WHEN
		transformer.transformFromEntity(null);

		// THEN
	}

	@Test
	public void shouldThrowIllegalArgument_On_NullRegistration() {
		// GIVEN
		expectedEx.expect(IllegalArgumentException.class);
		// WHEN

		transformer.transformFromRegistration(null);
		// THEN
	}

}
