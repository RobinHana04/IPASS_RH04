package setup;

import Persistence.PersistenceManager;
import org.backend.domain.Vakantiehuis;
import org.backend.requests.VacationRental;
import org.backend.security.MyUser;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("Server is starting with necessary data");
            MyUser.createUser("Robin", "password", "admin");
            MyUser.createUser("Joe", "password", "user");
            PersistenceManager.loadVacationRentalFromFile();

            Vakantiehuis v1 = new Vakantiehuis("Huisje 11", "Victoriameer 111", "50m2", 500);
            Vakantiehuis.getAlleHuizen().add(v1);
            VacationRental.getVacationRental().addVakantiehuizenVR(v1);
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

