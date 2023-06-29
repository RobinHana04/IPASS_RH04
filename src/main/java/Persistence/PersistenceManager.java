package Persistence;

import org.backend.requests.VacationRental;

import java.io.*;

public class PersistenceManager {
    private static final String filePath = "VacationRental.obj";
    private static final File file = new File(filePath);

    public static void loadVacationRentalFromFile() throws IOException, ClassNotFoundException {
        if (file.exists()) {
            FileInputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            Object obj = ois.readObject();

            if (obj instanceof VacationRental) {
                VacationRental loadedObj = (VacationRental) obj;
                VacationRental.setVacationRental(loadedObj);
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
        oos.flush();
        oos.close();
    }
}
