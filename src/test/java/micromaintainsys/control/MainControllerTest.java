package micromaintainsys.control;

import junit.framework.TestCase;

public class MainControllerTest extends TestCase {
    private MainController controller = new MainController();
    public void testLoginLogoutAdm() {
        controller.loginTecnico(0, "admin");
        assertNotNull(controller.getTecnicoSessao());
        assertEquals(controller.getTecnicoSessao().isAdm(), true);
        controller.logoutTecnico();
        assertNotNull(controller.getTecnicoSessao());
    }
}