package fr.mowitnow.lawnmower.batch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import fr.mowitnow.lawnmower.exception.BatchErrorEnum;
import fr.mowitnow.lawnmower.exception.BatchException;
import fr.mowitnow.lawnmower.model.Mower;
import fr.mowitnow.lawnmower.writer.OutputWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;

class OutputWriterTest {

  private OutputWriter outputWriter;

  @BeforeEach
  void setUp() {
    outputWriter = new OutputWriter();
  }

  @Test
  void testWrite() {
    // Création d'une liste de tondeuses pour écrire dans le fichier
    List<Mower> mowers = new ArrayList<>();
    mowers.add(new Mower(1, 2, 'N', ""));
    mowers.add(new Mower(3, 4, 'E', ""));
    mowers.add(new Mower(5, 6, 'S', ""));

    // Création d'un chunk contenant les tondeuses à écrire
    Chunk<Mower> chunk = new Chunk<>(mowers);

    // Exécution de la méthode de test
    outputWriter.write(chunk);

    // Vérification de l'existence du fichier créé
    Path of = Path.of("result.txt");
    assertTrue(Files.exists(of));

    // Vérification du contenu du fichier
    try {
      List<String> lines = Files.readAllLines(of);
      assertEquals("1 2 N", lines.get(0));
      assertEquals("3 4 E", lines.get(1));
      assertEquals("5 6 S", lines.get(2));
    } catch (IOException e) {
      fail("Failed to read the output file.");
    }

    // Suppression du fichier créé après les tests
    File file = new File("result.txt");
    if (file.exists()) {
      file.delete();
    }
  }

}

