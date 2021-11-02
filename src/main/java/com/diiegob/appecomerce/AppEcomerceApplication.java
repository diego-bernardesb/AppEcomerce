package com.diiegob.appecomerce;

import com.diiegob.appecomerce.domain.*;
import com.diiegob.appecomerce.domain.enuns.StatusPayment;
import com.diiegob.appecomerce.domain.enuns.TypeClient;
import com.diiegob.appecomerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class AppEcomerceApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private ItemOrderRepository itemOrderRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppEcomerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Jardinagem");
		Category cat5 = new Category(null, "Eletrônicos");
		Category cat6 = new Category(null, "Decoração");
		Category cat7 = new Category(null, "Perfumaria");
		Category cat8 = new Category(null, "Móveis");
		Category cat9 = new Category(null, "Limpeza");

		Product p1 = new Product(null,"Computador", 2000.00);
		Product p2 = new Product(null,"Impressora", 800.00);
		Product p3 = new Product(null,"Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", TypeClient.PESSOAFISICA);

		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Address e1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1 );
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2 );

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Request ped1 = new Request(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Request ped2 = new Request(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Payment pagto1 = new CardPayment(null, StatusPayment.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1); //adiciona o pagamento ao pedido

		Payment pagto2 = new BilletPayment(null, StatusPayment.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2)); //adiciona os pedidos ao cliente

		requestRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemOrder ip1 = new ItemOrder(ped1, p1,0.00 , 1, 2000.00);
		ItemOrder ip2 = new ItemOrder(ped1, p3,0.00 , 2, 80.00);
		ItemOrder ip3 = new ItemOrder(ped2, p2,100.00 , 1, 800.00);

		//adiciona os itens de pedidos a lista de pedidos
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		//adiciona os produtos a lista de itens de pedidos
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemOrderRepository.saveAll(Arrays.asList(ip1, ip2, ip3));



	}
}
