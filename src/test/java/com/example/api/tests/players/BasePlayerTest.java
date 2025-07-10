package com.example.api.tests.players;

import com.example.api.controllers.player.PlayersController;
import com.example.api.tests.BaseTest;
import com.players.api.dto.PlayerGetAllResponseDto;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.example.domain.enums.UserRole.SUPERVISOR;

public class BasePlayerTest extends BaseTest {

    protected final String ID_FIELD = "id";
    protected static Long supervisorUserId = 1L; //hardcoded ass getAllPlayersCall response has bug
    protected static final String SUPERVISOR_LOGIN = "supervisor";



    @BeforeMethod
    public void setup() {
//        initializeSupervisorUser();
    }

    private void initializeSupervisorUser() {
        PlayersController.getAllPlayersDetails()
                .as(PlayerGetAllResponseDto.class).getPlayers()
                .stream()
                .filter(player -> player.getRole().equals(SUPERVISOR.getRole()))
                .findFirst()
                .ifPresentOrElse(
                        player -> supervisorUserId = player.getId(),
                        () -> Assert.fail("Supervisor user not found"));
    }

}
