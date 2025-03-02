package com.listadecompras.listacompras.service;
import com.listadecompras.listacompras.entity.Transaction;
import com.listadecompras.listacompras.repository.TransactionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void updateTransaction (final Long id, final Transaction transaction) {

        final Transaction transactionSaved = this.transactionRepository.findById(id).orElse(null);

        if (transactionSaved == null || !transactionSaved.getId().equals(id)) {
            throw new RuntimeException();
        }
        BeanUtils.copyProperties(transaction, transactionSaved);
        this.transactionRepository.save(transactionSaved);
    }

    public void deleteProduct (final Long id){
        final Transaction dataTransaction = this.transactionRepository.findById(id).orElse(null);

        if (dataTransaction == null || !dataTransaction.getId().equals(id)){
            throw new RuntimeException();
        }
        this.transactionRepository.delete(dataTransaction);
    }
}
