package Service;

import Repository.LikeRepository;
import entity.Likes;

import java.util.List;
import java.util.stream.Collectors;

public class LikeService extends BaseService<LikeRepository, Likes, Integer> {

    private final LikeRepository likeRepository = new LikeRepository();

    public LikeService() {
        super(new LikeRepository(), Likes.class);
    }

    public List<Likes> findAllLikeByTweetId(Integer id) {
        return likeRepository.findAll(Likes.class)
                .stream()
                .filter(likes -> likes.getTweet().getId().equals(id))
                .collect(Collectors.toList());
    }

    public boolean checkAlreadyLikeOrDislikeByTweetId(Integer tweetId, Integer accountId) {
        long count = likeRepository.findAll(Likes.class)
                .stream()
                .filter(likes -> likes.getTweet().getId().equals(tweetId) || likes.getAccount().getId().equals(accountId))
                .count();
        return count != 1;
    }

    public void removeLikeOrDislikeByTweetId(Integer tweetId, Integer accountId) {
        likeRepository.delete(
                likeRepository.findAll(Likes.class)
                        .stream()
                        .filter(likes -> likes.getTweet().getId().equals(tweetId) ||
                                likes.getAccount().getId().equals(accountId))
                        .iterator()
                        .next());
    }

}
