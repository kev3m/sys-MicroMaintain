package micromaintainsys.dao;

import micromaintainsys.dao.cliente.ClienteDAO;
import micromaintainsys.dao.cliente.InterfaceCliente;
import micromaintainsys.dao.estoque.EstoqueDAO;
import micromaintainsys.dao.estoque.InterfaceEstoque;
import micromaintainsys.dao.fatura.FaturaDAO;
import micromaintainsys.dao.fatura.InterfaceFatura;
import micromaintainsys.dao.ordem.InterfaceOrdem;
import micromaintainsys.dao.ordem.OrdemDAO;
import micromaintainsys.dao.pagamento.InterfacePagamento;
import micromaintainsys.dao.pagamento.PagamentoDAO;
import micromaintainsys.dao.servico.InterfaceServico;
import micromaintainsys.dao.servico.ServicoDAO;
import micromaintainsys.dao.tecnico.InterfaceTecnico;
import micromaintainsys.dao.tecnico.TecnicoDAO;

public class DAO {
    private static InterfaceCliente interfaceCliente;
    private static InterfaceTecnico interfaceTecnico;
    private static InterfaceOrdem interfaceOrdem;
    private static InterfaceServico interfaceServico;
    private static InterfaceFatura interfaceFatura;
    private static InterfacePagamento interfacePagamento;
    private static InterfaceEstoque interfaceEstoque;

    public static InterfaceCliente getClienteDAO(){
        if(interfaceCliente == null){
            interfaceCliente = new ClienteDAO();
        }
        return interfaceCliente;
    }
    public static InterfaceTecnico getTecnicoDAO(){
        if(interfaceTecnico == null){
            interfaceTecnico = new TecnicoDAO();
        }
        return interfaceTecnico;
    }
    public static InterfaceOrdem getOrdemDAO(){
        if(interfaceOrdem == null){
            interfaceOrdem = new OrdemDAO();
        }
        return interfaceOrdem;
    }

    public static InterfaceServico getServicoDAO(){
        if(interfaceServico == null){
            interfaceServico = new ServicoDAO();
        }
        return interfaceServico;
    }

    public static InterfaceFatura getFaturaDAO(){
        if(interfaceFatura == null){
            interfaceFatura = new FaturaDAO();
        }
        return interfaceFatura;
    }

    public static InterfacePagamento getPagamentoDAO(){
        if(interfacePagamento == null){
            interfacePagamento = new PagamentoDAO();
        }
        return interfacePagamento;
    }
    public static InterfaceEstoque getEstoqueDAO(){
        if(interfaceEstoque == null){
            interfaceEstoque = new EstoqueDAO();
        }
        return interfaceEstoque;
    }

    /*Função para ser utilizada nos testes*/
    public static void _limpaDAOs(){
        interfaceCliente = null;
        interfaceTecnico = null;
        interfaceOrdem = null;
        interfaceServico = null;
        interfaceFatura = null;
        interfacePagamento = null;
        interfaceEstoque = null;
    }
}
