package service;

import repository.lmpl.TweetRepository;
import entity.Tweet;

import java.util.List;
import java.util.stream.Collectors;

public class TweetService extends BaseService<TweetRepository,Tweet,Integer>{

    private final TweetRepository tweetRepository = new TweetRepository();

    public TweetService() {
        super(new TweetRepository() , Tweet.class);
    }

    public List<Tweet> findAllById(Integer id){
        return tweetRepository.findAll(Tweet.class)
                .stream()
                .filter(tweet -> tweet.getAccount().getId().equals(id))
                .collect(Collectors.toList());
    }
}
