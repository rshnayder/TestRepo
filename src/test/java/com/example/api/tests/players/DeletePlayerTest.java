package com.example.api.tests.players;

import com.example.api.controllers.player.PlayersController;
import com.example.api.tests.BaseTest;
import com.example.domain.models.Player;
import com.example.domain.models.PlayerFactory;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeletePlayerTest extends BaseTest {

//
//    @Test
//    public void testCreateDefaultPlayer() {
//        Player defaultPlayer = PlayerFactory.createDefaultPlayer();
//
//        Response response = playersController.createPlayer(defaultPlayer);
//
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
//        Assert.assertNotNull(response.jsonPath().getString("id"), "Player ID should not be null");
//    }
//
//    @Test
//    public void testCreateAdminPlayer() {
//        Player adminPlayer = PlayerFactory.createAdminPlayer();
//
//        Response response = playersController.createPlayer(adminPlayer);
//
//        Assert.assertEquals(response.getStatusCode(), 200);
//        Assert.assertEquals(adminPlayer.getRole(), "admin");
//    }
//
//    @Test
//    public void testCreateCustomPlayerWithInvalidAge() {
//        Player invalidPlayer = PlayerFactory.createCustomPlayer(10, "male", "user");
//
//        Response response = playersController.createPlayer(invalidPlayer);
//
//        Assert.assertEquals(response.getStatusCode(), 400);
//    }
}
