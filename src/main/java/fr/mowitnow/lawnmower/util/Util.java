package fr.mowitnow.lawnmower.util;

import fr.mowitnow.lawnmower.exception.BatchErrorEnum;
import fr.mowitnow.lawnmower.exception.BatchException;
import fr.mowitnow.lawnmower.exception.InvalidFileFormatException;
import fr.mowitnow.lawnmower.model.Mower;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.ClassPathResource;

public class Util {

    Util(){}
    private static final String MOWER_LAWN_SIZE_REGEX = "^\\d+ \\d+$";
    private static final String CONSTRUCTIONS_REGEX = "[AGD]*";

    /**
     * read configuration from file
     * @return list of mowers from the file
     */
    public static List<Mower> read(String filename) throws InvalidFileFormatException {
        List<Mower> mowers = new ArrayList<>();
        var lines = readFile(filename);
        int countLines = lines.size();
        if (countLines == 0) {
            throw new InvalidFileFormatException("File is empty");
        }
        if (lines.get(0).matches(MOWER_LAWN_SIZE_REGEX)) {

            var countMowers = (countLines - 1) / 2;
            for (var i = 0; i < countMowers; i++) {
                var initialPosition = lines.get((2 * i + 1)).split(" ");
                var instructions = lines.get(2 * i + 2);
                if (instructions.matches(CONSTRUCTIONS_REGEX)){
                    mowers.add(new Mower(
                        Integer.parseInt(initialPosition[0]),
                        Integer.parseInt(initialPosition[1]),
                        initialPosition[2].charAt(0),
                        instructions));
                } else {
                    throw new InvalidFileFormatException("Commands must be A,G,D");
                }
            }
            return mowers;
        } else {
            throw new InvalidFileFormatException("Invalid dimension for lawn");
        }
    }


    /**
     * read file
     * @return the list of lines in the file
     */
    private static List<String> readFile(String filename) {
        try {
            ClassPathResource resource = new ClassPathResource(filename);
            Path filePath = Paths.get(resource.getURI());
            return Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new BatchException(BatchErrorEnum.READ_FILE_ERROR);
        }
    }
}
