package tv.wanzami.exception;

/**
 * Customs Entities DuplicateEntryException
 */
public class DuplicateEntryException extends RuntimeException {
    private static final long serialVersionUID = 1153736451942481446L;

	public DuplicateEntryException(Throwable cause) {
        super(cause);
    }
}

