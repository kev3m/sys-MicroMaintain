package micromaintainsys.dao;

import micromaintainsys.dao.cliente.ClienteDAO;
import micromaintainsys.dao.cliente.ClienteFakeDAO;
import micromaintainsys.dao.tecnico.InterfaceTecnico;
import micromaintainsys.dao.tecnico.TecnicoFakeDAO;

public class DAO {
    private static ClienteDAO clienteDAO;
    private static InterfaceTecnico interfaceTecnico;

    public static ClienteDAO getClienteDAO(){
        DAO.clienteDAO = new ClienteFakeDAO();
        return DAO.clienteDAO;


    }


    public static InterfaceTecnico getTecnicoDAO(){
        if(interfaceTecnico == null){
            interfaceTecnico = new TecnicoFakeDAO();
        }
        return interfaceTecnico;
    }
}
