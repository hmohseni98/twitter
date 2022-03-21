package Service;

import Repository.AccountRepository;
import Repository.CommentRepository;
import entity.Account;
import entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentService extends BaseService<CommentRepository, Comment, Integer> {

    private CommentRepository commentRepository = new CommentRepository();

    public CommentService() {
        super(new CommentRepository() , Comment.class);
    }

    public List<Comment> findAllById(Integer id) {
        return commentRepository.findAll(Comment.class)
                .stream()
                .filter(comment -> comment.getTweet().getId() == id)
                .collect(Collectors.toList());
    }
}
