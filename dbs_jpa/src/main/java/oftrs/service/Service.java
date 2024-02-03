package oftrs.service;

import jakarta.persistence.EntityManager;
import oftrs.dao.DAO;
import oftrs.model.payment;
import oftrs.model.trainer;
import oftrs.model.training;
import oftrs.model.user;

import java.util.Date;

public class Service {
    private final EntityManager entityManager;
    public Service(EntityManager entityManager) { this.entityManager = entityManager; }

    // CREATE SECTION

    /**
     * <h3>createUser</h3>
     * Creates a new user in the database
     **/
    public void createUser(user user) {
        DAO<oftrs.model.user> userDAO = new DAO<>(this.entityManager, oftrs.model.user.class);
        userDAO.insert(user);
    }

    /**
     * <h3>createTrainer</h3>
     * Creates a new trainer in the database
     **/
    public void createTrainer(trainer trainer) {
        DAO<oftrs.model.trainer> trainerDAO = new DAO<>(this.entityManager, oftrs.model.trainer.class);
        trainerDAO.insert(trainer);
    }

    /**
     * <h3>createTraining</h3>
     * Creates a new training in the database
     **/
    public void createTraining(training training) {
        DAO<oftrs.model.training> trainingDAO = new DAO<>(this.entityManager, oftrs.model.training.class);
        trainingDAO.insert(training);
    }

    /**
     * <h3>makePayment</h3>
     * Makes a payment for a user on corresponding training.
     **/
    public void makePayment(payment payment) {
        DAO<oftrs.model.payment> paymentDAO = new DAO<>(this.entityManager, oftrs.model.payment.class);
        paymentDAO.insert(payment);
    }

    // DELETE SECTION

    /**
     * <h3>deleteUser</h3>
     * Deletes a specified user in the database
     **/
    public void deleteUser(user user) {
        DAO<oftrs.model.user> userDAO = new DAO<>(this.entityManager, oftrs.model.user.class);
        userDAO.delete(user);
    }

    /**
     * <h3>deleteTrainer</h3>
     * Deletes a specified trainer in the database
     **/
    public void deleteTrainer(trainer trainer) {
        DAO<oftrs.model.trainer> trainerDAO = new DAO<>(this.entityManager, oftrs.model.trainer.class);
        trainerDAO.delete(trainer);
    }

    /**
     * <h3>deleteTraining</h3>
     * Deletes a specified training in the database
     **/
    public void deleteTraining(training training) {
        DAO<oftrs.model.training> trainingDAO = new DAO<>(this.entityManager, oftrs.model.training.class);
        trainingDAO.delete(training);
    }

    /**
     * <h3>deletePayment</h3>
     * Deletes a specified payment in the database.
     **/
    public void deletePayment(payment payment) {
        DAO<oftrs.model.payment> paymentDAO = new DAO<>(this.entityManager, oftrs.model.payment.class);
        paymentDAO.delete(payment);
    }


    // --- TRANSACTION ---

    public void handleTransaction(payment payment) {
        DAO<oftrs.model.payment> paymentDAO = new DAO<>(this.entityManager, oftrs.model.payment.class);
        paymentDAO.makeTransaction(payment);
    }


    // ----- GETTERS -----
    public EntityManager getEntityManager() { return entityManager; }
}
