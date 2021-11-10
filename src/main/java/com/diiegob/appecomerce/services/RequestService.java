package com.diiegob.appecomerce.services;

import com.diiegob.appecomerce.domain.BilletPayment;
import com.diiegob.appecomerce.domain.ItemOrder;
import com.diiegob.appecomerce.domain.Request;
import com.diiegob.appecomerce.domain.enuns.StatusPayment;
import com.diiegob.appecomerce.repositories.ItemOrderRepository;
import com.diiegob.appecomerce.repositories.PaymentRepository;
import com.diiegob.appecomerce.repositories.ProductRepository;
import com.diiegob.appecomerce.repositories.RequestRepository;
import com.diiegob.appecomerce.services.exceptions.ObjNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repo;
    @Autowired
    private BilletService billetService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ItemOrderRepository itemOrderRepository;

    public Request find(Integer id){
        Optional<Request> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjNotFoundException(
                "Objeto não encontrado! ID: " + id + " , Tipo: "+ Request.class.getName()));
    }

    public Request insert(Request obj){
        obj.setId(null);
        obj.setInstante(new Date());
        obj.getPagamento().setEstado(StatusPayment.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if(obj.getPagamento() instanceof BilletPayment){
            BilletPayment pagto = (BilletPayment) obj.getPagamento();
            billetService.preencherPagamentoComBillet(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        paymentRepository.save(obj.getPagamento());
        for (ItemOrder io: obj.getItens()) {
            io.setDesconto(0.0);
            io.setPreco(productRepository.findById(io.getProduto().getId()).get().getPreco());
            io.setPedido(obj);
        }
        itemOrderRepository.saveAll(obj.getItens());
        return obj;
    }
}
