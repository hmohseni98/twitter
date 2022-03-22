package CustomException;

public class AlreadyPointThatTweet extends RuntimeException{
    public AlreadyPointThatTweet() {
        super("you already like or dislike that tweet , first remove it and try again!");
    }
}
