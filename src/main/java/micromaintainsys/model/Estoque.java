package micromaintainsys.model;

import java.util.ArrayList;
import java.util.Hashtable;

public class Estoque {
    private Hashtable<TipoDePeca, Integer> pecas;
    private ArrayList<OrdemCompra> ordensCompra;

    public Estoque() {
        pecas = new Hashtable<TipoDePeca,Integer>();
        ordensCompra = new ArrayList<>();
    }

    public Estoque(Hashtable<TipoDePeca, Integer> pecas, ArrayList<OrdemCompra> ordensCompra) {
        this.pecas = pecas;
        this.ordensCompra = ordensCompra;
    }
}
