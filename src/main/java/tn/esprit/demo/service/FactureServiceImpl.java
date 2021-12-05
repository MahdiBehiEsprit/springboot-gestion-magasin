package tn.esprit.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.demo.entities.Client;
import tn.esprit.demo.entities.Facture;
import tn.esprit.demo.entities.Produit;
import tn.esprit.demo.entities.detailFacture;
import tn.esprit.demo.repository.ClientRepository;
import tn.esprit.demo.repository.DetailFactureRepository;
import tn.esprit.demo.repository.FactureRepository;
import tn.esprit.demo.repository.ProduitRepository;

@Service
public class FactureServiceImpl implements FactureService{

	@Autowired
	FactureRepository FactureRepository;

	@Autowired
	ClientRepository clientRepository;



	@Override
	public List<Facture> getAllFactures() {

		return  (List<Facture>) FactureRepository.findAll();
	}

	@Override

	public void cancelFacture(Long id) {

		Facture f = FactureRepository.findById(id).get();
		f.setActive(false);
		FactureRepository.save(f);

	}

	@Override
	public Facture getFactureById(Long id) {

		return FactureRepository.findById(id).get();
	}

	@Override
	public Facture addFacture(Facture f) {

		return FactureRepository.save(f);
	}

	@Override
	public List<Facture> getByIdClient(Long idClient) {
		if(clientRepository.findById(idClient).isPresent())
		{
			Client c = clientRepository.findById(idClient).get();
			List<Facture> factures = new ArrayList<>();
			factures.addAll(c.getFactures());
			return factures;
		}
		return null;
	}

	@Override
	public Facture addFactureClient(Facture f, Long idClient) {
		if (clientRepository.findById(idClient).isPresent())
		{
			Client c = clientRepository.findById(idClient).get();
		}
		return null;
	}
}