package com.example.lab4.repository;

import com.example.lab4.domain.Friendship;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FriendshipRepo {
    private static FriendshipRepo single_instance = null;
    public List<Friendship> friendships;

    private FriendshipRepo() {
        friendships = new ArrayList<>();
    }

    public static FriendshipRepo getInstance() {
        if (single_instance == null)
            single_instance = new FriendshipRepo();
        return single_instance;
    }

    public Friendship findOne(Friendship friendship) {
        for (Friendship f : friendships) {
            if (Objects.equals(f.getLeft(), friendship.getLeft()) && Objects.equals(f.getRight(), friendship.getRight()))
                return f;
        }
        return null;
    }

    public List<Friendship> findAll() {
        return friendships;
    }

    public Friendship save(Friendship friendship) {
        for (Friendship f : friendships) {
            if (Objects.equals(f.getLeft(), friendship.getLeft()) && Objects.equals(f.getRight(), friendship.getRight()))
                return null;
        }
        friendships.add(friendship);
        return friendship;
    }

    public Friendship delete(Friendship friendship) {
        for (Friendship f : friendships) {
            if (Objects.equals(f.getLeft(), friendship.getLeft()) && Objects.equals(f.getRight(), friendship.getRight())) {
                friendships.remove(friendship);
                return f;
            }
        }
        return null;
    }
}
