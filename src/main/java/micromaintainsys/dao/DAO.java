package micromaintainsys.dao;

import micromaintainsys.dao.cliente.ClienteFakeDAO;
import micromaintainsys.dao.cliente.InterfaceCliente;
import micromaintainsys.dao.ordem.InterfaceOrdem;
import micromaintainsys.dao.ordem.OrdemFakeDAO;
import micromaintainsys.dao.servico.InterfaceServico;
import micromaintainsys.dao.servico.ServicoFakeDAO;
import micromaintainsys.dao.tecnico.InterfaceTecnico;
import micromaintainsys.dao.tecnico.TecnicoFakeDAO;

public class DAO {
    private static InterfaceCliente interfaceCliente;
    private static InterfaceTecnico interfaceTecnico;
    private static InterfaceOrdem interfaceOrdem;
    private static InterfaceServico interfaceServico;

    public static InterfaceCliente getClienteDAO(){
        if(interfaceCliente == null){
            interfaceCliente = new ClienteFakeDAO();
        }
        return interfaceCliente;
    }
    public static InterfaceTecnico getTecnicoDAO(){
        if(interfaceTecnico == null){
            interfaceTecnico = new TecnicoFakeDAO();
        }
        return interfaceTecnico;
    }
    public static InterfaceOrdem getOrdemDAO(){
        if(interfaceOrdem == null){
            interfaceOrdem = new OrdemFakeDAO();
        }
        return interfaceOrdem;
    }

    public static InterfaceServico getServicoDAO(){
        if(interfaceServico == null){
            interfaceServico = new ServicoFakeDAO();
        }
        return interfaceServico;
    }
}
