package com.example.lab4.service;

import com.example.lab4.domain.Friendship;
import com.example.lab4.domain.Status;
import com.example.lab4.domain.User;
import com.example.lab4.repository.FriendshipRepo;
import com.example.lab4.repository.UserDbRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class UserService {
    private UserDbRepo userDbRepo;
    private FriendshipRepo friendshipRepo;

    public UserService() {}

    public UserService(UserDbRepo userDbRepo) {
        this.userDbRepo = userDbRepo;
        this.friendshipRepo = FriendshipRepo.getInstance();
    }

    public void addUser(String firstName, String lastName) {
        User user = new User(firstName, lastName);
        user.setId((long) user.hashCode());
        this.userDbRepo.save(user);
    }

    public User getUser(String firstName, String lastName) {
        User user = new User(firstName, lastName);
        user.setId((long) user.hashCode());
        for (User u : userDbRepo.findAll()) {
            if (u.equals(user))
                return u;
        }
        return null;
    }

    public User getByID(Long idUser) {
        return this.userDbRepo.findUser(idUser);
    }

    public Iterable<User> getAllUsers() {
        return userDbRepo.findAll();
        }

    public void sendFriendRequest(Long idUser, Long idFriend) {
        Friendship f1 = new Friendship(idUser, idFriend);
        Friendship f2 = new Friendship(idFriend, idUser);
        f1.setDate(LocalDateTime.now());
        f2.setDate(LocalDateTime.now());
        f1.setStatus(Status.SENT);
        f2.setStatus(Status.PENDING);
        friendshipRepo.save(f1);
        friendshipRepo.save(f2);
    }

    public void acceptFriendRequest(Long idUser, Long idFriend) {
        Friendship f1 = new Friendship(idUser, idFriend);
        Friendship f2 = new Friendship(idFriend, idUser);
        f1.setDate(LocalDateTime.now());
        f2.setDate(LocalDateTime.now());
        f1.setStatus(Status.ACCEPTED);
        f2.setStatus(Status.ACCEPTED);
        friendshipRepo.delete(f1);
        friendshipRepo.delete(f2);
        friendshipRepo.save(f1);
        friendshipRepo.save(f2);
    }

    public void deleteFriendship(Long idUser, Long idFriend) {
        Friendship f1 = new Friendship(idUser, idFriend);
        Friendship f2 = new Friendship(idFriend, idUser);
        friendshipRepo.delete(f1);
        friendshipRepo.delete(f2);
    }

    public Iterable<Friendship> getAllFriendships() {
        return friendshipRepo.findAll();
    }

    public Iterable<User> getUserFriends(User user) {
        ArrayList<User> friends = new ArrayList<>();
        for (Friendship f : this.friendshipRepo.findAll()) {
            if (Objects.equals(user.getId(), f.getLeft()) && f.getStatus() == Status.ACCEPTED) {
                friends.add(this.userDbRepo.findUser(f.getRight()));
            }
        }
        return friends;
    }
}
