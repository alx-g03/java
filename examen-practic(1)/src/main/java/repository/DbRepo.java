package repository;


import domain.Categorie;
import domain.Excursie;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DbRepo implements Repository<Long, Excursie> {
    private String url;
    private String username;
    private String password;

    public DbRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Excursie findOne(Long id_s) {
        String sql = "SELECT * from excursii WHERE excursii.id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
             ps.setLong(1, id_s);
             try (ResultSet rs = ps.executeQuery()) {
                 rs.next();
                 String oras = rs.getString("oras");
                 String atractie = rs.getString("atractie");
                 String categorie_str = rs.getString("categorie");
                 Double pret = rs.getDouble("pret");
                 Categorie categorie = Categorie.valueOf(categorie_str);
                 Excursie excursie = new Excursie(oras, atractie, categorie, pret);
                 excursie.setId((long) excursie.hashCode());
                 return excursie;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Excursie> findAll() {
        Set<Excursie> excursii = new HashSet<>();
        String sql = "SELECT * from excursii";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String oras = rs.getString("oras");
                String atractie = rs.getString("atractie");
                String categorie_str = rs.getString("categorie");
                Double pret = rs.getDouble("pret");
                Categorie categorie = Categorie.valueOf(categorie_str);
                Excursie excursie = new Excursie(oras, atractie, categorie, pret);
                excursie.setId((long) excursie.hashCode());
                excursii.add(excursie);
            }
            return excursii;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return excursii;
    }

    @Override
    public Excursie save(Excursie excursie) {
        String sql = "insert into excursii (oras, atractie, categorie, pret) values (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            String categorie_str = excursie.getCategorie().toString();
            ps.setString(1, excursie.getOras());
            ps.setString(2, excursie.getAtractie());
            ps.setString(3, categorie_str);
            ps.setDouble(4, excursie.getPret());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            return excursie;
        }
        return null;
    }

    @Override
    public Excursie delete(Long id) {
        return null;
    }
}
