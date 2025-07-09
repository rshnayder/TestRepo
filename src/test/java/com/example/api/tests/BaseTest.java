package com.example.api.tests;

import com.example.api.controllers.player.PlayersController;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    protected PlayersController playersController;

    public BaseTest() {
        this.playersController = new PlayersController();
    }


}
