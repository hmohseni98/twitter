package customException;

public class TweetLengthOutOfRange extends RuntimeException {


    public TweetLengthOutOfRange() {
        super("tweet length out of range");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
