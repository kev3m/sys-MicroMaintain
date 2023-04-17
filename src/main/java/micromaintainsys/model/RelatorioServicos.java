package micromaintainsys.model;

import java.util.ArrayList;

/**
 Classe que representa um relatório de serviços.
 */
public class RelatorioServicos {
    /**
     Lista de serviços que compõem o relatório.
     */
    ArrayList<Servico> servicos = new ArrayList<>();
    /**
     Valor total de espera em milisegundos.
     */
    long esperaTotal = 0;
    /**
     Valor médio de espera em milisegundos.
     */
    long mediaEspera = 0;
    /**
     Total de serviços encerrados.
     */
    int totalEncerrados = 0;
    /**
     Media de avaliações.
     */
    double mediaAvaliacoes = 0;
    /**
     Total de serviços avaliados.
     */
    int totalAvaliacoes = 0;
    /**
     Total de serviços em aberto.
     */
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
    /**
     *
     * @return lista de serviços que compõem o relatório
     */
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
    /**
     *
     * @return total de serviços encerrados
     */
    public int getTotalEncerrados() {
        return totalEncerrados;
    }
    /**
     *
     * @return média de avaliações
     */
    public double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }
    /**
     *
     * @return total de serviços avaliados
     */
    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }
    /**
     *
     * @return total de serviços em aberto
     */
    public int getTotalEmAberto() {
        return totalEmAberto;
    }
}
