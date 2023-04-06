package micromaintainsys.model;

import java.util.ArrayList;
import java.util.Hashtable;

public class Estoque {
    private Hashtable<String, Integer> pecas;
    private ArrayList<OrdemCompra> ordensCompra;

    public Estoque() {
        pecas = new Hashtable<String,Integer>();
        ordensCompra = new ArrayList<>();
    }

    public Estoque(Hashtable<String, Integer> pecas, ArrayList<OrdemCompra> ordensCompra) {
        this.pecas = pecas;
        this.ordensCompra = ordensCompra;
    }
}
