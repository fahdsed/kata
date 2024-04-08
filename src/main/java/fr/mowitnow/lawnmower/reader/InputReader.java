package fr.mowitnow.lawnmower.reader;

import fr.mowitnow.lawnmower.exception.BatchErrorEnum;
import fr.mowitnow.lawnmower.exception.BatchException;
import fr.mowitnow.lawnmower.model.Mower;
import fr.mowitnow.lawnmower.util.Util;
import java.util.List;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;

public class InputReader {

  private InputReader() {
    throw new IllegalStateException("Utility class");
  }

  public static ItemReader<Mower> createFlatFileItemReader() {
    List<Mower> mowers;
    try {
      mowers = Util.read("input.txt");
    } catch (Exception e) {
      throw new BatchException(BatchErrorEnum.READING_CONFIGURATION_ERROR);
    }
    return new ListItemReader<>(mowers);
  }


}
