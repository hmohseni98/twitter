package CustomException;

public class CanNotFollowYourAccount extends RuntimeException{
    public CanNotFollowYourAccount() {
        super("can not follow your account!");
    }
}
