package fr.mowitnow.lawnmower.batch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.mowitnow.lawnmower.exception.BatchException;
import fr.mowitnow.lawnmower.model.Mower;
import fr.mowitnow.lawnmower.reader.InputReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;

class InputReaderTest {

  @Test
  void testCreateFlatFileItemReader() {
    // Préparation des données de test
    List<Mower> mowers = List.of(
        new Mower(1, 2, 'N', "GAGAGAGAA"),
        new Mower(3, 3, 'E', "AADAADADDA")
    );

    // Utilisation de l'ItemReader créé par la méthode createFlatFileItemReader
    ItemReader<Mower> reader = InputReader.createFlatFileItemReader();

    // Vérification si le reader est une instance de ListItemReader
    assertTrue(reader instanceof ListItemReader);

    ListItemReader<Mower> listItemReader = (ListItemReader<Mower>) reader;
    List<Mower> mowersList = new ArrayList<>();
    Mower mower;
    while ((mower = listItemReader.read()) != null) {
      mowersList.add(mower);
    }
    // Vérification si les données du reader correspondent aux données préparées
    assertEquals(mowers, mowersList);
  }

}

