package com.example.lab4.repository;

import com.example.lab4.domain.User;
import com.example.lab4.domain.Validator;

import java.sql.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class UserDbRepo implements Repository<Long, User> {
    private String url;
    private String username;
    private String password;
    private Validator<User> validator;

    public UserDbRepo(String url, String username, String password, Validator<User> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public Optional<User> findOne(Long id_s) {
        String sql = "SELECT * from users WHERE users.id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
             ps.setLong(1, id_s);
             try (ResultSet rs = ps.executeQuery()) {
                 rs.next();
                 String firstName = rs.getString("first_name");
                 String lastName = rs.getString("last_name");
                 User user = new User(firstName, lastName);
                 user.setId((long) user.hashCode());
                 return Optional.of(user);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Iterable<User> findAll() {
        Set<User> users = new HashSet<>();
        String sql = "SELECT * from users";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                User user = new User(firstName, lastName);
                user.setId((long) user.hashCode());
                users.add(user);
            }
            return users;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> save(User user) {
        String sql = "insert into users (first_name, last_name) values (?, ?)";
        validator.validate(user);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            return Optional.ofNullable(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(Long id) {
        return Optional.empty();
    }

    public User findUser(Long id_s) {
        for (User u : this.findAll()) {
            if (Objects.equals(id_s, u.getId()))
                return u;
        }
        return null;
    }

    public User searchUser(String firstName, String lastName) {
        String sql = "SELECT * from users WHERE users.first_name = ? AND users.last_name = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                User user = new User(firstName, lastName);
                user.setId((long) user.hashCode());
                return user;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
