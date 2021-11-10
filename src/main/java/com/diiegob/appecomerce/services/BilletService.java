package com.diiegob.appecomerce.services;

import com.diiegob.appecomerce.domain.BilletPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BilletService {

    public void preencherPagamentoComBillet(BilletPayment pagto, Date instanteDoPedido){
        Calendar cal = Calendar.getInstance();
        cal.setTime(instanteDoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(cal.getTime());
    }
}
