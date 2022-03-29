package service;

import repository.lmpl.CommentRepository;
import entity.Comment;

import java.util.List;
import java.util.stream.Collectors;

public class CommentService extends BaseService<CommentRepository, Comment, Integer> {

    private final CommentRepository commentRepository = new CommentRepository();

    public CommentService() {
        super(new CommentRepository() , Comment.class);
    }

    public List<Comment> findAllById(Integer id) {
        return commentRepository.findAll(Comment.class)
                .stream()
                .filter(comment -> comment.getTweet().getId().equals(id))
                .collect(Collectors.toList());
    }

    public List<Comment> findAllByTweetId(Integer id){
        return commentRepository.findAll(Comment.class)
                .stream()
                .filter(comment -> comment.getTweet().getId().equals(id))
                .collect(Collectors.toList());
    }
}
