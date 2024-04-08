package fr.mowitnow.lawnmower.writer;


import fr.mowitnow.lawnmower.exception.BatchErrorEnum;
import fr.mowitnow.lawnmower.exception.BatchException;
import fr.mowitnow.lawnmower.model.Mower;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public record OutputWriter() implements ItemWriter<Mower> {

  @Override
  public void write(Chunk<? extends Mower> chunk) {

    var outputFile = "result.txt";

    try (var writer = new PrintWriter(new FileWriter(outputFile))) {
      chunk.getItems().stream().forEach(mower -> {
        String outputLine = mower.getX() + " " + mower.getY() + " " + mower.getOrientation();
        writer.println(outputLine);
      });
    } catch (IOException e) {
      throw new BatchException(BatchErrorEnum.WRITE_FILE_ERROR);
    }



  }
}