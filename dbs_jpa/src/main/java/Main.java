import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import oftrs.controller.Controller;
import oftrs.dao.DAO;
import oftrs.model.payment;
import oftrs.model.trainer;
import oftrs.model.training;
import oftrs.model.user;
import oftrs.service.Service;

import java.math.BigDecimal;

public class Main {
    private static final Controller CONTROLLER = new Controller();
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("main");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Service service = new Service(entityManager);

        try (entityManagerFactory; entityManager) {

            // Data Specification and Creation
            user test_user = CONTROLLER.prepareNewUser(
                    226226,
                    "Test",
                    "Test",
                    "test@test.me",
                    "+421226351275",
                    "test"
            );

            trainer test_trainer = CONTROLLER.prepareNewTrainer(
                    test_user,
                    999999,
                    "MMA",
                    "MMA Intermediate",
                    "2025-03-03"
            );

            training test_training = CONTROLLER.prepareNewTraining(
                    "AAA051",
                    "MMA Training",
                    "SilliconGym - Vaníčkova 7 Břevnov Praha",
                    "2022-07-13",
                    15,
                    120
            );

            test_training = CONTROLLER.assignTrainerForTraining(
                    test_trainer,
                    test_training,
                    "ZZZ"
            );

            payment test_payment = CONTROLLER.prepareNewPayment(
                    test_user,
                    test_training,
                    BigDecimal.valueOf(351),
                    "VISA"
            );

            // INSERT
            service.createUser(test_user);
            service.createTrainer(test_trainer);
            service.createTraining(test_training);
            service.handleTransaction(test_payment);

            DAO daoUser = new DAO(entityManager, user.class);
            daoUser.get(226226);

            // DELETE
//            service.deletePayment(test_payment);
//            service.deleteTraining(test_training);
//            service.deleteTrainer(test_trainer);
//            service.deleteUser(test_user);

        }
    }
}
