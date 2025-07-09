package com.example.api.tests.players;

import com.example.api.controllers.player.PlayersController;
import com.example.assertions.Assertions;
import com.example.domain.models.Player;
import com.example.domain.models.PlayerFactory;
import com.example.api.tests.BaseTest;
import com.players.api.dto.PlayerCreateResponseDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.example.domain.enums.UserRole.ADMIN;

public class CreatePlayerTest extends BasePlayerTest {
    
    @Test
    public void testCreateDefaultPlayer() {
        Player defaultPlayer = PlayerFactory.createDefaultPlayer();

        Response response = playersController.createPlayer(ADMIN, defaultPlayer);

        Assertions.assertStatusCode(response, 200);
    }


}
