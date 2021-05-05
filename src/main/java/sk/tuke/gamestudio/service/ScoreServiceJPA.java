package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class ScoreServiceJPA implements ScoreService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Score score) {
        entityManager.persist(score);           //persist - > uloz obj do db neprepisuje
    }

    @Override
    public List<Score> getTopScores(String game) {
        return entityManager.createNamedQuery("Score.getTopScores").setMaxResults(5).getResultList();
    }

    @Override
    public void reset() {
        entityManager.createNamedQuery("Score.resetScores").executeUpdate();
    }




}
