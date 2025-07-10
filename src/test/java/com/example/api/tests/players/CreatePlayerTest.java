package com.example.api.tests.players;

import com.example.api.controllers.player.PlayersController;
import com.example.domain.enums.UserRole;
import com.example.domain.models.Player;
import com.example.domain.models.PlayerFactory;
import com.players.api.dto.PlayerCreateResponseDto;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.example.api.controllers.player.PlayersController.createPlayer;
import static com.example.domain.enums.UserRole.*;

public class CreatePlayerTest extends BasePlayerTest {


    private  Player defaultPlayer = PlayerFactory.createDefaultPlayer();

    @DataProvider(name = "AdminAndSupervisorRoles")
    public static Object[][] provideRoles() {
        return new Object[][]{
                {ADMIN},
                {SUPERVISOR}
        };
    }

    @DataProvider(name = "InvalidPlayers")
    public static Object[][] providePlayers() {
        return new Object[][]{
                {PlayerFactory.createPlayerWithAgeRestriction()},
                {PlayerFactory.createPlayerWithInvalidGender()}
        };
    }

    @Test(dataProvider = "AdminAndSupervisorRoles")
    public void testCreateValidPlayer() {
        var response = createPlayer(SUPERVISOR, defaultPlayer);

        response.then().statusCode(200);
        assertions.assertFieldExists(response, ID_FIELD);
        assertions.assertResponseMatchesMappedObjectIgnoringFields(
                response, defaultPlayer, PlayerCreateResponseDto.class, ID_FIELD);
    }

    @Test(dataProvider = "InvalidPlayers")
    public void testCreateInvalidPlayer(Player player) {
        var response = createPlayer(SUPERVISOR, player);
        response.then().statusCode(400);
    }

}
