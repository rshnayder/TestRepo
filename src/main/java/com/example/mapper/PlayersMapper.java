package com.example.mapper;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.domain.models.Player;
import com.players.api.dto.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class PlayersMapper {

    static ModelMapper mapper = new ModelMapper();

    static {
        mapper.typeMap(Player.class, PlayerCreateResponseDto.class);

        mapper.typeMap(Player.class, PlayerDeleteRequestDto.class).addMappings(m ->
              m.map(Player::getId, PlayerDeleteRequestDto::setPlayerId)
        );

        mapper.typeMap(Player.class, PlayerGetByPlayerIdRequestDto.class).addMappings(m ->
             m.map(Player::getId, PlayerGetByPlayerIdRequestDto::setPlayerId)
        );
    }

    public static <T> T map(Player player, Class<T> clazz) {
        return mapper.map(player, clazz);
    }

    public static <T> List<T> mapList(List<Player> players, Class<T> clazz) {
        return players.stream()
                .map(player -> mapper.map(player, clazz))
                .collect(Collectors.toList());
    }
}
