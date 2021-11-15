package com.diiegob.appecomerce.services;

import com.diiegob.appecomerce.domain.*;
import com.diiegob.appecomerce.domain.enuns.StatusPayment;
import com.diiegob.appecomerce.domain.enuns.TypeClient;
import com.diiegob.appecomerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

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


    public void instantiateTestDataBase() throws ParseException {


        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Cama mesa e banho");
        Category cat4 = new Category(null, "Eletrônicos");
        Category cat5 = new Category(null, "Jardinagem");
        Category cat6 = new Category(null, "Decoração");
        Category cat7 = new Category(null, "Perfumaria");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);
        Product p4 = new Product(null, "Mesa de escritório", 300.00);
        Product p5 = new Product(null, "Toalha", 50.00);
        Product p6 = new Product(null, "Colcha", 200.00);
        Product p7 = new Product(null, "TV true color", 1200.00);
        Product p8 = new Product(null, "Roçadeira", 800.00);
        Product p9 = new Product(null, "Abajour", 100.00);
        Product p10 = new Product(null, "Pendente", 180.00);
        Product p11 = new Product(null, "Shampoo", 90.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Arrays.asList(p11));

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Arrays.asList(cat2));
        p5.getCategories().addAll(Arrays.asList(cat3));
        p6.getCategories().addAll(Arrays.asList(cat3));
        p7.getCategories().addAll(Arrays.asList(cat4));
        p8.getCategories().addAll(Arrays.asList(cat5));
        p9.getCategories().addAll(Arrays.asList(cat6));
        p10.getCategories().addAll(Arrays.asList(cat6));
        p11.getCategories().addAll(Arrays.asList(cat7));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

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
        Client cli2 = new Client(null, "Ana Dias", "anadias@gmail.com", "36378912377", TypeClient.PESSOAFISICA);
        Client cli3 = new Client(null, "João Rodrigues", "joao-rodrigues@gmail.com", "36378912377", TypeClient.PESSOAFISICA);
        Client cli4 = new Client(null, "Agnaldo Pereira", "agnaldo@gmail.com", "36378912377", TypeClient.PESSOAFISICA);
        Client cli5 = new Client(null, "Lia Brown", "lia.b@gmail.com", "36378912377", TypeClient.PESSOAFISICA);
        Client cli6 = new Client(null, "Alberto Martins", "albi@gmail.com", "36378912377", TypeClient.PESSOAFISICA);
        Client cli7 = new Client(null, "Bia Antunes", "bia@gmail.com", "36378912377", TypeClient.PESSOAFISICA);
        Client cli8 = new Client(null, "Reginaldo Alencar", "reginaldo@gmail.com", "36378912377", TypeClient.PESSOAFISICA);
        Client cli9 = new Client(null, "Bruno Santos", "santos.bru@gmail.com", "36378912377", TypeClient.PESSOAFISICA);
        Client cli10 = new Client(null, "Rodolfo Santos", "ro.santos@gmail.com", "36378912377", TypeClient.PESSOAFISICA);
        Client cli11 = new Client(null, "Josefine Araujo", "jofine@gmail.com", "36378912377", TypeClient.PESSOAFISICA);
        Client cli12 = new Client(null, "Rafael Pinheiros", "rafa@gmail.com", "36378912377", TypeClient.PESSOAFISICA);
        Client cli13 = new Client(null, "Marta Cecilia", "marta@gmail.com", "36378912377", TypeClient.PESSOAFISICA);
        Client cli14 = new Client(null, "Diego Bernardes", "diego@gmail.com", "36378912377", TypeClient.PESSOAFISICA);

        cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

        Address e1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
        Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

        clientRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6, cli7, cli8, cli9, cli10, cli11, cli12, cli13, cli14));
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

        ItemOrder ip1 = new ItemOrder(ped1, p1, 0.00, 1, 2000.00);
        ItemOrder ip2 = new ItemOrder(ped1, p3, 0.00, 2, 80.00);
        ItemOrder ip3 = new ItemOrder(ped2, p2, 100.00, 1, 800.00);

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
