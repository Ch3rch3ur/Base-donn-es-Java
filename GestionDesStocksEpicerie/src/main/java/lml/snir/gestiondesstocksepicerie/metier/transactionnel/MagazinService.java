package lml.snir.gestiondesstocksepicerie.metier.transactionnel;

import java.util.List;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;
import lml.snir.gestiondesstocksepicerie.metier.entity.Stock;
import lml.snir.gestiondesstocksepicerie.metier.entity.Categorie;
import lml.snir.persistence.CrudService;


/**
 * Défini les services à rendre, elle ne dit pas comment on fait (pas de code !!)
 * @author jupiter
 */
public interface MagazinService extends CrudService<Magazin> {
    public List<Magazin> getByNom(String nom) throws Exception;
    public List<Magazin> getByPassword(String password) throws Exception;
    public List<Magazin> getByLogin(String login) throws Exception;
}

