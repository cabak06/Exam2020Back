package utils;


import entities.Role;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "test");
    User user1 = new User("user1", "test1");
    User user2 = new User("user2", "test2");
    User admin = new User("admin", "test");
    User both = new User("user_admin", "test");

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role userRole1 = new Role("user1");
    Role userRole2 = new Role("user2");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    user1.addRole(userRole);
    user2.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    em.persist(userRole);
    em.persist(userRole1);
    em.persist(userRole2);
    em.persist(adminRole);
    em.persist(user);
    em.persist(user1);
    em.persist(user2);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
