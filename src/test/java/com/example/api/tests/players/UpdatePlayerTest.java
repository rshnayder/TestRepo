package com.example.api.tests.players;

import com.example.api.controllers.player.PlayersController;
import com.example.domain.enums.UserRole;
import com.example.domain.models.Player;
import com.example.domain.models.PlayerFactory;
import org.testng.annotations.*;

import static com.example.api.controllers.player.PlayersController.createPlayer;
import static com.example.api.controllers.player.PlayersController.getPlayerDetails;
import static com.example.domain.enums.UserRole.SUPERVISOR;
import static com.example.domain.models.PlayerFactory.createDefaultPlayer;
import static com.example.domain.models.PlayerFactory.createNotExistingPlayer;

public class UpdatePlayerTest extends BasePlayerTest {

    private static Player defaultPlayer = createDefaultPlayer();
    private static Player adminPlayer = PlayerFactory.createAdminPlayer();

    @DataProvider(name = "editorLogins")
    public static Object[][] provideRoles() {
        return new Object[][]{{adminPlayer.getLogin()}, {SUPERVISOR_LOGIN}, {defaultPlayer.getLogin()},};
    }

    @BeforeClass
    void createUser() {
        var adminResp = createPlayer(SUPERVISOR, adminPlayer).as(Player.class);
        adminPlayer.setId(adminResp.getId()); //done that way due to the bug in the getAllPlayersCall response
        adminPlayer.setPassword(adminResp.getPassword()); //done that way due to the bug in the getAllPlayersCall response
        var defaultPlayerResp = createPlayer(SUPERVISOR, defaultPlayer).as(Player.class);
        defaultPlayer.setId(defaultPlayerResp.getId());
        defaultPlayer.setPassword(defaultPlayerResp.getPassword());
    }

    @Test(dataProvider = "editorLogins")
    void testUpdateExistingUser(String editorLogin) {
        var updatedPlayer = defaultPlayer;
        updatedPlayer.setAge(defaultPlayer.getAge() + 1);
        var response = PlayersController.updatePlayer(editorLogin, defaultPlayer.getId(), updatedPlayer);
        assertions.verifyStatusCode(response, 200);
        assertions.assertEquals(response.as(Player.class), updatedPlayer);
    }

    @Test
    void testUpdateAdminByRegularUser() {
        var updatedAdmin = adminPlayer;
        updatedAdmin.setRole(UserRole.USER.getRole());
        var response = PlayersController.updatePlayer(defaultPlayer.getLogin(), adminPlayer.getId(), updatedAdmin);
        assertions.verifyStatusCode(response, 403);
    }

    @AfterClass
    public void removeCreatedAdmin() {
        PlayersController.deletePlayer(SUPERVISOR, adminPlayer);
    }
}
