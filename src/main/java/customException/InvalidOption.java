package customException;

public class InvalidOption extends RuntimeException {

    public InvalidOption() {
        super("invalid option");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
