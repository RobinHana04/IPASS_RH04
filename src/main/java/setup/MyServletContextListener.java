package setup;

import Persistence.PersistenceManager;
import org.backend.domain.Boeking;
import org.backend.domain.Huurder;
import org.backend.domain.Vakantiehuis;
import org.backend.domain.Verhuurder;
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

            MyUser.createUser("Robin", "password", "user");
            MyUser.createUser("Max", "password", "user");
            MyUser.createUser("Martijn", "password", "admin");
            PersistenceManager.loadVacationRentalFromFile();

            Huurder h1 = new Huurder("Robin");
            Huurder h2 = new Huurder("Max");
            Huurder h3 = new Huurder("Martijn");

            Verhuurder vh1 = new Verhuurder("Robin");
            Verhuurder vh2 = new Verhuurder("Max");
            Verhuurder vh3 = new Verhuurder("Martijn");
            
            Vakantiehuis v1 = new Vakantiehuis("Huisje 11", "Victoriameer 111", "50m2", 500, "", vh2);
            Vakantiehuis v2 = new Vakantiehuis("Huisje 12", "Victoriameer 113", "51m2", 500, "", vh1);

            Boeking b1 = new Boeking(h1, v1, d, dPlusOne);

            Vakantiehuis.getAlleHuizen().add(v1);
            VacationRental.getVacationRental().addVakantiehuizenVR(v1);

            Vakantiehuis.getAlleHuizen().add(v2);
            VacationRental.getVacationRental().addVakantiehuizenVR(v2);

            Boeking.addBoeking(b1);
            VacationRental.getVacationRental().addBoekingenVR(b1);

            Huurder.getAlleHuurders().add(h1);
            VacationRental.getVacationRental().addHuurdersVR(h1);

            Huurder.getAlleHuurders().add(h2);
            VacationRental.getVacationRental().addHuurdersVR(h2);

            Huurder.getAlleHuurders().add(h3);
            VacationRental.getVacationRental().addHuurdersVR(h3);

            Verhuurder.getAlleVerhuurders().add(vh1);
            VacationRental.getVacationRental().addVerhuurderVR(vh1);

            Verhuurder.getAlleVerhuurders().add(vh2);
            VacationRental.getVacationRental().addVerhuurderVR(vh2);

            Verhuurder.getAlleVerhuurders().add(vh3);
            VacationRental.getVacationRental().addVerhuurderVR(vh3);


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

