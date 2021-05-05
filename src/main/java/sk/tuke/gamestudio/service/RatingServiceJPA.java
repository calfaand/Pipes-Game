package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class RatingServiceJPA implements RatingService{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void setRating(Rating rating)  {//"text":"public.rating","cur":{"from":13,"to":13}}{

        entityManager.createNamedQuery("Rating.delRating").setParameter("game", rating.getGame()).setParameter("player", rating.getPlayer()).executeUpdate();
        entityManager.persist(rating);

    }

    @Override
    public int getAverageRating(String game)  {
        return (int) Math.floor(Math.round((Double) entityManager.createNamedQuery("Rating.getAverageRating").setParameter("game", game).getSingleResult()));

    }

    @Override
    public int getRating(String game, String player){
        return ((Rating)entityManager.createNamedQuery("Rating.getRating").setParameter("game", game).setParameter("player", player).getSingleResult()).getRating();

    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNamedQuery("Rating.resetRating").executeUpdate();
    }
}
