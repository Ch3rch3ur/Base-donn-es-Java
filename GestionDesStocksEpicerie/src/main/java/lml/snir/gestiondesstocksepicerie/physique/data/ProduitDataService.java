package lml.snir.gestiondesstocksepicerie.physique.data;

import java.util.List;
import lml.snir.gestiondesstocksepicerie.metier.entity.Produit;
import lml.snir.persistence.CrudService;

public interface ProduitDataService extends CrudService<Produit> {
    public List<Produit> getByNom(String nom) throws Exception;        
}