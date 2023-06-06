package setup;

import Persistence.PersistenceManager;
import org.backend.requests.VacationRental;
import org.backend.domain.Vakantiehuis;
import org.backend.domain.Verhuurder;

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
            Verhuurder v1 = new Verhuurder("Robin");
            Vakantiehuis vh1 = new Vakantiehuis("Huisje 1", "Victoriameer 121", "50m2", 1);
           Vakantiehuis vh2 = new Vakantiehuis("Huisje 2", "AmeliaPolder 33", "220m2", 2);
           Vakantiehuis.addHuis(vh1);
           Vakantiehuis.addHuis(vh2);
           v1.voegHuisToe(vh1);
           v1.voegHuisToe(vh2);
           Verhuurder.voegVerhuurderToe(v1);

           VacationRental vacationRental = VacationRental.getVacationRental();
           vacationRental.addVakantiehuizenVR(vh1);
           vacationRental.addVakantiehuizenVR(vh2);
           vacationRental.addVerhuurderVR(v1);
            PersistenceManager.loadVacationRentalFromFile();

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
            System.out.println("Terminating application");
            PersistenceManager.saveVacationRentalToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

