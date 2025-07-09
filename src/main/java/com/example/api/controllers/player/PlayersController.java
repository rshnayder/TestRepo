package com.example.api.controllers.player;

import java.util.Map;

import com.example.api.base.BaseController;
import com.example.domain.enums.UserRole;
import com.example.domain.models.Player;
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

    public Response createPlayer(UserRole editor, Player player) {
        return withEditor(editor)
                .withQueryParams(player.toMap())
                .get(GET_CREATE_PLAYER);
    }


    public Response deletePlayer(UserRole editor, PlayerDeleteRequestDto requestDto) {
        return withEditor(editor)
                .withBody(requestDto)
                .delete(DELETE_PLAYER);
    }


    public Response getPlayerDetails(PlayerGetByPlayerIdRequestDto requestDto) {
        return withBody(requestDto).post(POST_GET_PLAYER);
    }


    public Response getPlayerDetails() {
        return get(GET_ALL_PLAYERS);
    }

    public Response updatePlayer(UserRole editor, long id, PlayerUpdateRequestDto requestDto) {
        return withEditor(editor)
                .withId(String.valueOf(id))
                .withBody(requestDto)
                .patch(PATCH_UPDATE_PLAYER);

    }

    public PlayersController withEditor(UserRole editor) {
        return withPathParams(Map.of(EDITOR_PARAM, editor.getRole()));
    }

    public PlayersController withId(String id) {
        return withPathParams(Map.of(ID_PARAM, id));
    }

    @Override
    protected PlayersController getSelf() {
        return this;
    }
}

