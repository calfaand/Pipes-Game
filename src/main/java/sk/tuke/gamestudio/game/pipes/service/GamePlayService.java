package sk.tuke.gamestudio.game.pipes.service;

import sk.tuke.gamestudio.game.pipes.entity.GamePlay;

public interface GamePlayService {

    void saveGame(GamePlay gamePlay);

    GamePlay loadGame();

}
