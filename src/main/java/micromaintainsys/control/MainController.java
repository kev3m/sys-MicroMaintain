package micromaintainsys.control;
import java.util.*;

import micromaintainsys.dao.DAO;
import micromaintainsys.exceptions.*;
import micromaintainsys.model.*;

/**
 * Controller responsável por prover a interface com o sistema.
 */
public class MainController {
    /*TODO
    Implementar método de avaliação do cliente
     */
    private final Queue<Ordem> ordensAbertas;
    private final ArrayList<Ordem> ordensServico;
    private final ArrayList<Fatura> faturas;
    //Armazena o ID do tecnico logado no sistema
    private Tecnico tecnicoSessao;
    private final Estoque estoque;

    public MainController(){
        this.estoque = carregaEstoque();
        ArrayList<Ordem> abertas = DAO.getOrdemDAO().pegaTodasPorStatus(StatusOrdem.Aberta);
        Collections.sort(abertas);
        this.ordensAbertas = new LinkedList<>(abertas);
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
     * @param id id do técnico
     * @param senha senha do técnico
     * @throws InvalidUserException usuário inválido
     * @throws UserAlreadyLoggedInException algum usuário já está logado
     * @throws WrongPasswordException senha errada
     */
    public void loginTecnico(int id, String senha) throws
            InvalidUserException,
            UserAlreadyLoggedInException,
            WrongPasswordException{
        /*Não é possível fazer login sem fazer logoff do técnico anterior!*/
        Tecnico loginTecnico = pegaTecnicoPorId(id);
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
     * @return true se o logout foi realizado com sucesso, false caso não
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
     * Pega um técnico por ID
     * @param tecnicoID id do técnico
     * @return um objeto do tipo Tecnico
     */
    public Tecnico pegaTecnicoPorId(int tecnicoID){
        return DAO.getTecnicoDAO().pegaPorId(tecnicoID);
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
     * @param tecnicoID id do técnico
     * @param adm true se usuário for administrador, false caso contrário
     * @throws UserNotLoggedInException usuário não está logado
     * @throws NotAllowedException operação não permitida
     * @throws InvalidUserException usuário a ser definido como administrador é inválido
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
        DAO.getTecnicoDAO().atualiza(tecnico);
    }

    /**
     * Lista todos os técnicos
     * @return uma lista contendo todos os técnicos cadastrados
     */
    public ArrayList<Tecnico> listaTecnicos(){
        return DAO.getTecnicoDAO().pegaTodos();
    }

    public boolean _hasPermission(Tecnico tecnico){
        /*técnico normal tentando manipular objetos de outro técnico*/
        return this.tecnicoSessao.getTecnicoID() == tecnico.getTecnicoID()
                || this.tecnicoSessao.isAdm();
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
     * @return operação bem sucedida
     * @throws UserNotLoggedInException nenhum usuário logado
     */
    public boolean removeCliente(int clienteID) throws UserNotLoggedInException{
        if (this.tecnicoSessao == null){
            throw new UserNotLoggedInException();
        }
        return clienteID >= 0 && DAO.getClienteDAO().remove(clienteID);
    }
    ArrayList<Cliente>listaClientes(){
        return DAO.getClienteDAO().pegaTodos();
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
        this.ordensServico.add(novaOrdem);
        return novaOrdem;
    }

    /**
     * Atribui a ordem em aberto mais antiga a um técnico.
     * Caso o estoque não seja suficiente para realizar um serviço da ordem,
     * lança uma exceção.
     * @param tecnicoID id do técnico a receber a ordem
     * @return  true se ordem foi atribuída com sucesso, false em outro caso
     * @throws UserNotLoggedInException nenhum usuário logado no sistema
     * @throws InvalidUserException técnico a ter a ordem atribuída é inválido
     * @throws NotAllowedException usuário não tem permissão para realizar a operação
     * @throws ComponentOutOfStockException componente necessário para executar serviço fora de estoque
     * @throws ComponentDoesNotExistException componente necessário para executar serviço não foi cadastrado no estoque
     */
    public boolean atribuiOrdem(int tecnicoID) throws
            UserNotLoggedInException,
            InvalidUserException,
            NotAllowedException,
            AssemblyWithEmptyComponentException,
            ComponentOutOfStockException,
            ComponentDoesNotExistException{

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
        if (tecnico.getOrdemEmAndamentoID() >= 0)
            return false;
        /*A fila de ordens abertas está vazia*/
        if (ordem == null){
            return false;
        }
        /*Verificações de serviço*/
        for (Servico servico: DAO.getServicoDAO().pegaTodosPorOrdemID(ordem.getOrdemID())) {
            String peca = servico.getPeca().toLowerCase();
            /*Serviço tem peça*/
            if (!peca.equals("")) {
                /*Tipo de peça nunca foi comprado*/
                if (!this.estoque.getPecas().containsKey(peca))
                    throw new ComponentDoesNotExistException(peca);
                /*Tipo de peça fora de estoque*/
                if (this.estoque.getPecas().get(peca) == 0)
                    throw new ComponentOutOfStockException(peca);
            }
        }
        tecnico.setOrdemEmAndamentoID(ordem.getOrdemID());
        ordem.setTecnicoID(tecnico.getTecnicoID());
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
            if (!servico.foiEncerrado()){
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
    public ArrayList<Ordem> listaTodasAsOrdens(){
        return ordensServico;
    }

    /**
     *
     * @param categoria categoria do serviço
     * @param valor valor cobrado pelo serviço
     * @param peca  peça utilizada pelo serviço ("" para serviço sem peça)
     * @param descricao descrição do serviço
     * @param ordemID   ID da ordem a que o serviço pertence
     * @return um novo objeto do tipo Servico
     * @throws AssemblyWithEmptyComponentException serviço de montagem sem especificação de peça
     */
    public Servico criaServico(
                                CategoriaServico categoria,
                                double valor,
                                String peca,
                                String descricao,
                                int ordemID) throws
                AssemblyWithEmptyComponentException{
        /*Montagem sem especificação de peça*/
        if (categoria == CategoriaServico.Montagem && peca.equals(""))
            throw new AssemblyWithEmptyComponentException();
        return DAO.getServicoDAO().cria(categoria, valor, peca, descricao, ordemID);
    }

    public void encerraServico(int servicoID){
        Servico servico = DAO.getServicoDAO().pegaPorId(servicoID);
        Ordem ordem = DAO.getOrdemDAO().pegaPorId(servico.getOrdemID());
        Tecnico tecnicoDaOrdem = DAO.getTecnicoDAO().pegaPorId(ordem.getTecnicoID());

        if (_hasPermission(tecnicoDaOrdem)){
            servico.encerraServico();
            DAO.getServicoDAO().atualiza(servico);
        }
        else throw new NotAllowedException(this.tecnicoSessao.getTecnicoID());
    }

    /** Avalia um serviço
     *
     * @param servicoID id do serviço
     * @param avaliacao avaliação do cliente
     * @return true se a operação foi realizada com sucesso, false caso contrário
     */
    public boolean avaliaServico(int servicoID, double avaliacao){
        Servico servico = DAO.getServicoDAO().pegaPorId(servicoID);
        if (servico != null){
            servico.avaliaServico(avaliacao);
            return true;
        }
        return false;
    }

    /*Métodos relacionados a Pagamento e Fatura */
    /**
     * Gera a fatura de uma ordem com o valor igual
     * ao da soma dos serviços cadastrados na ordem
     * @param ordemID ID da ordem
     * @return fatura referente à ordem gerada
     */
    public Fatura geraFatura(int ordemID){
        Ordem ordem = DAO.getOrdemDAO().pegaPorId(ordemID);
        if (ordem.getFaturaID() > -1){
            return null;
        }

        ArrayList<Servico> servicosOrdem = DAO.getServicoDAO().pegaTodosPorOrdemID(ordemID);
        double valorTotal = servicosOrdem.stream().mapToDouble(Servico::getValor).sum();
        Fatura fatura = DAO.getFaturaDAO().cria(ordemID, valorTotal);
        this.faturas.add(fatura);
        ordem.setFaturaID(fatura.getFaturaID());
        DAO.getOrdemDAO().atualiza(ordem);
        return fatura;
    }

    /**
     * Realiza pagamento de uma fatura
     * @param tipo método de pagamento
     * @param valor valor do pagamento
     * @param faturaID ID da fatura a que o pagamento se refere
     * @return um novo objeto do tipo Pagamento
     */
    public Pagamento realizaPagamento(TipoPagamento tipo, double valor, int faturaID){
        Fatura fatura = DAO.getFaturaDAO().pegaPorId(faturaID);
        double totalPago = fatura.getValorPago() + valor;
        double restante = fatura.getValorTotal() - totalPago;
        if(restante < 0)
            throw new PaymentExceedsAmountException(restante);

        fatura.setValorPago(totalPago);
        /*Se todos os pagamentos já foram realizados, encerra a ordem*/
        if (totalPago - fatura.getValorTotal() == 0){
            Ordem ordemPaga = DAO.getOrdemDAO().pegaPorId(fatura.getOrdemID());
            ordemPaga.setStatus(StatusOrdem.Finalizada);
            DAO.getOrdemDAO().atualiza(ordemPaga);
        }
        return DAO.getPagamentoDAO().cria(tipo, valor, faturaID);
    }

    /*
    Métodos relacionados a Estoque/OrdemCompra
     */

    public Estoque carregaEstoque(){
        //this.estoque = DAO.getEstoqueDAO().cria()
        return DAO.getEstoqueDAO().carrega();
    }

    /**
     * Cria uma nova ordem de compra
     * @param peca peça a ser comparada
     * @param quantidade quantidade de peças a ser comprada
     * @param valorUnitario valor individual da peça
     */
    public void compraPeca(String peca, int quantidade, double valorUnitario){
        OrdemCompra novaOrdem = new OrdemCompra(peca, quantidade, valorUnitario);
        this.estoque.criaOrdemCompra(novaOrdem);
        this.estoque.adicionaPeca(peca, quantidade);
        DAO.getEstoqueDAO().atualiza(estoque);
    }

    /*Métodos relacionados a relatórios*/

    /**
     * Gera relatório de serviços
     * @param inicio data de início do relatório
     * @param fim data de fim do relatório
     * @return um novo objeto contendo os dados do relatório de serviços
     */
    public RelatorioServicos geraRelatorioServicos(Calendar inicio, Calendar fim){
        ArrayList<Servico> servicos = DAO.getServicoDAO().pegaTodosPorDataCriacao(inicio, fim);
        return new RelatorioServicos(servicos);
    }

    /**
     * Gera relatório de compras
     * @param inicio data de início do relatório
     * @param fim data de fim do relatório
     * @return um novo objeto contendo os dados do relatório de compras
     */
    public RelatorioCompras geraRelatorioCompras(Calendar inicio, Calendar fim){
        return this.estoque.geraRelatorioCompras(inicio, fim);
    }

    /**
     * Retorna o técnico atualmente logado no sistema
     * @return objeto do tipo técnico
     */
    public Tecnico getTecnicoSessao() {
        return tecnicoSessao;
    }

    /**
     * Lista todas as ordens em aberto
     * @return a fila de ordens
     */
    public Queue<Ordem> getOrdensAbertas(){
        return this.ordensAbertas;
    }

    /**
     * Lista todas as faturas registradas
     * @return a lista de faturas
     */
    public ArrayList<Fatura> getFaturas(){
        return this.faturas;
    }

}
