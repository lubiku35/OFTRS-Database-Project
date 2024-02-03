package oftrs.controller;

import oftrs.model.payment;
import oftrs.model.trainer;
import oftrs.model.training;
import oftrs.model.user;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Controller {

    public user prepareNewUser(int userId, String firstname, String lastname, String email, String phoneNumber, String nickname) {
        // New instance
        user newUser = new user();

        newUser.setUserid(userId);
        newUser.setFirstname(firstname);
        newUser.setLastname(lastname);
        newUser.setEmail(email);
        newUser.setPhonenumber(phoneNumber);
        newUser.setNickname(nickname);

        return newUser;
    }


    public trainer prepareNewTrainer(user user, int trainerid, String specification, String type, String validityDate) {
        // New instance
        trainer newTrainer = new trainer();

        newTrainer.setTrainerid(trainerid);
        newTrainer.setUser(user);
        newTrainer.setSpecification(specification);
        newTrainer.setType(type);
        newTrainer.setValiditydate(Date.valueOf(validityDate));

        return newTrainer;
    }

    public training prepareNewTraining(String trainingCount, String trainingName, String trainingPlace, String trainingDate, int maxCapacity, int duration) {
        // New instance
        training newTraining = new training();
        newTraining.setTrainingcount(trainingCount);
        newTraining.setTrainingname(trainingName);
        newTraining.setPlace(trainingPlace);
        newTraining.setTrainingdate(Date.valueOf(trainingDate));
        newTraining.setMaxcapacity(maxCapacity);
        newTraining.setDuration(duration);

        return newTraining;
    }

    public training assignTrainerForTraining(trainer trainer, training training, String postfix) {
        training.setTrainerid(trainer.getTrainerid());
        training.setTrainingid(trainer.getTrainerid() + "-" + postfix);

        return training;
    }

    public payment prepareNewPayment(user user, training training, BigDecimal paymentAmount, String type) {
        // New instance
        payment newPayment = new payment();

        // Today Date
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(dateTimeFormatter);

        newPayment.setUserid(user.getUserid());
        newPayment.setTrainingid(training.getTrainingid());
        newPayment.setPaymentid(user.getUserid() + "_" + training.getTrainingid());
        newPayment.setPaymentamount(paymentAmount);
        newPayment.setPaymentdate(Date.valueOf(formattedDate));
        newPayment.setPaymenttype(type);

        return newPayment;
    }
}
