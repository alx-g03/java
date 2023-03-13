package domain;

import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class User extends Entity<Integer> {
    private String name;
    private Vector<User> friends;

    public User(String name) {
        this.name = name;
        this.friends = new Vector<User>();
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public void deleteFriend(User friend) {
        friends.remove(friend);
    }

    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", friends=" + friends +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return getName().equals(that.getName()) &&
                getFriends().equals(that.getFriends());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getFriends());
    }
}
