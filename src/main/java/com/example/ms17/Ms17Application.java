package com.example.ms17;

import com.example.ms17.model.Customer;
import com.example.ms17.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class Ms17Application implements CommandLineRunner {
	/**
	 * EntityManager#persist() - melumati qosmaq
	 * EntityManager#merge()
	 * EntityManager#Remove()
	 * EntityManager#flush()
	 */
	private final CustomerRepository customerRepository;
	private final EntityManagerFactory emf;

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Ms17Application.class, args);
		log.info("Application is starting!");

		log.warn("Sdfdf");
	}

	@Override
	public void run(String... args) throws Exception{
		Customer customer=new Customer();
		customer.setName("Test");
		customer.setAddress("Branch Address");
		customer.setBranch(15);
		customerRepository.save(customer);
		EntityManager em=emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
		em.close();

		System.out.println(customerRepository.findById(1l));
		EntityManager em1=emf.createEntityManager();

		em1.getTransaction().begin();
		em1.persist(customer);
		em1.getTransaction().commit();
		em1.close();

		Customer customer1 =emf.createEntityManager().find(Customer.class,1L);
		System.out.printf("CustomerFindeAll" + customer1);

		EntityManager em3=emf.createEntityManager();

		em3.getTransaction().begin();
		em3.remove(customer);
		em3.getTransaction().commit();
		em3.close();

		Customer customer2 =emf.createEntityManager().find(Customer.class,1L);
		System.out.printf("CustomerFindeAll" + customer2);


	}

}
