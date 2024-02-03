package oftrs.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import oftrs.model.payment;
import oftrs.model.training;
import oftrs.model.user;

import java.math.BigDecimal;
import java.util.List;

public class DAO<Entity> {
    private EntityManager entityManager;
    private final Class<Entity> entityType;

    public DAO(EntityManager entityManager, Class<Entity> entityType) {
        this.entityManager = entityManager;
        this.entityType = entityType;
    }

    public void insert(Entity entity) {
        if (entity == null) {
            System.out.println("DAO<" + entityType.getSimpleName() + ">: Inserting null ERROR.");
            return;
        }

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();

            System.out.println("DAO<" + entityType.getSimpleName() + ">: Inserting " + entity);
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }

    public void makeTransaction(payment payment) {
        if (payment == null) {
            System.out.println("DAO<" + entityType.getSimpleName() + ">: Inserting null ERROR.");
            return;
        }

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            // Your transaction logic here
            String trainingID = payment.getTrainingid();
            int userID = payment.getUserid();
            BigDecimal paymentAmount = payment.getPaymentamount();
            String paymentType = payment.getPaymenttype();

            String paymentID = userID + "_" + trainingID;
            java.sql.Date paymentDate = new java.sql.Date(System.currentTimeMillis());

            // Check if the training exists and the MaxCapacity allows for the payment
            String trainingQuery = "SELECT 1 FROM OFTRS.Training WHERE TrainingID = :trainingID";

            TypedQuery<Integer> trainingCheck = (TypedQuery<Integer>) entityManager.createNativeQuery(trainingQuery);
            trainingCheck.setParameter("trainingID", trainingID);

            if (trainingCheck.getSingleResult() == 1) {

                // Insert payment
                String insertQuery =
                        "INSERT INTO OFTRS.Payment (UserID, TrainingID, PaymentID, PaymentAmount, PaymentDate, PaymentType) " +
                        "VALUES (:userID, :trainingID, :paymentID, :paymentAmount, :paymentDate, :paymentType)";

                TypedQuery<Integer> insertPayment = (TypedQuery<Integer>) entityManager.createNativeQuery(insertQuery, Integer.class);
                insertPayment.setParameter("userID", userID);
                insertPayment.setParameter("trainingID", trainingID);
                insertPayment.setParameter("paymentID", paymentID);
                insertPayment.setParameter("paymentAmount", paymentAmount);
                insertPayment.setParameter("paymentDate", paymentDate);
                insertPayment.setParameter("paymentType", paymentType);
                insertPayment.executeUpdate();

                // Update MaxCapacity
                String updateQuery =
                        "UPDATE OFTRS.Training SET MaxCapacity = MaxCapacity - 1 " +
                        "WHERE TrainingID = :trainingID AND MaxCapacity > 0";
                TypedQuery<Integer> updateTraining = (TypedQuery<Integer>) entityManager.createNativeQuery(updateQuery);

                updateTraining.setParameter("trainingID", trainingID);
                updateTraining.executeUpdate();

                transaction.commit();
                System.out.println("DAO<" + entityType.getSimpleName() + ">: Inserting " + payment.getClass());
            } else {
                System.out.println("Training not found or MaxCapacity reached.");
                transaction.rollback();
            }
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }


    public Entity update(Entity entity) {
        if (entity == null) {
            System.out.println("DAO<" + entityType.getSimpleName() + ">: Updating null ERROR.");
            return null;
        }

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Entity updatedEntity = entityManager.merge(entity);
            transaction.commit();

            System.out.println("DAO<" + entityType.getSimpleName() + ">: Updating " + entity);
            return updatedEntity;
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            exception.printStackTrace();
            return null;
        }
    }

    public void delete(Entity entity) {
        if (entity == null) {
            System.out.println("DAO<" + entityType.getSimpleName() + ">: Removing null ERROR.");
            return;
        }

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            // Reattach the entity if it is detached
            if (!entityManager.contains(entity)) { entity = entityManager.merge(entity); }
            entityManager.remove(entity);
            transaction.commit();

            System.out.println("DAO<" + entityType.getSimpleName() + ">: Removing " + entity);
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            exception.printStackTrace();
        }
    }

    public Entity get(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Entity entity = entityManager.find(this.entityType, id);
            transaction.commit();

            if (entity == null) {
                System.out.println("DAO: Getting - Item not found.");
            } else {
                System.out.println("DAO<" + entityType.getSimpleName() + ">: Getting " + entity);
            }

            return entity;
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            exception.printStackTrace();
            return null;
        }
    }

    public Entity get(String id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Entity entity = entityManager.find(this.entityType, id);
            transaction.commit();

            if (entity == null) {
                System.out.println("DAO: Getting - Item not found.");
            } else {
                System.out.println("DAO<" + entityType.getSimpleName() + ">: Getting " + entity);
            }

            return entity;
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            exception.printStackTrace();
            return null;
        }
    }

    public List<Entity> getItems() {
        System.out.println("DAO<" + entityType.getSimpleName() + ">: SELECT a FROM " + entityType.getSimpleName() + " AS a");

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            TypedQuery<Entity> query = entityManager.createQuery("SELECT a FROM " + entityType.getSimpleName() + " AS a", entityType);

            List<Entity> resultList = query.getResultList();
            for (Entity entity : resultList) {
                System.out.println("\t" + entity);
            }
            System.out.println();

            transaction.commit();

            return resultList;
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            exception.printStackTrace();
            return null;
        }
    }

    public List<Entity> getItemsByValue(String columnName, String value) {
        System.out.println("DAO<" + entityType.getSimpleName() + ">: SELECT a FROM " + entityType.getSimpleName() + " AS a " + "WHERE (a." + columnName + " = " + value + ")");

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            TypedQuery<Entity> query = entityManager.createQuery("SELECT a FROM " + entityType.getSimpleName() + " AS a " + "WHERE (a." + columnName + "=:val)", entityType);
            query.setParameter("val", value);

            List<Entity> resultList = query.getResultList();
            for (Entity entity : resultList) {
                System.out.println("\t" + entity);
            }
            System.out.println();

            transaction.commit();

            return resultList;
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            exception.printStackTrace();
            return null;
        }
    }

    public List<Entity> getItemsByValue(String columnName, int value) {
        System.out.println("DAO<" + entityType.getSimpleName() + ">: SELECT a FROM " + entityType.getSimpleName() + " AS a " + "WHERE (a." + columnName + " = " + value + ")");

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            TypedQuery<Entity> query = entityManager.createQuery("SELECT a FROM " + entityType.getSimpleName() + " AS a " + "WHERE (a." + columnName + "=:val)", entityType);
            query.setParameter("val", value);

            List<Entity> resultList = query.getResultList();
            for (Entity entity : resultList) {
                System.out.println("\t" + entity);
            }
            System.out.println();

            transaction.commit();

            return resultList;
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            exception.printStackTrace();
            return null;
        }
    }

    public List<Entity> getItemsByTwoValues(String columnName1, String value1, String columnName2, String value2) {
        System.out.println("DAO<" + entityType.getSimpleName() + ">: SELECT a FROM " + entityType.getSimpleName() + " AS a " + "WHERE (a." + columnName1 + " = " + value1 + " AND " + columnName2 + " = " + value2 + ")");

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            TypedQuery<Entity> query = entityManager.createQuery("SELECT a FROM " + entityType.getSimpleName() + " AS a " + "WHERE (a." + columnName1 + "=:val1 AND " + columnName2 + "=:val2)", entityType);
            query.setParameter("val1", value1);
            query.setParameter("val2", value2);

            List<Entity> resultList = query.getResultList();
            for (Entity entity : resultList) {
                System.out.println("\t" + entity);
            }
            System.out.println();

            transaction.commit();

            return resultList;
        } catch (Exception exception) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            exception.printStackTrace();
            return null;
        }
    }
}


