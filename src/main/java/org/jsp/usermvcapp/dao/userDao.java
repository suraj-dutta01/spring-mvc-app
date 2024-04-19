package org.jsp.usermvcapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.jandex.TypeTarget.Usage;
import org.jsp.usermvcapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Repository;

@Repository
public class userDao {
	@Autowired
	private EntityManager entityManager;
	
	public User saveUser(User user) {
		EntityTransaction transaction=entityManager.getTransaction();
		entityManager.persist(user);
		transaction.begin();
        transaction.commit();
        return user;
	}
	public User updateUser(User user) {
		EntityTransaction transaction=entityManager.getTransaction();
		User dbUser=entityManager.find(User.class, user.getId());
		if(dbUser!=null) {
			dbUser.setName(user.getName());
			dbUser.setPhone(user.getPhone());
			dbUser.setEmail(user.getEmail());
			dbUser.setPassword(user.getPassword());
			transaction.begin();
            transaction.commit();
            return dbUser;
		}
		return null;
	}
	public User findById(int id) {
		return entityManager.find(User.class, id);
	}
    public User verifyUser(int id,String password) {
        String qry="select u from User u where u.id=?1 and u.password=?2";
        Query q= entityManager.createQuery(qry);
        q.setParameter(1,id);
        q.setParameter(2,password);
       try {
    	   User user=(User) q.getSingleResult();
    	   return user;
	} catch (NoResultException e) {
		   return null;
	       }   
    }
    public User verifyUser(long phone,String password) {
        String qry="select u from User u where u.phone=?1 and u.password=?2";
        Query q= entityManager.createQuery(qry);
        q.setParameter(1,phone);
        q.setParameter(2,password);
       try {
    	   User user=(User) q.getSingleResult();
    	   return user;
	} catch (NoResultException e) {
		   return null;
	       }   
    }
    public User verifyUser(String email,String password) {
        String qry="select u from User u where u.email=?1 and u.password=?2";
        Query q= entityManager.createQuery(qry);
        q.setParameter(1,email);
        q.setParameter(2,password);
       try {
    	   User user=(User) q.getSingleResult();
    	   return user;
	} catch (NoResultException e) {
		   return null;
	       }   
    }
    public boolean deleteUser(int id) {
    	EntityTransaction transaction=entityManager.getTransaction();
    	User user=entityManager.find(User.class,id);
    	if(user!=null) {
    		System.out.println("99999999999999999999999999999");
    		entityManager.remove(user);
    		transaction.begin();
            transaction.commit();
            System.out.println("tttttttttttttttttttttttttttttttt");
            return true;
    	}
    	return false;
    }
    
}
