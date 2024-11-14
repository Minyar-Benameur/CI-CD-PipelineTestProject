package org.springframework.samples.petclinic.owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OwnerTest {

	private Owner owner;

	private Pet charlie;

	@BeforeEach
	public void setUp() {
		owner = new Owner();

		charlie = Mockito.mock(Pet.class);

		when(charlie.getName()).thenReturn("Charlie");
		when(charlie.isNew()).thenReturn(true);

		owner.addPet(charlie);
	}

	// Tests pour la méthode getPet(String name, boolean ignoreNew)

	@Test
	public void testGetPet_IgnoreNewFalse_PetIsNew() {
		when(charlie.isNew()).thenReturn(true); // Charlie est un nouveau pet
		Pet foundPet = owner.getPet("Charlie", false);
		assertNotNull(foundPet); // Le pet doit être retourné
		assertEquals("Charlie", foundPet.getName());
	}

	@Test
	public void testGetPet_IgnoreNewTrue_PetIsNew() {
		when(charlie.isNew()).thenReturn(true); // Charlie est un nouveau pet
		Pet foundPet = owner.getPet("Charlie", true);
		assertNull(foundPet); // Le pet ne doit pas être retourné
	}

	@Test
	public void testGetPet_IgnoreNewTrue_PetIsNotNew() {
		when(charlie.isNew()).thenReturn(false); // Charlie n'est pas un nouveau pet
		Pet foundPet = owner.getPet("Charlie", true);
		assertNotNull(foundPet); // Le pet doit être retourné
		assertEquals("Charlie", foundPet.getName());
	}

	@Test
	public void testGetPet_IgnoreNewFalse_PetIsNotNew() {
		when(charlie.isNew()).thenReturn(false); // Charlie n'est pas un nouveau pet
		Pet foundPet = owner.getPet("Charlie", false);
		assertNotNull(foundPet); // Le pet doit être retourné
		assertEquals("Charlie", foundPet.getName());
	}

	@Test
	public void testGetPetByName_NotFound() {
		Pet foundPet = owner.getPet("NonExistentPet", false);
		assertNull(foundPet);
	}

	@Test
	public void testGetPetByName_NullName() {
		Pet foundPet = owner.getPet("", false);
		assertNull(foundPet);
	}

}
