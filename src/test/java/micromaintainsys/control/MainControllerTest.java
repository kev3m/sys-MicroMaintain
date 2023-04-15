package micromaintainsys.control;

import junit.framework.TestCase;
import micromaintainsys.dao.DAO;
import micromaintainsys.model.Tecnico;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MainControllerTest extends TestCase {
    private MainController controller = new MainController();

    @Test
    public void testLoginLogoutAdm() {
        controller.loginTecnico(0, "admin");
        assertNotNull(controller.getTecnicoSessao());
        assertEquals(true, controller.getTecnicoSessao().isAdm());
        controller.logoutTecnico();
        assertNull(controller.getTecnicoSessao());
    }
    @Test
    public void testCriaELogaTecnico(){
        controller.loginTecnico(0, "admin");
        Tecnico tecnico = controller.criaTecnico("Joao", "123");
        assertNotNull(tecnico);
        assertEquals("Joao", tecnico.getNome());
        List<Tecnico> cadastrados = controller.listaTecnicos();
        assertEquals(2, cadastrados.size());
        controller.logoutTecnico();
        controller.loginTecnico(tecnico.getTecnicoID(), "123");
        assertEquals(controller.getTecnicoSessao().getNome(), tecnico.getNome());
        controller.logoutTecnico();

    }
}