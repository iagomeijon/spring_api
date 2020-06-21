package com.iagomeijon.spring_api.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.filter.OrderedRequestContextFilter;
import org.springframework.context.annotation.Configuration;

import com.iagomeijon.spring_api.domain.Address;
import com.iagomeijon.spring_api.domain.BillPayment;
import com.iagomeijon.spring_api.domain.CardPayment;
import com.iagomeijon.spring_api.domain.Category;
import com.iagomeijon.spring_api.domain.City;
import com.iagomeijon.spring_api.domain.Client;
import com.iagomeijon.spring_api.domain.Order;
import com.iagomeijon.spring_api.domain.OrderItem;
import com.iagomeijon.spring_api.domain.Payment;
import com.iagomeijon.spring_api.domain.Product;
import com.iagomeijon.spring_api.domain.State;
import com.iagomeijon.spring_api.domain.enums.ClientType;
import com.iagomeijon.spring_api.domain.enums.PaymentStatus;
import com.iagomeijon.spring_api.repositories.AddressRepository;
import com.iagomeijon.spring_api.repositories.CategoryRepository;
import com.iagomeijon.spring_api.repositories.CityRepository;
import com.iagomeijon.spring_api.repositories.ClientRepository;
import com.iagomeijon.spring_api.repositories.OrderItemRepository;
import com.iagomeijon.spring_api.repositories.OrderRepository;
import com.iagomeijon.spring_api.repositories.PaymentRepository;
import com.iagomeijon.spring_api.repositories.ProductRepository;
import com.iagomeijon.spring_api.repositories.StateRepository;

@Configuration
public class Instatiation implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//MARK: Product and category
		Product p1 = new Product(null, "Computer", 3000.00);
		Product p2 = new Product(null, "Priter", 250.99);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		Category c1 = new Category(null, "Computing");
		Category c3 = new Category(null, "Home");
		Category c2 = new Category(null, "Office");
		
		c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		c2.getProducts().add(p2);
		
		p1.getCategories().add(c1);
		p2.getCategories().addAll(Arrays.asList(c1, c2));
		p3.getCategories().add(c1);
		
		categoryRepository.saveAll(Arrays.asList(c1, c2 ,c3));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		//MARK: State and city
		State st1 = new State(null, "Bahia");
		State st2 = new State(null, "São paulo");
		
		City city1 = new City(null, "Salvador", st1);
		City city2 = new City(null, "São paulo", st2);
		City city3 = new City(null, "Santos", st2);
		
		st1.getCities().add(city1);
		st2.getCities().addAll(Arrays.asList(city2, city3));
		
		
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));
		
		//MARK Client and Address
		Client client1 = new Client(null, "Iago", "iago@gmail.com", "12345678912", ClientType.PHYSICALPERSON);
		Client client2 = new Client(null, "Company", "company@gmail.com", "12345845715", ClientType.LEGALPERSON);
		client1.getPhones().addAll(Arrays.asList("7188551364", "11977551475"));
		client2.getPhones().add("71989154635");
		
		Address address1 = new Address(null, "street1", "neighborhood1", "10", "42638891", client1, city1);
		Address address2 = new Address(null, "street2", "neighborhood2", "11", "42638874", client1, city2);
		Address address3 = new Address(null, "street3", "neighborhood3", "12", "42638745", client2, city3);
		
		client1.getAddressses().addAll(Arrays.asList(address1, address2));
		client2.getAddressses().add(address3);
		
		clientRepository.saveAll(Arrays.asList(client1, client2));
		addressRepository.saveAll(Arrays.asList(address1, address2, address3));
		
		// MARK Oder and Payment 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
		
		Order order1 = new Order(null, sdf.parse("30/05/2020 10:34"), client1, client1.getAddressses().get(0));
		Order order2 = new Order(null, sdf.parse("31/04/2020 18:34"), client1, client1.getAddressses().get(1));
		
		Payment payment1 = new CardPayment(null, PaymentStatus.CLOSED, order1, 6);
		Payment payment2 = new BillPayment(null, PaymentStatus.PENDING, order2, sdf.parse("05/05/2020 00:00"), sdf.parse("06/06/2020 00:00"));
		order1.setPayment(payment1);
		order2.setPayment(payment2);
		
		client1.getOrder().addAll(Arrays.asList(order1, order2));
		
		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));
		clientRepository.save(client1);
		
		// MARK OrderItem
		OrderItem orderItem1 = new OrderItem(order1, p1, 00.00, 1, 3000.00);
		OrderItem orderItem2 = new OrderItem(order1, p3, 00.00, 2, 160.00);
		OrderItem orderItem3 = new OrderItem(order2, p2, 00.00, 1, 150.00);
		
		order1.getItens().addAll(Arrays.asList(orderItem1, orderItem2));
		order2.getItens().add(orderItem3);
		
		p1.getItens().add(orderItem1);
		p2.getItens().add(orderItem3);
		p3.getItens().add(orderItem2);
		
		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3));
		
	}

}
