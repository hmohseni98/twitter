import CustomException.*;
import Service.*;
import entity.*;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.NoResultException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Integer selectOption;
    private static final AccountService accountService = new AccountService();
    private static final TweetService tweetService = new TweetService();
    private static final CommentService commentService = new CommentService();
    private static final FollowService followService = new FollowService();
    private static final LikeService likeService = new LikeService();
    private static Account loginAccount;
    private static Integer typeOfUse;

    public static void main(String[] args) {
        welcomeMenu();
    }

    private static void welcomeMenu() {
        try {
            System.out.println("1.login");
            System.out.println("2.sign-up");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                typeOfUse = 1;
                inputInformationMenu();
            } else if (selectOption == 2) {
                typeOfUse = 2;
                inputInformationMenu();
            } else {
                throw new InvalidOption();
            }
        } catch (InvalidOption | InputMismatchException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
            welcomeMenu();
        }
    }


    private static void inputInformationMenu() {
        try {
            System.out.print("username:");
            String username = scanner.next();
            System.out.print("password:");
            String password = scanner.next();
            if (typeOfUse == 1) {
                if (checkLogin(username, password)) {
                    mainMenu();
                } else
                    welcomeMenu();
            } else if (typeOfUse == 2) {
                accountService.save(new Account(username, password));
                System.out.println("create account success!, please login");
                welcomeMenu();
            }
        } catch (ConstraintViolationException e) {
            scanner.nextLine();
            System.out.println("this username already exist!, try with another username");
            welcomeMenu();
        }
    }

    private static void mainMenu() {
        try {
            System.out.println("1.account services");
            System.out.println("2.tweet services");
            System.out.println("3.comment services");
            System.out.println("4.follow services");
            System.out.println("5.like services");
            System.out.println("6.back to welcome menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                accountServicesMenu();
            } else if (selectOption == 2) {
                tweetServicesMenu();
            } else if (selectOption == 3) {
                commentServicesMenu();
            } else if (selectOption == 4) {
                followServicesMenu();
            } else if (selectOption == 5) {
                likeServicesMenu();
            } else if (selectOption == 6) {
                welcomeMenu();
            } else {
                throw new InvalidOption();
            }
        } catch (InvalidOption | InputMismatchException e) {
            scanner.nextLine();
            System.out.println(e.getMessage());
            mainMenu();
        }
    }

    private static void likeServicesMenu() {
        try {
            System.out.println("1.like or dislike tweet");
            System.out.println("2.remove like or dislike");
            System.out.println("3.show who like or dislike that tweet");
            System.out.println("4.back to services menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                System.out.print("enter tweet id:");
                Integer tweetId = scanner.nextInt();
                if (likeService.checkAlreadyLikeOrDislikeByTweetId(tweetId, loginAccount.getId())) {
                    System.out.println("1.like");
                    System.out.println("2.dislike");
                    System.out.print("select one option:");
                    int option = scanner.nextInt();
                    Tweet tweet = tweetService.findById(tweetId);
                    if (option == 1) {
                        Likes likes = new Likes(loginAccount, tweet, Type.LIKE);
                        likeService.save(likes);
                        System.out.println("like added success!");
                    } else if (option == 2) {
                        Likes likes = new Likes(loginAccount, tweet, Type.DISLIKE);
                        likeService.save(likes);
                        System.out.println("dislike added success!");
                    } else
                        throw new InvalidOption();
                } else
                    throw new AlreadyPointThatTweet();
                likeServicesMenu();
            } else if (selectOption == 2) {
                System.out.print("enter tweet id:");
                Integer tweetId = scanner.nextInt();
                likeService.removeLikeOrDislikeByTweetId(tweetId,loginAccount.getId());
                likeServicesMenu();
            } else if (selectOption == 3) {
                System.out.print("enter tweet id:");
                Integer tweetId = scanner.nextInt();
                likeService.findAllLikeByTweetId(tweetId).forEach(System.out::println);
                likeServicesMenu();
            } else if (selectOption == 4) {
                mainMenu();
            } else {
                throw new InvalidOption();
            }
        } catch (InvalidOption | InputMismatchException | NoResultException | AlreadyPointThatTweet | RecordDoesNotExist e) {
            scanner.nextLine();
            System.out.println(e.getMessage());
            likeServicesMenu();
        }

    }

    private static void followServicesMenu() {
        try {
            System.out.println("1.follow account");
            System.out.println("2.unfollow account");
            System.out.println("3.show follower account");
            System.out.println("4.show following account");
            System.out.println("5.back to services menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                System.out.print("enter account name:");
                String accountName = scanner.next();
                if (checkAccountName(accountName)) {
                    Account account = accountService.findByUsernameId(accountName);
                    Follow follow = new Follow(account, loginAccount);
                    followService.save(follow);
                    System.out.println("follow success");
                }
                followServicesMenu();
            } else if (selectOption == 2) {
                System.out.print("enter account name:");
                String accountName = scanner.next();
                Account account = accountService.findByUsernameId(accountName);
                Follow follow = new Follow(account, loginAccount);
                followService.delete(follow);
                System.out.println("unfollow success");
                followServicesMenu();
            } else if (selectOption == 3) {
                System.out.print("enter account name:");
                String accountName = scanner.next();
                followService.findAllFollowingByAccountName(accountName).forEach(System.out::println);
                followServicesMenu();
            } else if (selectOption == 4) {
                System.out.print("enter account name:");
                String accountName = scanner.next();
                followService.findAllFollowerByAccountName(accountName).forEach(System.out::println);
                followServicesMenu();
            } else if (selectOption == 5) {
                mainMenu();
            } else {
                throw new InvalidOption();
            }
        } catch (InvalidOption | InputMismatchException | NoResultException e) {
            System.out.println(e.getMessage());
            followServicesMenu();
            scanner.nextLine();
        }
    }

    private static void accountServicesMenu() {
        try {
            System.out.println("1.show account information");
            System.out.println("2.change password");
            System.out.println("3.remove account");
            System.out.println("4.search account by username");
            System.out.println("5.back to services menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                System.out.println(accountService.findById(loginAccount.getId()).toString2());
                accountServicesMenu();
            } else if (selectOption == 2) {
                System.out.print("enter new password:");
                String password = scanner.next();
                accountService.update(new Account(loginAccount.getId(), loginAccount.getUsername(), password));
                System.out.println("change password success");
                accountServicesMenu();
            } else if (selectOption == 3) {
                System.out.println("your account removed");
                accountService.delete(loginAccount);
                welcomeMenu();
            } else if (selectOption == 4) {
                System.out.print("enter username account:");
                String username = scanner.next();
                accountService.findByUsername(username).forEach(System.out::println);
                accountServicesMenu();
            } else if (selectOption == 5) {
                mainMenu();
            } else {
                throw new InvalidOption();
            }
        } catch (InvalidOption | InputMismatchException e) {
            System.out.println(e.getMessage());
            accountServicesMenu();
            scanner.nextLine();
        }
    }

    private static void tweetServicesMenu() {
        try {
            System.out.println("1.add new tweet");
            System.out.println("2.show your tweets");
            System.out.println("3.show all tweets");
            System.out.println("4.edit tweet");
            System.out.println("5.back to services menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                scanner.nextLine();
                System.out.print("enter tweet:");
                String tweet = scanner.nextLine();
                if (tweet.length() > 280)
                    throw new TweetLengthOutOfRange();
                tweetService.save(new Tweet(loginAccount, tweet, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))));
                System.out.println("tweet publish success!");
                tweetServicesMenu();
            } else if (selectOption == 2) {
                tweetService.findAllById(loginAccount.getId()).forEach(System.out::println);
                tweetServicesMenu();
            } else if (selectOption == 3) {
                tweetService.findAll().forEach(System.out::println);
                tweetServicesMenu();
            } else if (selectOption == 4) {
                System.out.print("enter tweet id:");
                Integer tweetId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("enter new tweet:");
                String tweet = scanner.nextLine();
                if (tweet.length() > 280)
                    throw new TweetLengthOutOfRange();
                Tweet updateTweet = new Tweet(tweetId, loginAccount, tweet, Date.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
                tweetService.update(updateTweet);
                System.out.println("update success!");
                tweetServicesMenu();
            } else if (selectOption == 5) {
                mainMenu();
            } else {
                throw new InvalidOption();
            }
        } catch (TweetLengthOutOfRange | InputMismatchException | InvalidOption e) {
            System.out.println(e.getMessage());
            tweetServicesMenu();
            scanner.nextLine();
        }
    }

    private static void commentServicesMenu() {
        try {
            System.out.println("1.add new comment");
            System.out.println("2.edit comment");
            System.out.println("3.remove comment");
            System.out.println("4.show your comments");
            System.out.println("5.show all comment by tweet");
            System.out.println("6.back to services menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                System.out.print("enter tweet id:");
                int tweet_id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("enter comment:");
                String comment = scanner.nextLine();
                commentService.save(new Comment(loginAccount, tweetService.findById(tweet_id), comment));
                System.out.println("your comment added success!");
                commentServicesMenu();
            } else if (selectOption == 2) {
                System.out.print("enter comment id:");
                Integer comment_id = scanner.nextInt();
                System.out.print("enter new comment:");
                scanner.nextLine();
                String comment = scanner.nextLine();
                Comment comment1 = commentService.findById(comment_id);
                comment1.setDescription(comment);
                commentService.update(comment1);
                System.out.println("your comment edited success!");
                commentServicesMenu();
            } else if (selectOption == 3) {
                System.out.print("enter comment id:");
                int comment_id = scanner.nextInt();
                commentService.delete(commentService.findById(comment_id));
                System.out.println("your comment delete success!");
                commentServicesMenu();
            } else if (selectOption == 4) {
                commentService.findAllById(loginAccount.getId()).forEach(System.out::println);
                commentServicesMenu();
            } else if (selectOption == 5) {
                System.out.print("enter tweet id:");
                Integer tweetId = scanner.nextInt();
                commentService.findAllByTweetId(tweetId).forEach(System.out::println);
                commentServicesMenu();
            } else if (selectOption == 6) {
                mainMenu();
            } else {
                throw new InvalidOption();
            }
        } catch (InputMismatchException | InvalidOption e) {
            System.out.println(e.getMessage());
            commentServicesMenu();
            scanner.nextLine();
        }
    }

    private static boolean checkAccountName(String username) {
        try {
            if (username.equals(loginAccount.getUsername())) {
                throw new CanNotFollowYourAccount();
            }
            return true;
        } catch (CanNotFollowYourAccount e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    private static boolean checkLogin(String username, String password) {
        try {
            loginAccount = accountService.login(username, password);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("wrong username or password!");
            return false;
        }
    }
}
