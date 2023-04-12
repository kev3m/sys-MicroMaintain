package micromaintainsys.control;
import java.lang.reflect.Array;
import java.util.*;

import micromaintainsys.dao.DAO;
import micromaintainsys.exceptions.*;
import micromaintainsys.model.*;

/**
 * Controller responsável por prover a interface com o sistema.
 */
public class MainController {
    private Queue<Ordem> ordensAbertas;
    private ArrayList<Ordem> ordensServico;
    private ArrayList<OrdemCompra> ordensCompras;
    private ArrayList<Fatura> faturas;
    //Armazena o ID do tecnico logado no sistema
    private Tecnico tecnicoSessao;
    private Estoque estoque;

    public MainController(){
        this.estoque = criaEstoque();
        ArrayList<Ordem> abertas = DAO.getOrdemDAO().pegaTodasPorStatus(StatusOrdem.Aberta);
        Collections.sort(abertas);
        this.ordensAbertas = new LinkedList<Ordem>(abertas);
        this.ordensServico = new ArrayList<>(DAO.getOrdemDAO().pegaTodas());
        this.faturas = new ArrayList<>(DAO.getFaturaDAO().pegaTodas());
   }
    /*
    Métodos relacionados a TÉCNICOS
    */

    /**
     * Cria um novo técnico.
     * @param nome nome do técnico
     * @param senha senha do técnico
     * @return novo técnico
     * @throws UserNotLoggedInException nenhum usuário logado
     * @throws NotAllowedException usuário não tem permissão para executar
     */
    public Tecnico criaTecnico(String nome, String senha) throws UserNotLoggedInException, NotAllowedException{
        if (this.tecnicoSessao == null){
            throw new UserNotLoggedInException();
        }
        else if (!this.tecnicoSessao.isAdm()){
            throw new NotAllowedException(this.tecnicoSessao.getTecnicoID());
        }
        else{
            return DAO.getTecnicoDAO().cria(nome, senha);
        }
    }

    /**
     * Realiza login do tecnico
     * @param id
     * @param senha
     * @throws InvalidUserException
     * @throws UserAlreadyLoggedInException
     * @throws WrongPasswordException
     */
    public void loginTecnico(int id, String senha) throws
            InvalidUserException,
            UserAlreadyLoggedInException,
            WrongPasswordException{
        /*Não é possível fazer login sem fazer logoff do técnico anterior!*/
        Tecnico loginTecnico = DAO.getTecnicoDAO().pegaPorId(id);
        if (loginTecnico == null){
            throw new InvalidUserException(id);
        }
        else if(this.tecnicoSessao != null){
            throw new UserAlreadyLoggedInException();
        }
        boolean success = DAO.getTecnicoDAO().autentica(id, senha);
        if (success)
             this.tecnicoSessao = loginTecnico;
        else
            throw new WrongPasswordException();
    }

    /**
     * Realiza o log out do técnico.
     * @return
     */
    public boolean logoutTecnico(){
        if (this.tecnicoSessao != null){
            this.tecnicoSessao = null;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Remove um técnico do sistema.
     * @param tecnicoID id do técnico a ser removido
     * @throws UserNotLoggedInException nenhum usuário logado
     * @throws NotAllowedException usuário não tem permissão para executar
     * @throws InvalidUserException usuário a ser removido não existe
     */
    public void removeTecnico(int tecnicoID) throws InvalidUserException, NotAllowedException, UserNotLoggedInException{
        if (this.tecnicoSessao == null){
            throw new UserNotLoggedInException();
        }
        else if (!this.tecnicoSessao.isAdm()){
            throw new NotAllowedException(this.tecnicoSessao.getTecnicoID());
        }
        else if(DAO.getTecnicoDAO().pegaPorId(tecnicoID) == null){
            throw new InvalidUserException(tecnicoID);
        }
        else{
            DAO.getTecnicoDAO().remove(tecnicoID);
        }
    }

    /**
     * Define se o usuário em questão é administrador
     * Apenas administradores podem utilizar.
     * @param tecnicoID
     * @param adm
     * @throws UserNotLoggedInException
     * @throws NotAllowedException
     * @throws InvalidUserException
     */
    public void setAdmTecnico(int tecnicoID, boolean adm) throws
            UserNotLoggedInException,
            NotAllowedException,
            InvalidUserException{
        Tecnico tecnico = DAO.getTecnicoDAO().pegaPorId(tecnicoID);
        if (tecnicoSessao == null) throw new UserNotLoggedInException();
        if (!tecnicoSessao.isAdm()) throw new NotAllowedException(tecnicoID);
        if (tecnico == null) throw new InvalidUserException(tecnicoID);
        tecnico.setAdm(adm);
    }

    public boolean _hasPermission(Tecnico tecnico){
        /*técnico normal tentando manipular objetos de outro técnico*/
        if (this.tecnicoSessao.getTecnicoID() != tecnico.getTecnicoID()
                && !this.tecnicoSessao.isAdm())
            return false;
        return true;
    }
    /*
    Métodos relacionados a CLIENTES
     */

    /**
     * Cria um novo cliente.
     * @param nome nome do cliente
     * @param endereco endereço do cliente
     * @param telefone telefone do cliente
     * @return objeto do novo cliente
     * @throws UserNotLoggedInException nenhum usuário logado
     */
    public Cliente criaCliente(String nome, String endereco, String telefone) throws UserNotLoggedInException{
        if (this.tecnicoSessao == null){
            throw new UserNotLoggedInException();
        }
        return DAO.getClienteDAO().cria(nome, endereco, telefone);
    }

    /**
     * Remove um cliente.
     * @param clienteID id do cliente
     * @return
     * @throws UserNotLoggedInException nenhum usuário logado
     */
    public boolean removeCliente(int clienteID) throws UserNotLoggedInException{
        if (this.tecnicoSessao == null){
            throw new UserNotLoggedInException();
        }
        if (clienteID >= 0 && DAO.getClienteDAO().remove(clienteID)){
            return true;
        }
        return false;
    }

    /*
    Métodos relacionados a Ordens e Serviços
     */
    public Ordem criaOrdem(int clienteID) throws UserNotLoggedInException{
        if (this.tecnicoSessao == null){
            throw new UserNotLoggedInException();
        }
        Ordem novaOrdem = DAO.getOrdemDAO().cria(clienteID);
        this.ordensAbertas.add(novaOrdem);
        return novaOrdem;
    }

    public boolean atribuiOrdem(int tecnicoID) throws UserNotLoggedInException, InvalidUserException, NotAllowedException{
        Tecnico tecnico = DAO.getTecnicoDAO().pegaPorId(tecnicoID);
        Ordem ordem = this.ordensAbertas.poll();
        /*Nenhum usuário logado*/
        if (this.tecnicoSessao == null)
            throw new UserNotLoggedInException();
        /*Técnico a receber a ordem não existe*/
        if (tecnico == null){
            throw new InvalidUserException(tecnicoID);
        }
        /*Usuário normal tentando atribuir ordem a outro usuário*/
        if (!this._hasPermission(tecnico))
            throw new NotAllowedException(this.tecnicoSessao.getTecnicoID());
        /*Técnico já tem ordem em aberto*/
        if (this.tecnicoSessao.getOrdemEmAndamentoID() >= 0)
            return false;
        /*A fila de ordens abertas está vazia*/
        if (ordem == null){
            return false;
        }
        this.tecnicoSessao.setOrdemEmAndamentoID(ordem.getOrdemID());
        ordem.setTecnicoID(this.tecnicoSessao.getTecnicoID());
        ordem.setStatus(StatusOrdem.Andamento);
        DAO.getTecnicoDAO().atualiza(tecnico);
        DAO.getOrdemDAO().atualiza(ordem);
        return true;
    }

    public boolean fechaOrdem(int ordemID) throws UserNotLoggedInException{
        Ordem ordem = DAO.getOrdemDAO().pegaPorId(ordemID);
        Tecnico tecnicoDaOrdem = DAO.getTecnicoDAO().pegaPorId(ordem.getTecnicoID());
        if (this.tecnicoSessao == null)
            throw new UserNotLoggedInException();
        /*Usuário normal tentando atribuir ordem a outro usuário*/
        if (!this._hasPermission(tecnicoDaOrdem))
            throw new NotAllowedException(this.tecnicoSessao.getTecnicoID());
        if (ordem.getStatus() != StatusOrdem.Andamento)
            return false;

        /*Testa se todos os serviços da ordem já foram encerrados*/
        ArrayList<Servico> servicosOrdem = DAO.getServicoDAO().pegaTodosPorOrdemID(ordemID);
        boolean emAberto = false;
        for (Servico servico: servicosOrdem){
            if (servico.foiEncerrado()){
                emAberto = true;
                break;
            }
        }
        if (emAberto) return false;
        else {
            ordem.setStatus(StatusOrdem.Pagamento);
            tecnicoDaOrdem.setOrdemEmAndamentoID(-1);
            DAO.getOrdemDAO().atualiza(ordem);
            return true;
        }
    }
    public Servico criaServico(CategoriaServico categoria, double valor, String peca, String descricao, int ordemID ){
        Ordem ordem = DAO.getOrdemDAO().pegaPorId(ordemID);
        Tecnico tecnicoDaOrdem = DAO.getTecnicoDAO().pegaPorId(ordem.getTecnicoID());
        if (!_hasPermission(tecnicoDaOrdem)){
            throw new NotAllowedException(this.tecnicoSessao.getTecnicoID());
        }
        return DAO.getServicoDAO().cria(categoria, valor, peca, descricao, ordemID);
    }

    public void encerraServico(int servicoID){
        Servico servico = DAO.getServicoDAO().pegaPorId(servicoID);
        Ordem ordem = DAO.getOrdemDAO().pegaPorId(servico.getOrdemID());
        Tecnico tecnicoDaOrdem = DAO.getTecnicoDAO().pegaPorId(ordem.getTecnicoID());

        if (!_hasPermission(tecnicoDaOrdem)){
            throw new NotAllowedException(this.tecnicoSessao.getTecnicoID());
        }
        servico.encerraServico();
        DAO.getServicoDAO().atualiza(servico);
    }

    /*Métodos relacionados a Pagamento e Fatura */
    /**
     * Gera a fatura de uma ordem com o valor igual
     * ao da soma dos serviços cadastrados na ordem
     * @param ordem
     * @return
     */
    public Fatura geraFatura(Ordem ordem){
        if (ordem.getFaturaID() > -1){
            return null;
        }
        int ordemID = ordem.getOrdemID();

        ArrayList<Servico> servicosOrdem = DAO.getServicoDAO().pegaTodosPorOrdemID(ordemID);
        double valorTotal = servicosOrdem.stream().mapToDouble(Servico::getValor).sum();
        Fatura fatura = DAO.getFaturaDAO().cria(ordemID, valorTotal);
        ordem.setFaturaID(fatura.getFaturaID());
        DAO.getOrdemDAO().atualiza(ordem);
        return fatura;
    }
    public Pagamento realizaPagamento(TipoPagamento tipo, double valor, int faturaID){
        Fatura fatura = DAO.getFaturaDAO().pegaPorId(faturaID);
        /*TODO tratar caso de o pagamento ser mais que o necessário (vai ter função troco?)*/
        //double restante = fatura.getValorTotal() - fatura.getValorPago();

        fatura.setValorPago(fatura.getValorPago() + valor);
        Pagamento novoPagamento = DAO.getPagamentoDAO().cria(tipo, valor, faturaID);
        return novoPagamento;
    }

    /*
    Métods relacionados a Estoque/OrdemCompra
     */

    public Estoque criaEstoque(){
        //this.estoque = DAO.getEstoqueDAO().cria()
        return DAO.getEstoqueDAO().cria();
    }

    public void compraPeca(String peca, int quantidade, double valorUnitario){
        OrdemCompra novaOrdem = new OrdemCompra(peca, quantidade, valorUnitario);
        this.estoque.criaOrdemCompra(novaOrdem);
        this.estoque.adicionaPeca(peca, quantidade);
        DAO.getEstoqueDAO().atualiza(estoque);
    }

    /*Métodos relacionados a relatórios*/
    public RelatorioServicos geraRelatorioServicos(Calendar inicio, Calendar fim){
        ArrayList<Servico> servicos = DAO.getServicoDAO().pegaTodosPorDataCriacao(inicio, fim);
        return new RelatorioServicos(servicos);
    }
    public RelatorioCompras geraRelatorioCompras(Calendar inicio, Calendar fim){
        return this.estoque.geraRelatorioCompras(inicio, fim);
    }

}
