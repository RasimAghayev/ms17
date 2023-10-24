package com.example.ms17.service.impl;

import com.example.ms17.model.Account;
import com.example.ms17.repository.AccountRepository;
import com.example.ms17.service.TransferService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferServiceImpl implements TransferService {
    private final AccountRepository accountRepository;
    private final EntityManagerFactory entityManagerFactory;

//    public TransferServiceImpl(AccountRepository accountRepository, EntityManagerFactory entityManagerFactory) {
//        this.accountRepository = accountRepository;
//        this.entityManagerFactory = entityManagerFactory;
//    }

    @Override
    @Transactional
    public void doTransfer(BigDecimal amount) throws Exception {
        Optional<Account> source = accountRepository.findById(2L);
        Optional<Account> target = accountRepository.findById(1L);
        if (source.get().getAmount().compareTo(amount) > 0) {
            source.get().setAmount(source.get().getAmount().subtract(amount));
            accountRepository.save(source.get());
            if (true)
                throw new RuntimeException("DDDDD");
            target.get().setAmount(target.get().getAmount().add(amount));
            accountRepository.save(target.get());

        }

    }

    @Override
//    @SneakyThrows
    public void doTransferWithoutTransactional(BigDecimal amount) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Optional<Account> source = accountRepository.findById(2L);
            Optional<Account> target = accountRepository.findById(1L);
            if (source.get().getAmount().compareTo(amount) > 0) {
                source.get().setAmount(source.get().getAmount().subtract(amount));
                accountRepository.save(source.get());
                if (true)
                    throw new RuntimeException("DDDDD");
//                    throw new Exception("DDDDD");
                target.get().setAmount(target.get().getAmount().add(amount));
                accountRepository.save(target.get());

            }
        } catch (RuntimeException re) {
            transaction.rollback();
            throw re;
        } finally {
            transaction.commit();
            em.close();
        }
    }
}
