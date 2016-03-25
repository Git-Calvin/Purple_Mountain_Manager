/**
 * Hiker.java
 *
 * Created by Calvin Wong on 10/08/2014
 *
 * Hiker class that generates hikers
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hiker extends Thread { // uses a thread to generate hikers

    private String hikers = "hiker";
    private File fOut;
    private FileOutputStream fos;
    private static BufferedWriter bw;

    public Hiker() {

        // finding file location to store hiker's name
        String location = System.getProperty("user.dir") + "\\";
        String filename = location + "HikersList.txt";

        fOut = new File(filename);

        try {
            if (!fOut.exists()) {
                fOut.createNewFile();
            }
            fos = new FileOutputStream(fOut);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
        } catch (Exception ex) {
            System.out.println("Exception in creating file" + ex);
        }
    }

    // method to close file
    public static void closeFile() {

        try {
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Hiker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void run() {

        while (Controller.flag) {

            try {
                // Generate hikers
                String temp = hikers.concat(new Integer(Controller.count).toString());
                Controller.HikersList.add(temp);
                bw.write(temp);
                bw.newLine();
                System.out.println("Adding:::" + Controller.count);
                Controller.count++;

                Thread.sleep(100);
            } catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getHikers() {
        return hikers;
    }

    public void setHikers(String hikers) {
        this.hikers = hikers;
    }

    @Override
    public String toString() {
        return "Hiker{" +
                "hikers='" + hikers + '\'' +
                ", fOut=" + fOut +
                ", fos=" + fos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hiker hiker = (Hiker) o;

        if (fOut != null ? !fOut.equals(hiker.fOut) : hiker.fOut != null) return false;
        if (fos != null ? !fos.equals(hiker.fos) : hiker.fos != null) return false;
        if (hikers != null ? !hikers.equals(hiker.hikers) : hiker.hikers != null) return false;

        return true;
    }
} // end of class
