package micromaintainsys.control;

import junit.framework.TestCase;
import micromaintainsys.exceptions.InvalidUserException;
import micromaintainsys.exceptions.UserAlreadyLoggedInException;
import micromaintainsys.exceptions.WrongPasswordException;
import micromaintainsys.model.*;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;
import java.util.Queue;

public class MainControllerTest extends TestCase {
    /*TODO
        * testar remoção de técnico
        * testar setAdmTecnico
        * testar removeCliente
     */
    private MainController controller = new MainController();

    @Test
    public void testLoginLogoutAdm() {
        controller.loginTecnico(0, "admin");
        assertNotNull(controller.getTecnicoSessao());
        assertTrue(controller.getTecnicoSessao().isAdm());
        boolean sucesso = controller.logoutTecnico();
        assertTrue(sucesso);
        assertNull(controller.getTecnicoSessao());
    }
    @Test
    public void testCriaELogaTecnico(){
        controller.loginTecnico(0, "admin");
        Tecnico tecnicoCriado = controller.criaTecnico("Joao", "123");
        assertNotNull(tecnicoCriado);
        assertEquals("Joao", tecnicoCriado.getNome());
        List<Tecnico> cadastrados = controller.listaTecnicos();
        assertEquals(2, cadastrados.size());
        controller.logoutTecnico();
        controller.loginTecnico(tecnicoCriado.getTecnicoID(), "123");
        assertEquals(controller.getTecnicoSessao().getNome(), tecnicoCriado.getNome());
        controller.logoutTecnico();
    }
    @Test
    public void testSenhaErrada(){
        boolean wrongPass = false;
        try{
            controller.loginTecnico(0, "admino");
        }
        catch (WrongPasswordException e){
            wrongPass = true;
        }
        assertEquals(true, wrongPass);
    }
    @Test
    public void testLoginUsuarioLogado(){
        boolean alreadyLogged = false;
        controller.loginTecnico(0, "admin");
        try{
            controller.loginTecnico(0, "admin");
        }
        catch (UserAlreadyLoggedInException e){
            alreadyLogged = true;
        }
        assertEquals(true, alreadyLogged);
    }
    @Test
    public void testLoginUsuarioInvalido(){
        boolean invalidUser = false;
        try{
            controller.loginTecnico(999, "admin");
        }
        catch (InvalidUserException e){
            invalidUser = true;
        }
        assertEquals(true, invalidUser);
    }
    public void testSistemaCompleto(){
        Calendar inicioProcesso = Calendar.getInstance();
        /*Cria técnico*/
        controller.loginTecnico(0, "admin");
        Tecnico novoTecnico = controller.criaTecnico("Pedro", "senha");
        Queue<Ordem> abertas = controller.getOrdensAbertas();
        controller.logoutTecnico();

        /*Loga no novo técnico e cadastra cliente*/
                controller.loginTecnico(novoTecnico.getTecnicoID(), "senha");
        Cliente clienteCriado = controller.criaCliente("Geraldo", "Rua Nascimento Silva 107", "7199999999");

        /*Compra peça, cadastra ordem e serviços e atribui ao técnico*/
        controller.compraPeca("bateriaBIOS", 50, 0.5);
        Ordem ordemCriada = controller.criaOrdem(clienteCriado.getId());
        Servico servico1 = controller.criaServico(CategoriaServico.Limpeza, 75.5, "", "Limpeza completa", ordemCriada.getOrdemID());
        Servico servico2 = controller.criaServico(CategoriaServico.Montagem, 30, "bateriaBIOS", "Trocar bateria da BIOS", ordemCriada.getOrdemID());

        controller.atribuiOrdem(novoTecnico.getTecnicoID());

        /*Encerra serviços e ordem*/
        controller.encerraServico(servico1.getServicoID());
        controller.encerraServico(servico2.getServicoID());
        controller.fechaOrdem(ordemCriada.getOrdemID());

        /*Gera fatura e faz pagamento*/
        Fatura fatura = controller.geraFatura(ordemCriada.getOrdemID());
        controller.realizaPagamento(TipoPagamento.Dinheiro, 50, fatura.getFaturaID());
        controller.realizaPagamento(TipoPagamento.Pix, 55.5, fatura.getFaturaID());
        assertEquals(StatusOrdem.Finalizada, ordemCriada.getStatus());
        Calendar fimProcesso = Calendar.getInstance();

        /*Testa dados do relatório*/
        RelatorioCompras relatorioCompras = controller.geraRelatorioCompras(inicioProcesso, fimProcesso);
        assertEquals(25.0, relatorioCompras.getValorTotal());
        assertEquals(1, relatorioCompras.getOrdens().size());
        RelatorioServicos relatorioServicos = controller.geraRelatorioServicos(inicioProcesso, fimProcesso);
        assertEquals(2, relatorioServicos.getTotalEncerrados());
        assertEquals(0, relatorioServicos.getTotalEmAberto());
    }

}