package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;
import static org.mockito.Mockito.*;

class PetValidatorTest {

	private PetValidator petValidator;

	private Errors errors;

	private Pet pet;

	@BeforeEach
	public void setup() {
		petValidator = new PetValidator();
		errors = mock(Errors.class);
		pet = new Pet();
	}

	@Test
	public void testValidate_NameIsEmpty_ShouldRejectValue() {
		// Pet sans nom
		pet.setName(""); // nom vide

		// Valider le pet
		petValidator.validate(pet, errors);

		// Vérifier que la validation a échoué pour le champ "name"
		verify(errors).rejectValue("name", "required", "required");
	}

	@Test
	public void testValidate_NameIsValid_ShouldNotRejectValue() {
		// Arrange: Pet avec un nom valide
		pet.setName("Buddy");

		// Act: Valider le pet
		petValidator.validate(pet, errors);

		// Assert: Aucun appel à rejectValue pour "name" ne devrait se produire
		verify(errors, never()).rejectValue(eq("name"), anyString(), anyString());
	}

}
