package lml.snir.gestiondesstocksepicerie.metier.transactionnel;

import java.util.List;
import lml.snir.gestiondesstocksepicerie.metier.MetierFactory;
import lml.snir.gestiondesstocksepicerie.metier.entity.Magazin;
import lml.snir.gestiondesstocksepicerie.metier.entity.Stock;
import lml.snir.gestiondesstocksepicerie.physique.data.MagazinDataService;
import lml.snir.gestiondesstocksepicerie.physique.data.PhysiqueDataFactory;

/**
 *
 * @author joris
 */
public class MagazinServiceImpl implements MagazinService {
    
    private final MagazinDataService magazinDataService;
    
    public MagazinServiceImpl() throws Exception {
        this.magazinDataService = PhysiqueDataFactory.getMagazinDataService();
    }
    
    @Override
    public List<Magazin> getByNom(String nom) throws Exception {
        return this.magazinDataService.getByNom(nom);
    }

    @Override
    public Magazin add(Magazin magazin) throws Exception {
        if (magazin instanceof Magazin) {
            if (this.magazinDataService.getByLogin(magazin.getLogin()) != null) {
                throw new Exception("Login déjà attribué !");
            }
            return this.magazinDataService.add(magazin);
        } else {
            throw new Exception("L'objet n'est pas de type Magazin !");
        }
    }
    
    @Override
    public void remove(Magazin magazin) throws Exception {
        List<Stock> stock = MetierFactory.getStockService().getByMagazin(magazin);
        if (!stock.isEmpty()) {
            throw new Exception("Impossible de supprimer il y a " + stock.size() + " stock qui possèdent déjà ce " + magazin.toString());
        }
        
        this.magazinDataService.remove(magazin);
    }
    
    @Override
    public void update(Magazin magazin) throws Exception {
        this.magazinDataService.update(magazin);
    }
    
    @Override
    public Magazin getById(Long id) throws Exception {
        return this.magazinDataService.getById(id);
    }
    
    @Override
    public long getCount() throws Exception {
        return this.magazinDataService.getCount();
    }
    
    @Override
    public List<Magazin> getAll() throws Exception {
        return this.magazinDataService.getAll();
    }
    
    @Override
    public List<Magazin> getAll(int begin, int count) throws Exception {
        return this.magazinDataService.getAll(begin, count);
    }
    
    @Override
    public List<Magazin> getByPassword(String password) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Magazin> getByLogin(String login) throws Exception {
        return this.magazinDataService.getByLogin(login);
    }
    
}
