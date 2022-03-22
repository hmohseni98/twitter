package CustomException;

public class RecordDoesNotExist extends RuntimeException{

    public RecordDoesNotExist() {
        super("Record Does Not Exist");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
