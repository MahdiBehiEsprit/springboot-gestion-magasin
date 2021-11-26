package tn.esprit.demo.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.demo.entities.Stock;
import tn.esprit.demo.repository.StockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockService stockService;
    private StockRepository stockRepository;

    @Override
    public Optional<Stock> get(Long id) {
        return stockRepository.findById(id);
    }

    @Override
    //TODO fix nullpointer solution
    public Stock saveStock(Stock s) {
        stockRepository.save(s);
        return s;
    }

    @Override
    public Stock updateStock(Stock s) {
        stockService.saveStock(s);
        return s;
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public boolean checkExists(Long id){
        return stockRepository.existsById(id);
    }

    @Override
    public List<Stock> warnStock(){
        return stockRepository.warnStock();
    }
}
