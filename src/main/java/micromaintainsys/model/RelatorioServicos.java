package micromaintainsys.model;

import java.util.ArrayList;

public class RelatorioServicos {
    ArrayList<Servico> servicos = new ArrayList<>();
    long esperaTotal = 0;
    long mediaEspera = 0;
    int totalEncerrados = 0;
    double mediaAvaliacoes = 0;
    int totalAvaliacoes = 0;
    int totalEmAberto = 0;

    /**
     *
     * @param servicos lista de serviços que compõem o relatório
     */
    public RelatorioServicos(ArrayList<Servico> servicos){
        double agregadoAvaliacoes = 0;
        this.servicos = servicos;
        for (Servico servico: servicos){
            if (servico.getHorarioFinalizacao() == null){
                this.totalEmAberto++;
            }
            else{
                this.totalEncerrados++;
                if (servico.foiAvaliado()){
                    this.totalAvaliacoes++;
                    agregadoAvaliacoes += servico.getAvaliacaoCliente();
                    this.esperaTotal += (servico.getHorarioFinalizacao().getTimeInMillis()
                        - servico.getHorarioAbertura().getTimeInMillis());

                }
            }
        }
        this.mediaAvaliacoes = agregadoAvaliacoes/totalAvaliacoes;
        this.mediaEspera = this.esperaTotal/this.totalEncerrados;
    }

    public ArrayList<Servico> getServicos() {
        return servicos;
    }

    /**
     *
     * @return valor total de espera em milisegundos
     */
    public long getEsperaTotal() {
        return esperaTotal;
    }

    /**
     *
     * @return tempo médio de espera em segundos
     */
    public long getMediaEspera() {
        return mediaEspera;
    }

    public int getTotalEncerrados() {
        return totalEncerrados;
    }

    public double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public int getTotalEmAberto() {
        return totalEmAberto;
    }
}
