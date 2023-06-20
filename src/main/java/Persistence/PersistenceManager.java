package Persistence;

import org.backend.domain.Vakantiehuis;
import org.backend.domain.Verhuurder;
import org.backend.requests.VacationRental;

import java.io.*;
import java.util.List;

public class PersistenceManager {
    private static final String filePath = "VacationRental.obj";
    private static final File file = new File(filePath);

    public static void loadVacationRentalFromFile() throws IOException, ClassNotFoundException {
        if (file.exists()) {
            FileInputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            Object obj = ois.readObject();

            if (obj != null && obj instanceof VacationRental) {
                VacationRental loadedObj = (VacationRental) obj;
                VacationRental.setVacationRental(loadedObj);

                List<Vakantiehuis> vakantiehuizen = loadedObj.getVakantiehuizenVR();
                List<Verhuurder> verhuurders = loadedObj.getAllVerhuurders();

                System.out.println("Vakantiehuizen:");
                for (Vakantiehuis vakantiehuis : vakantiehuizen) {
                    System.out.println(vakantiehuis);
                }

                System.out.println("Verhuurders:");
                for (Verhuurder verhuurder : verhuurders) {
                    System.out.println(verhuurder);
                }

                ois.close();
            }
        } else {
            System.out.println("File does not exist.");
        }
    }

    public static void saveVacationRentalToFile() throws IOException {
        VacationRental vcr = VacationRental.getVacationRental();
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(vcr);
        System.out.println("All houses from VacationRental object: " + vcr.getVakantiehuizenVR());
        oos.flush();
        oos.close();
    }
}
