package pedidosApp.backend;

import org.junit.jupiter.api.Test;
import pedidosApp.backend.entity.Item;
import pedidosApp.backend.entity.Pedido;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testCalculoItem {

    @Test
    public void testCalculoItem() {
        Pedido pedido = new Pedido();
        pedido.setIpi(new BigDecimal("8"));     // 8%
        pedido.setSt(new BigDecimal("5"));      // 5%
        pedido.setMc(new BigDecimal("10"));     // 10%
        pedido.setMc1(new BigDecimal("20"));    // 20%
        pedido.setFrete(new BigDecimal("3"));   // 3%
        pedido.setStvd(new BigDecimal("2"));    // 2%

        Item item = new Item();
        item.setPedido(pedido);
        item.setCusto(new BigDecimal("0,1"));   // custo base
        item.setQuantidade(new BigDecimal("157")); // quantidade

        calculoItem(item); // chama a função que você quer testar

        // Cálculos esperados
        BigDecimal ipi = new BigDecimal("100").multiply(new BigDecimal("0.08")); // 8
        BigDecimal st = new BigDecimal("100").multiply(new BigDecimal("0.05"));  // 5
        BigDecimal custoUnitario = new BigDecimal("100").add(ipi).add(st);       // 113
        BigDecimal custoTotal = custoUnitario.multiply(new BigDecimal("2"));     // 226
        BigDecimal mc = custoUnitario.multiply(new BigDecimal("0.10"));          // 11.30
        BigDecimal mc1 = mc.multiply(new BigDecimal("0.20"));                    // 2.26
        BigDecimal frete = mc.multiply(new BigDecimal("0.03"));                  // 0.339
        BigDecimal vtot = (mc.add(frete)).multiply(new BigDecimal("2"));        // ≈ 23.28
        BigDecimal stvd = (mc1.add(frete)).multiply(new BigDecimal("0.02"));    // ≈ 0.052
        BigDecimal prcf = mc1.add(frete.add(stvd));                              // ≈ 2.67
        BigDecimal vtotf = prcf.multiply(new BigDecimal("2"));                   // ≈ 5.34

        // Validações
        assertEquals(ipi.setScale(4, RoundingMode.HALF_UP), item.getIpi());
        assertEquals(st.setScale(4, RoundingMode.HALF_UP), item.getSt());
        assertEquals(custoUnitario.setScale(4, RoundingMode.HALF_UP), item.getCustoUnitario());
        assertEquals(custoTotal.setScale(4, RoundingMode.HALF_UP), item.getCustoTotal());
        assertEquals(mc.setScale(4, RoundingMode.HALF_UP), item.getMc());
        assertEquals(mc1.setScale(4, RoundingMode.HALF_UP), item.getMc1());
        assertEquals(frete.setScale(4, RoundingMode.HALF_UP), item.getFrete());
        assertEquals(vtot.setScale(4, RoundingMode.HALF_UP), item.getVtot());
        assertEquals(stvd.setScale(4, RoundingMode.HALF_UP), item.getStvd());
        assertEquals(prcf.setScale(4, RoundingMode.HALF_UP), item.getPrcf());
        assertEquals(vtotf.setScale(4, RoundingMode.HALF_UP), item.getVtotf());
    }

    public void calculoItem (Item item) {
        BigDecimal ipi = (item.getPedido().getIpi()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal st = (item.getPedido().getSt()).divide(BigDecimal.valueOf(100),2, RoundingMode.HALF_UP);
        BigDecimal mc = (item.getPedido().getMc()).divide(BigDecimal.valueOf(100),2 , RoundingMode.HALF_UP);
        BigDecimal mc1 = (item.getPedido().getMc1()).divide(BigDecimal.valueOf(100),2 , RoundingMode.HALF_UP);
        BigDecimal frete = (item.getPedido().getFrete()).divide(BigDecimal.valueOf(100),2 , RoundingMode.HALF_UP);
        BigDecimal stvd = (item.getPedido().getStvd()).divide(BigDecimal.valueOf(100),2 , RoundingMode.HALF_UP);

        item.setIpi(item.getCusto().multiply(ipi));
        item.setSt(item.getCusto().multiply(st));
        item.setCustoUnitario(item.getCusto().add(item.getIpi()).add(item.getSt()));
        item.setCustoTotal(item.getCustoUnitario().multiply(item.getQuantidade()));
        item.setMc(item.getCustoUnitario().multiply(mc));
        item.setMc1(item.getMc().multiply(mc1));
        item.setFrete(item.getMc().multiply(frete));
        item.setVtot((item.getMc().add(item.getFrete())).multiply(item.getQuantidade()));
        item.setStvd((item.getMc1().add(item.getFrete())).multiply(stvd));
        item.setPrcf(item.getMc1().add(item.getFrete().add(item.getStvd())));
        item.setVtotf(item.getPrcf().multiply(item.getQuantidade()));
    }
}
