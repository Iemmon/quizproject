package com.finalproject.quizsystem.config;

import com.finalproject.quizsystem.controller.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PassconfValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @Test
    public void isValidShouldReturnTrue() {
        UserDTO user = new UserDTO();
        user.setEmail("someemail@gmail.com");
        user.setPassword("password");
        user.setPassconf("password");

        PassconfValidator validator = new PassconfValidator();
        assertTrue(validator.isValid(user, context));

    }

    @Test
    public void isValidShouldReturnFalseAsPasswordsNotEqual() {
        UserDTO user = new UserDTO();
        user.setEmail("someemail@gmail.com");
        user.setPassword("password");
        user.setPassconf("pass");

        ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext customizableContext = mock(ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext.class);

        when(context.getDefaultConstraintMessageTemplate()).thenReturn("");
        when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(violationBuilder);
        when(violationBuilder.addPropertyNode(anyString())).thenReturn(customizableContext);

        PassconfValidator validator = new PassconfValidator();

        assertFalse(validator.isValid(user, context));
    }
}