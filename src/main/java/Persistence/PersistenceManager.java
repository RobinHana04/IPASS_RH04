package Persistence;

import org.backend.model.VacationRental;
import org.backend.model.Vakantiehuis;
import org.backend.model.Verhuurder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PersistenceManager {
    static Path filePath = Path.of("C:\\Users\\Robin Hana\\IPASS_VacationRental\\src\\main\\java\\Persistence\\VacationRental.obj");

    public static void loadVacationRentalFromFile() throws IOException, ClassNotFoundException {
        InputStream is = Files.newInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(is);
        Object obj = ois.readObject();
        if(obj != null) {
            VacationRental loadedObj = (VacationRental) obj;
            List<Vakantiehuis> vakantiehuizen = loadedObj.getAllVakantieHuizen();
            List<Verhuurder> verhuurders = loadedObj.getAllVerhuurders();

// Print de vakantiehuizen
            System.out.println("Vakantiehuizen:");
            for (Vakantiehuis vakantiehuis : vakantiehuizen) {
                System.out.println(vakantiehuis);
            }

// Print de verhuurders
            System.out.println("Verhuurders:");
            for (Verhuurder verhuurder : verhuurders) {
                System.out.println(verhuurder);
            }
            VacationRental.setVacationRental(loadedObj);
            ois.close();
        }
    }



    public static void saveVacationRentalToFile() throws IOException {
        VacationRental vcr = VacationRental.getVacationRental();
        System.out.println(vcr);
        OutputStream os = Files.newOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(vcr);
        oos.flush();
        oos.close();
    }
}