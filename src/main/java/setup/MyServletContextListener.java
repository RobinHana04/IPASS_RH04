package setup;

import Persistence.PersistenceManager;
import org.backend.domain.Boeking;
import org.backend.domain.Huurder;
import org.backend.domain.Vakantiehuis;
import org.backend.requests.VacationRental;
import org.backend.security.MyUser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.time.LocalDate;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("Server is starting with necessary data");
            LocalDate d = LocalDate.now();
            LocalDate dPlusOne = d.plusDays(1);

            MyUser.createUser("Robin", "password", "admin");
            MyUser.createUser("Joe", "password", "user");
            PersistenceManager.loadVacationRentalFromFile();

            Huurder h1 = new Huurder("RobinHana04");
            Vakantiehuis v1 = new Vakantiehuis("Huisje 11", "Victoriameer 111", "50m2", 500, "");
            Boeking b1 = new Boeking(h1, v1, d, dPlusOne);

            Vakantiehuis.getAlleHuizen().add(v1);
            VacationRental.getVacationRental().addVakantiehuizenVR(v1);

            Boeking.getAlleBoekingen().add(b1);
            VacationRental.getVacationRental().addBoekingenVR(b1);

            Huurder.getAlleHuurders().add(h1);
            VacationRental.getVacationRental().addHuurdersVR(h1);

            h1.addBoeking(b1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            System.out.println("Terminating application: ");
            PersistenceManager.saveVacationRentalToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

