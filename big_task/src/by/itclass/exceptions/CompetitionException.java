package by.itclass.exceptions;

import lombok.Getter;

public class CompetitionException extends Exception{
    @Getter
    private final String errorLine;

    public CompetitionException(Throwable cause, String errorLine) {
        super(cause);
        this.errorLine = errorLine;
    }
}
