package micromaintainsys.model;

import java.util.ArrayList;

public class Estoque {
    private ArrayList<Peca> pecas;
    private ArrayList<OrdemCompra> ordensCompra;

    public Estoque(ArrayList<Peca> pecas,ArrayList<OrdemCompra> ordensCompra){
        this.pecas = pecas;
        this.ordensCompra = ordensCompra;
    }
}
