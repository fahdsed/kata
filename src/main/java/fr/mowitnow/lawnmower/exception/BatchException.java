package fr.mowitnow.lawnmower.exception;

import fr.mowitnow.lawnmower.enums.ErrorEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class BatchException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -2945764093858796458L;

    private final ErrorEnum errorEnum;

}
