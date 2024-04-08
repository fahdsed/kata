package fr.mowitnow.lawnmower.exception;

import fr.mowitnow.lawnmower.enums.ErrorEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BatchErrorEnum implements ErrorEnum {

    BATCH_ERROR("error.batch_error", "Problème généré lors du lancement du batch."),
    WRITE_FILE_ERROR("error.write_file","Failed to write output file."),
    READING_CONFIGURATION_ERROR("error.redaing_configuration_file", "Error reading configuration file"),
    READ_FILE_ERROR("error.read_file","Failed to read file."),
    CREATE_DIRECTORY_ERROR("error.create_directory","Failed to create directory.");

    final String errorKey;
    final String errorMessage;
}
