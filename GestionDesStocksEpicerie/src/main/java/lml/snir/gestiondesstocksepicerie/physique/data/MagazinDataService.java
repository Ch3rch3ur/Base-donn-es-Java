package lml.snir.gestiondesstocksepicerie.physique.data;

import java.util.List;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;
import lml.snir.persistence.CrudService;

/**
 *
 * @author joris
 */
public interface MagazinDataService extends CrudService<Magazin> {
    public List<Magazin> getByNom(String nom) throws Exception;
    public List<Magazin> getByLogin(String login) throws Exception;
    public List<Magazin> getByPassword(String password) throws Exception;
}