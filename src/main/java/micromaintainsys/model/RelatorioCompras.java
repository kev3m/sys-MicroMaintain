package micromaintainsys.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;

/**
 * Classe que representa um relatório de compras.
 */
public class RelatorioCompras {
    /**
     * Hashtable de peças compradas.
     */
    Hashtable<String, Integer> pecasCompradas = new Hashtable<>();
    /**
     * Lista de ordens de compra.
     */
    ArrayList<OrdemCompra> ordens = new ArrayList<>();
    /**
     * Valor total das ordens de compra.
     */
    double valorTotal = 0;


    /**
     * Construtor do Relatório de Compras
     * Gera o relatório de compras.
     *
     * @param ordensDeCompra lista de Ordens de Compra que alimentam o relatório
     * @param inicio data a partir da qual deve-se considerar as Ordens de Compra
     * @param fim data até a qual deve-se considerar as Ordens de Compra
     */
    public RelatorioCompras(ArrayList<OrdemCompra> ordensDeCompra, Calendar inicio, Calendar fim){
        long inicioMs = inicio.getTimeInMillis();
        long fimMs = fim.getTimeInMillis();

        for (OrdemCompra ordemCompra : ordensDeCompra) {
            long ordemInicioMs = ordemCompra.getDataCriacao().getTimeInMillis();
            if ((ordemInicioMs - inicioMs) >= 0
                    && (fimMs - ordemInicioMs) >= 0) {
                this.ordens.add(ordemCompra);
                this.valorTotal += ordemCompra.getValorUnitario() * ordemCompra.getQuantidade();
                String nomePeca = ordemCompra.getPeca();
                boolean hasKey = pecasCompradas.containsKey(nomePeca);
                if (hasKey) {
                    int novoValor = pecasCompradas.get(nomePeca) + ordemCompra.getQuantidade();
                    this.pecasCompradas.replace(nomePeca, novoValor);
                } else {
                    this.pecasCompradas.put(nomePeca, ordemCompra.getQuantidade());
                }
            }
        }
    }

    /**
     * Obtém a Hashtable de peças compradas.
     * @return Hashtable de peças compradas.
     */
    public Hashtable<String, Integer> getPecasCompradas() {
        return pecasCompradas;
    }
    /**
     * Obtém a lista de ordens de compra.
     * @return Lista de ordens de compra.
     */
    public ArrayList<OrdemCompra> getOrdens() {
        return ordens;
    }
    /**
     * Obtém o valor total das ordens de compra.
     * @return Valor total das ordens de compra.
     */
    public double getValorTotal() {
        return valorTotal;
    }
}
