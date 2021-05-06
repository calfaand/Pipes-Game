package sk.tuke.gamestudio.game.pipes.service;

import sk.tuke.gamestudio.game.pipes.entity.GamePlay;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class GamePlayServiceJPA implements GamePlayService{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveGame(GamePlay gamePlay) {           //ulozi do db cele stats z fieldu
        entityManager.persist(gamePlay);
    }

    @Override
    public GamePlay loadGame() {
        try{
            return (GamePlay) entityManager.createQuery(
                //tuto nejaky select na get z db  .getSingleResult()
            );

        }
        catch (NoResultException e){
            return null;
        }

    }
}
