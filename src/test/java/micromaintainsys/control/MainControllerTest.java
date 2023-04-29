package micromaintainsys.control;

import junit.framework.TestCase;
import micromaintainsys.dao.DAO;
import micromaintainsys.exceptions.InvalidUserException;
import micromaintainsys.exceptions.UserAlreadyLoggedInException;
import micromaintainsys.exceptions.WrongPasswordException;
import micromaintainsys.model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainControllerTest extends TestCase {
    private final MainController controller = new MainController();

    public void testPersistence(){
        controller.loginTecnico(0, "admin");
        int cid1 = controller.criaCliente("Adalberto", "Rua dos Bobos, n. 0", "171171171").getId();

        /*O método _limpaDAOs atribui null a cada variável que armazena os DAOs específicos
        Então, quando o controller chama o DAO outra vez, ele cria uma nova instância
        do DAO específico, e carrega os arquivos outra vez.*/
        DAO._limpaDAOs();

        int cid2 = controller.criaCliente("Outro Cliente", "Outro lugar", "Outro telefone").getId();
        ArrayList<Cliente> clientes = controller.listaClientes();
        Cliente c1 = null;
        Cliente c2 = null;
        for (Cliente cliente: clientes){
            if (cid1 == cliente.getId()){
                c1 = cliente;
            }
            if (cid2 == cliente.getId()){
                c2 = cliente;
            }
        }
        assertNotNull(c1);
        assertNotNull(c2);
        assertEquals(c1.getName(), "Adalberto");
        assertEquals(c2.getName(), "Outro Cliente");
        assert(cid1 < cid2);
    }
    public void testLoginLogoutAdm() {
        controller.loginTecnico(0, "admin");
        assertNotNull(controller.getTecnicoSessao());
        assertTrue(controller.getTecnicoSessao().isAdm());
        boolean sucesso = controller.logoutTecnico();
        assertTrue(sucesso);
        assertNull(controller.getTecnicoSessao());
    }

    public void testCriaELogaTecnico(){
        controller.loginTecnico(0, "admin");
        int tecnicosAntes = controller.listaTecnicos().size();
        Tecnico tecnicoCriado = controller.criaTecnico("Joao", "123");
        assertNotNull(tecnicoCriado);
        assertEquals("Joao", tecnicoCriado.getNome());

        assertEquals(tecnicosAntes+1, controller.listaTecnicos().size());
        controller.logoutTecnico();
        controller.loginTecnico(tecnicoCriado.getTecnicoID(), "123");
        assertEquals(controller.getTecnicoSessao().getNome(), tecnicoCriado.getNome());
        controller.logoutTecnico();
    }

    public void testSenhaErrada(){
        boolean wrongPass = false;
        try{
            controller.loginTecnico(0, "admino");
        }
        catch (WrongPasswordException e){
            wrongPass = true;
        }
        assertTrue(wrongPass);
    }

    public void testLoginUsuarioLogado(){
        boolean alreadyLogged = false;
        controller.loginTecnico(0, "admin");
        try{
            controller.loginTecnico(0, "admin");
        }
        catch (UserAlreadyLoggedInException e){
            alreadyLogged = true;
        }
        assertTrue(alreadyLogged);
    }

    public void testLoginUsuarioInvalido(){
        boolean invalidUser = false;
        try{
            controller.loginTecnico(999, "admin");
        }
        catch (InvalidUserException e){
            invalidUser = true;
        }
        assertTrue(invalidUser);
    }

    public void testRemoveTecnico(){
        controller.loginTecnico(0, "admin");
        int novoTecnicoID = controller.criaTecnico("Paulo", "456").getTecnicoID();
        controller.removeTecnico(novoTecnicoID);
        controller.logoutTecnico();
        boolean removido = false;
        try{
            controller.loginTecnico(novoTecnicoID, "456");
        }
        catch(InvalidUserException e){
            removido = true;
        }
        assertTrue(removido);
    }
    public void testAtribuicaoAdm(){
        controller.loginTecnico(0, "admin");
        int novoTecnicoID = controller.criaTecnico("José", "123").getTecnicoID();
        controller.setAdmTecnico(novoTecnicoID, true);
        controller.logoutTecnico();
        controller.loginTecnico(novoTecnicoID, "123");
        assertTrue(controller.getTecnicoSessao().isAdm());
    }

    public void testeCriaERemoveCliente(){
        controller.loginTecnico(0, "admin");
        int nClientes = controller.listaClientes().size();
        int novoClienteID = controller.criaCliente("Ana", "Rua dos Bobos n. 0", "000000000").getId();
        assertEquals(nClientes + 1, controller.listaClientes().size());
        boolean resRemocao = controller.removeCliente(novoClienteID);
        assertTrue(resRemocao);
        assertEquals(nClientes, controller.listaClientes().size());
    }
    public void testCriacaoTecnicoAtePagamentoFatura(){
        Calendar inicioProcesso = Calendar.getInstance();
        /*Cria técnico*/
        controller.loginTecnico(0, "admin");
        Tecnico novoTecnico = controller.criaTecnico("Pedro", "senha");
        controller.logoutTecnico();

        /*Loga no novo técnico e cadastra cliente*/
        controller.loginTecnico(novoTecnico.getTecnicoID(), "senha");
        Cliente clienteCriado = controller.criaCliente("Geraldo", "Rua Nascimento Silva 107", "7199999999");

        /*Compra peça, cadastra ordem e serviços e atribui ao técnico*/
        controller.compraPeca("bateriaBIOS", 50, 0.5);
        int totalOrdens = controller.listaTodasAsOrdens().size();
        int ordensAbertas = controller.getOrdensAbertas().size();
        Ordem ordemCriada = controller.criaOrdem(clienteCriado.getId());
        assertEquals(totalOrdens+1, controller.listaTodasAsOrdens().size());
        assertEquals(ordensAbertas + 1, controller.getOrdensAbertas().size());
        Servico servico1 = controller.criaServico(CategoriaServico.Limpeza, 75.5, "", "Limpeza completa", ordemCriada.getOrdemID());
        Servico servico2 = controller.criaServico(CategoriaServico.Montagem, 30, "bateriaBIOS", "Trocar bateria da BIOS", ordemCriada.getOrdemID());

        boolean resAtribuicao = controller.atribuiOrdem(novoTecnico.getTecnicoID());
        assertTrue(resAtribuicao);
        assertEquals(StatusOrdem.Andamento, ordemCriada.getStatus());
        /*Encerra serviços e ordem*/
        controller.encerraServico(servico1.getServicoID());
        controller.encerraServico(servico2.getServicoID());
        boolean resAvaliacaoS1 = controller.avaliaServico(servico1.getServicoID(), 8);
        boolean resAvaliacaoS2 = controller.avaliaServico(servico2.getServicoID(), 2);
        assertTrue(resAvaliacaoS1);
        assertTrue(resAvaliacaoS2);

        boolean resFechamento = controller.fechaOrdem(ordemCriada.getOrdemID());
        assertTrue(resFechamento);

        /*Gera fatura e faz pagamento*/
        Fatura fatura = controller.geraFatura(ordemCriada.getOrdemID());
        Pagamento pagamento1 = controller.realizaPagamento(TipoPagamento.Dinheiro, 50, fatura.getFaturaID());
        Pagamento pagamento2 = controller.realizaPagamento(TipoPagamento.Pix, 55.5, fatura.getFaturaID());
        assertNotNull(pagamento1);
        assertNotNull(pagamento2);
        assertEquals(StatusOrdem.Finalizada, ordemCriada.getStatus());
        Calendar fimProcesso = Calendar.getInstance();

        /*Testa dados do relatório*/
        RelatorioCompras relatorioCompras = controller.geraRelatorioCompras(inicioProcesso, fimProcesso);
        assertEquals(25.0, relatorioCompras.getValorTotal());
        assertEquals(1, relatorioCompras.getOrdens().size());
        RelatorioServicos relatorioServicos = controller.geraRelatorioServicos(inicioProcesso, fimProcesso);
        assertEquals(2, relatorioServicos.getTotalEncerrados());
        assertEquals(0, relatorioServicos.getTotalEmAberto());
        assertEquals(2, relatorioServicos.getTotalAvaliacoes());
        assertEquals(5.0, relatorioServicos.getMediaAvaliacoes());
    }
}