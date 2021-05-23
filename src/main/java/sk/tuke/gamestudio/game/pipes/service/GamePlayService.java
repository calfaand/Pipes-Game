package sk.tuke.gamestudio.game.pipes.service;

import sk.tuke.gamestudio.game.pipes.entity.GamePlay;

import java.util.UUID;

public interface GamePlayService {

    void saveGame(GamePlay gamePlay);

    GamePlay loadGame(UUID gameID);

}
