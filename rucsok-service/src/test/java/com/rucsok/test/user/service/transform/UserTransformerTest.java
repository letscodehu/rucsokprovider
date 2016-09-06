package com.rucsok.test.user.service.transform;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;

import com.rucsok.user.domain.User;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.transform.UserTransformer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = UserTransformer.class)
public class UserTransformerTest {

	@Autowired
	private UserTransformer userTransformer;
	
	private String name = "testname";
	
	private String email = "testemail";
	
	@Test
	public void shouldTransformFromEntity() {
		// GIVEN
		final UserEntity entity = new UserEntity(name, email);
		// WHEN

		final User result = userTransformer.transformEntityToUser(entity);
		
		// THEN
		Assert.assertEquals(result.getEmail(), email);
		Assert.assertEquals(result.getUsername(), name);
		
	}
	
	@Test
	public void shouldTransformToEntity() {
		// GIVEN
		final User user = new User(email, name);
		
		// WHEN
		final UserEntity entity = userTransformer.transformUserToEntity(user);

		// THEN
		Assert.assertEquals(entity.getEmail(), email);
		Assert.assertEquals(entity.getName(), name);
	}

}
