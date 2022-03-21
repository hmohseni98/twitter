import CustomException.AccountDoesNotExist;
import CustomException.InvalidOption;
import CustomException.TweetLengthOutOfRange;
import CustomException.UsernameAlreadyExist;
import Service.AccountService;
import Service.CommentService;
import Service.TweetService;
import entity.Account;
import entity.Comment;
import entity.Tweet;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Integer selectOption;
    private static final AccountService accountService = new AccountService();
    private static final TweetService tweetService = new TweetService();
    private static final CommentService commentService = new CommentService();
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
                System.out.println("invalid option");
                welcomeMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("invalid character");
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
                }
            } else if (typeOfUse == 2) {
                accountService.save(new Account(username, password));
                System.out.println("create account success!, please login");
                welcomeMenu();
            }
        } catch (UsernameAlreadyExist | AccountDoesNotExist e) {
            System.out.println(e.getMessage());
            welcomeMenu();
            scanner.nextLine();
        }
    }

    private static void mainMenu() {
        System.out.println("1.account services");
        System.out.println("2.tweet services");
        System.out.println("3.comment services");
        System.out.println("4.back to welcome menu");
        System.out.print("please select one option:");
        selectOption = scanner.nextInt();
        if (selectOption == 1) {
            accountServicesMenu();
        } else if (selectOption == 2) {
            tweetServicesMenu();
        } else if (selectOption == 3) {
            commentServicesMenu();
        } else if (selectOption == 4) {
            welcomeMenu();
        } else {
            System.out.println("invalid option");
            mainMenu();
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
                System.out.println(accountService.findById(loginAccount.getId()));
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
                System.out.println(accountService.findByUsername(username).toString2());
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
                System.out.print("enter subject:");
                String subject = scanner.nextLine();
                System.out.print("enter description:");
                String description = scanner.nextLine();
                if (description.length() > 280)
                    throw new TweetLengthOutOfRange();
                tweetService.save(new Tweet(loginAccount, subject, description));
                System.out.println("tweet publish success!");
                tweetServicesMenu();
            } else if (selectOption == 2) {
                tweetService.findAllById(loginAccount.getId()).forEach(System.out::println);
                tweetServicesMenu();
            } else if (selectOption == 3) {
                tweetService.findAll().forEach(System.out::println);
                tweetServicesMenu();
            } else if (selectOption == 4) {
                scanner.nextLine();
                System.out.print("enter new subject:");
                String subject = scanner.nextLine();
                System.out.print("enter new description:");
                String description = scanner.nextLine();
                if (description.length() > 280)
                    throw new TweetLengthOutOfRange();
                tweetService.update(new Tweet(loginAccount, subject, description));
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
            System.out.println("5.back to services menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                System.out.print("enter tweet id:");
                int tweet_id = scanner.nextInt();
                System.out.print("enter comment description:");
                String description = scanner.nextLine();
                commentService.save(new Comment(loginAccount, tweetService.findById(tweet_id), description));
                System.out.println("your comment added success!");
                commentServicesMenu();
            } else if (selectOption == 2) {
                System.out.print("enter comment id:");
                Integer comment_id = scanner.nextInt();
                System.out.print("enter tweet id:");
                int tweet_id = scanner.nextInt();
                System.out.print("enter new comment description:");
                String description = scanner.nextLine();
                commentService.update(new Comment(comment_id, loginAccount, tweetService.findById(tweet_id), description));
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

    private static boolean checkLogin(String username, String password) {
        loginAccount = accountService.login(username, password);
        return true;
    }
}
