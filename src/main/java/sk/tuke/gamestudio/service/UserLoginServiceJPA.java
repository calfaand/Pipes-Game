package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.UserLogin;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class UserLoginServiceJPA implements UserLoginService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setUserLogin(UserLogin userLogin) {
        entityManager.persist(userLogin);
    }

    @Override
    public String getUserLoginPassword(String username) {
        String pw="";
        try {
            pw= ((UserLogin)entityManager.createNamedQuery("UserLogin.getPassword").setParameter("username", username).getSingleResult()).getPassword();
        }
        catch (NoResultException e){
            System.err.println("neni take meno v db");
        }
        return pw;

    }
    // {username:'miro', password='123456'}


}
