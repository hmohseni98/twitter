package service;

import repository.lmpl.FollowRepository;
import entity.Follow;

import java.util.List;
import java.util.stream.Collectors;

public class FollowService extends BaseService<FollowRepository, Follow, Integer> {

    private final FollowRepository followRepository = new FollowRepository();

    public FollowService() {
        super(new FollowRepository(), Follow.class);
    }

    public List<Follow> findAllFollowerByAccountName(String username) {
        List<Follow> followers = followRepository.findAll(Follow.class)
                .stream()
                .filter(follow -> follow.getFollowings().getUsername().equals(username))
                .collect(Collectors.toList());
        return followers;
    }

    public Long findAllFollowerByAccName(String username) {
        Long followers = followRepository.findAll(Follow.class)
                .stream()
                .filter(follow -> follow.getFollowings().getUsername().equals(username))
                .count();
        return followers;
    }

    public List<Follow> findAllFollowingByAccountName(String username) {
        List<Follow> followings = followRepository.findAll(Follow.class)
                .stream()
                .filter(follow -> follow.getFollowers().getUsername().equals(username))
                .collect(Collectors.toList());
        return followings;
    }

    public Long findAllFollowingByAccName(String username) {
        Long followings = followRepository.findAll(Follow.class)
                .stream()
                .filter(follow -> follow.getFollowers().getUsername().equals(username))
                .count();
        return followings;
    }
}
