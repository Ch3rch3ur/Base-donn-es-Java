package lml.snir.gestiondesstocksepicerie.physique.data;

import java.util.List;
import java.util.Map;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;
import lml.snir.persistence.jdbc.AbstractCrudServiceJDBC;

/**
 *
 * @author fanou
 */
final class MagazinDataServiceJDBCImpl<T> extends AbstractCrudServiceJDBC<Magazin> implements MagazinDataService {

    public MagazinDataServiceJDBCImpl() throws Exception {
        String query = null;
        try {
            switch (super.getDBType()) {
                case SQLITE:
                    query = "CREATE TABLE IF NOT EXISTS `" + super.getEntityName() + "` (\n"
                            + "  `id` INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                            + "  `login` varchar(32) UNIQUE,\n"
                            + "  `mdp` varchar(32) NOT NULL,\n"
                            + "  `nom` varchar(32) NOT NULL\n"
                            + ");";
                    break;
            }
            super.executeQuery(query);

        } catch (Exception ex) {
            System.out.println(this.getClass().getSimpleName() + "\n" + super.getDBType() + "\n" + ex);
        }
    }

    @Override
    public List<Magazin> getByNom(String nom) throws Exception {
        String query = "SELECT * FROM " + super.getEntityName() + " WHERE nom = '" + nom + "'";
        return super.getResults(query);
    }

    @Override
    public Magazin add(Magazin magazin) throws Exception {
        String nom = magazin.getNom();
        String login;
        String password;
        String discriminant = magazin.getClass().getSimpleName();
        String query;

        if (magazin instanceof Magazin) {
            Magazin b = (Magazin) magazin;
            login = b.getLogin();
            password = b.getMdp();

            query = "INSERT INTO " + super.getEntityName() + " (nom, prenom, login, mdp, type) VALUES ('"
                    + nom + "','"
                    + login + "','"
                    + password + "','"
                    + discriminant + "')";
        } else {
            query = "INSERT INTO " + super.getEntityName() + " (nom, prenom, type) VALUES ('"
                    + nom + "','"
                    + discriminant + "')";
        }

        magazin.setId(super.executeAdd(query));
        return magazin;
    }

    @Override
    public void remove(Magazin magazin) throws Exception {
        super.remove(magazin.getId());
    }

    @Override
    public void update(Magazin magazin) throws Exception {
        String nom = magazin.getNom();
        String login;
        String password;
        String discriminant = magazin.getClass().getSimpleName();

        String query = "UPDATE " + super.getEntityName() + " SET nom = '"
                + nom + "', prenom = '"
                + discriminant + "' WHERE id = '" + magazin.getId() + "'";

        if (magazin instanceof Magazin) {
            Magazin b = (Magazin) magazin;
            login = b.getLogin();
            password = b.getMdp();
            
            query = "UPDATE " + super.getEntityName() + " SET nom = '"
                    + nom + "', prenom = '"
                    + login + "', mdp = '"
                    + password + "',  type = '"
                    + discriminant + "' WHERE id = '" + magazin.getId() + "'";
        }

        super.executeQuery(query);
    }

    @Override
    protected Magazin createEntity(Map map) throws Exception {
        Magazin magazin;

        int id = (int) map.get("id");
        String nom = (String) map.get("nom");
        String prenom = (String) map.get("prenom");
        String login = (String) map.get("login");
        String mdp = (String) map.get("mdp");
        String discriminant = (String) map.get("type");

        if ("Magazin".equals(discriminant)) {
            magazin = new Magazin(nom, mdp, login);
            Magazin a = (Magazin) magazin;
            a.setLogin(login);
            a.setEncodedMdp(mdp);
        } else {
            magazin = new Magazin(nom, mdp, login);
        }

        magazin.setId(id);

        return magazin;
    }

    @Override
    public List<Magazin> getByLogin(String login) throws Exception {
        String query = "SELECT * FROM " + super.getEntityName() + " WHERE login = '" + login + "'";
        return super.getResults(query);
    }

    @Override
    public List<Magazin> getByPassword(String password) throws Exception {
        String query = "SELECT * FROM " + super.getEntityName() + " WHERE mdp = '" + password + "'";
        return super.getResults(query);
        
    }

}
