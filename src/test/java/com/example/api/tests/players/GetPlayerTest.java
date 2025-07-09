package com.example.api.tests.players;

import com.example.api.controllers.player.PlayersController;
import com.example.api.tests.BaseTest;
import com.example.domain.models.Player;
import com.example.domain.models.PlayerFactory;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetPlayerTest extends BaseTest {


//    @Test
//    public void testCreateCustomPlayerWithInvalidAge() {
//        Player invalidPlayer = PlayerFactory.createCustomPlayer(10, "male", "user");
//
//        Response response = playersController.createPlayer(invalidPlayer);
//
//        Assert.assertEquals(response.getStatusCode(), 400);
//    }
}
