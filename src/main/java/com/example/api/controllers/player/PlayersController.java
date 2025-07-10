package com.example.api.controllers.player;

import java.util.Map;

import com.example.api.BaseController;
import com.example.domain.enums.UserRole;
import com.example.domain.models.Player;
import com.example.mapper.PlayersMapper;
import com.players.api.dto.*;
import io.restassured.response.Response;

import static com.example.api.constants.PathParameter.EDITOR_PARAM;
import static com.example.api.constants.PathParameter.ID_PARAM;

public class PlayersController extends BaseController<PlayersController> {

    private static final String GET_CREATE_PLAYER = "/create/{editor}";
    private static final String DELETE_PLAYER = "/delete/{editor}";
    private static final String POST_GET_PLAYER = "/get";
    private static final String GET_ALL_PLAYERS = "/get/all";
    private static final String PATCH_UPDATE_PLAYER = "/update/{editor}/{id}";

    public PlayersController() {
        super("/player");
    }

    public static Response createPlayer(UserRole editor, Player player) {
        return new PlayersController()
                .withPathParams(Map.of("editor", editor.getRole()))
                .withQueryParams(player.toMap())
                .get(GET_CREATE_PLAYER);
    }

    public static Response deletePlayer(UserRole editor, Player player) {
        var requestDto = PlayersMapper.map(player, PlayerDeleteRequestDto.class);

        return new PlayersController()
                .withEditor(editor.getRole())
                .withBody(requestDto)
                .delete(DELETE_PLAYER);
    }

    public static Response getPlayerDetails(Player player) {
        var requestDto = PlayersMapper.map(player, PlayerGetByPlayerIdRequestDto.class);

        return new PlayersController()
                .withBody(requestDto)
                .post(POST_GET_PLAYER);
    }


    public static Response getAllPlayersDetails() {
        return new PlayersController().get(GET_ALL_PLAYERS);
    }

    public static Response updatePlayer(String editor, Long id, Player player) {
        var requestDto = PlayersMapper.map(player, PlayerUpdateRequestDto.class);

        return new PlayersController().withEditor(editor)
                .withIdToBeChanged(id.toString())
                .withBody(requestDto)
                .patch(PATCH_UPDATE_PLAYER);
    }

    public PlayersController withEditor(String editor) {
        return withPathParams(Map.of(EDITOR_PARAM, editor));
    }

    public PlayersController withIdToBeChanged(String id) {
        return withPathParams(Map.of(ID_PARAM, id));
    }

    @Override
    protected  PlayersController getSelf() {
        return this;
    }
}

