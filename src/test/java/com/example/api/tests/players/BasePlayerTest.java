package com.example.api.tests.players;

import com.example.api.controllers.player.PlayersController;
import com.example.api.tests.BaseTest;
import com.example.domain.models.Player;
import com.example.domain.models.PlayerFactory;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public abstract class BasePlayerTest extends BaseTest {

    protected PlayersController playersController = new PlayersController();
}
