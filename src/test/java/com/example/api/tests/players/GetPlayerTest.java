package com.example.api.tests.players;

import com.example.api.controllers.player.PlayersController;
import com.example.api.tests.BaseTest;
import com.example.domain.models.Player;
import com.example.domain.models.PlayerFactory;
import com.players.api.dto.PlayerGetAllResponseDto;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.example.api.controllers.player.PlayersController.*;
import static com.example.domain.enums.UserRole.*;
import static com.example.domain.models.PlayerFactory.createDefaultPlayer;
import static com.example.domain.models.PlayerFactory.createNotExistingPlayer;

public class GetPlayerTest extends BaseTest {

    private Player defaultPlayer;

    @BeforeMethod
    void createUser() {
        defaultPlayer = createDefaultPlayer();
        defaultPlayer = createPlayer(SUPERVISOR, defaultPlayer).as(Player.class);
    }

    @Test
    void testGetExistingUser() {
        var response = PlayersController.getPlayerDetails(defaultPlayer);
        response.then().statusCode(200);
        Assertions.assertThat(response.as(Player.class)).isEqualTo(defaultPlayer);
    }

    @Test
    void testDeleteNonExistentPlayer() {
        var nonExistentPlayerId = createNotExistingPlayer();
        var response = getPlayerDetails(nonExistentPlayerId);
        assertions.verifyStatusCode(response, 404);
    }

    @Test
    void testGetListOfAllUsers() {
        Response response = PlayersController.getAllPlayersDetails();
        var responseDto = response.body().as(PlayerGetAllResponseDto.class);
        assertions.verifyStatusCode(response, 200);
        assertions.assertNotNull(responseDto);
        var firstPlayer = responseDto.getPlayers().get(0);
        assertions.assertThatAllFieldsAreNotNull(firstPlayer);
    }
}
