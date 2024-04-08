package fr.mowitnow.lawnmower.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.mowitnow.lawnmower.exception.InvalidFileFormatException;
import fr.mowitnow.lawnmower.model.Mower;
import java.util.List;
import org.junit.jupiter.api.Test;

class UtilTest {

  @Test
  void testReadValidFile() throws InvalidFileFormatException {
    String filename = "input.txt";
    List<Mower> mowers = Util.read(filename);

    // Vérifier si la liste des tondeuses n'est pas vide
    assertFalse(mowers.isEmpty());

    // Vérifier les données de la première tondeuse
    Mower firstMower = mowers.get(0);
    assertEquals(1, firstMower.getX());
    assertEquals(2, firstMower.getY());
    assertEquals('N', firstMower.getOrientation());
    assertEquals("GAGAGAGAA", firstMower.getInstructions());

    // Vérifier les données de la deuxième tondeuse
    Mower secondMower = mowers.get(1);
    assertEquals(3, secondMower.getX());
    assertEquals(3, secondMower.getY());
    assertEquals('E', secondMower.getOrientation());
    assertEquals("AADAADADDA", secondMower.getInstructions());
  }

  @Test
  void testReadEmptyFile() {
    String filename = "empty_file.txt";
    assertThrows(InvalidFileFormatException.class, () -> Util.read(filename));
  }

  @Test
  void testReadInvalidDimension() {
    String filename = "invalid_dimension.txt";
    assertThrows(InvalidFileFormatException.class, () -> Util.read(filename));
  }
}

