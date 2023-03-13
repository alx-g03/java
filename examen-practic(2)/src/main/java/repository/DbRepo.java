package repository;


import domain.Obiect;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DbRepo implements Repository<Long, Obiect> {
    private String url;
    private String username;
    private String password;

    public DbRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Obiect findOne(Long id_s) {
        return null;
    }

    @Override
    public Iterable<Obiect> findAll() {
        Set<Obiect> obiecte = new HashSet<>();
        String sql = "SELECT * from obiecte";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String numePacient = rs.getString("nume_pacient");
                String medic = rs.getString("medic");
                String sectie = rs.getString("sectie");
                Long durata = rs.getLong("durata");
                Obiect obiect = new Obiect(numePacient, medic, sectie, durata);
                obiect.setId((long) obiect.hashCode());
                obiecte.add(obiect);
            }
            return obiecte;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return obiecte;
    }

    @Override
    public Obiect save(Obiect obiect) {
        return null;
    }

    @Override
    public Obiect delete(Long id) {
        return null;
    }
}
