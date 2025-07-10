package com.example.api.tests.players;

import com.example.api.controllers.player.PlayersController;
import com.example.api.tests.BaseTest;
import com.example.domain.enums.UserRole;
import com.example.domain.models.Player;
import com.example.domain.models.PlayerFactory;
import com.players.api.dto.ResponseEntity;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.example.api.controllers.player.PlayersController.*;
import static com.example.domain.enums.UserRole.ADMIN;
import static com.example.domain.enums.UserRole.SUPERVISOR;
import static com.example.domain.models.PlayerFactory.createDefaultPlayer;
import static com.example.domain.models.PlayerFactory.createNotExistingPlayer;
import static com.players.api.dto.ResponseEntity.StatusCodeEnum._204_NO_CONTENT;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class DeletePlayerTest extends BasePlayerTest {

    private Player defaultPlayer;

    @BeforeMethod
    void createUser() {
        defaultPlayer = createDefaultPlayer();
        defaultPlayer = createPlayer(SUPERVISOR, defaultPlayer).as(Player.class);
    }

    @DataProvider(name = "AdminAndSupervisorRoles")
    public static Object[][] provideRoles() {
        return new Object[][]{
                {ADMIN},
                {SUPERVISOR}
        };
    }

    @Test(dataProvider = "AdminAndSupervisorRoles")
    void testDeletePlayer(UserRole userRole) {
        var response = deletePlayer(userRole, defaultPlayer);
        assertions.verifyStatusCode(response, 204);
        assertions.verifyStatusCode(getPlayerDetails(defaultPlayer), 404);
    }

    @Test
    void testDeleteNonExistentPlayer() {
        var nonExistentPlayerId = createNotExistingPlayer(); // Assuming this ID does not exist
        var response = deletePlayer(SUPERVISOR, nonExistentPlayerId);
        assertions.verifyStatusCode(response, 403);
    }



}
