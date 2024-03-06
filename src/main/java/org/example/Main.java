package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Secretariat.resetSecretariat();
        String testDirectory = "src/main/resources/" + args[0] + "/";
        String testPath = testDirectory + args[0];
        File outputFile = new File(testPath + ".out");
        File inputFile = new File(testPath + ".in");
        Scanner s = new Scanner(inputFile);
        FileWriter fw = new FileWriter(outputFile, true);
        PrintWriter pw = new PrintWriter(fw);
        while (s.hasNextLine()) {
            String command = s.nextLine();
            String[] splitCommand = command.split(" - ");
            switch (splitCommand[0]) {
                case ("adauga_student") : {
                    try {
                        if (splitCommand[1].equals("licenta")) {
                            Secretariat.addStudent(new BachelorStudent(splitCommand[2]));
                        }
                        if (splitCommand[1].equals("master")) {
                            Secretariat.addStudent(new MasterStudent(splitCommand[2]));
                        }
                    } catch (DuplicatedStudent e) {
                        pw.println("***");
                        pw.println(e.getMessage());
                        pw.flush();
                    }
                    break;
                }
                case ("citeste_mediile") : {
                    Secretariat.readGrades(testDirectory);
                    break;
                }
                case ("posteaza_mediile") : {
                    Secretariat.postGrades(outputFile);
                    break;
                }
                case ("contestatie") : {
                    Secretariat.contestation(splitCommand[1], splitCommand[2]);
                    break;
                }
                case ("adauga_curs") : {
                    Secretariat.addCourse(splitCommand[1], splitCommand[2], splitCommand[3]);
                    break;
                }
                case ("adauga_preferinte") : {
                    Secretariat.addPreferences(splitCommand[1], splitCommand);
                    break;
                }
                case ("repartizeaza") : {
                    Secretariat.assignStudents();
                    break;
                }
                case ("posteaza_curs") : {
                    Secretariat.postCourse(splitCommand[1], outputFile);
                    break;
                }
                case ("posteaza_student") : {
                    Secretariat.postStudent(splitCommand[1], outputFile);
                    break;
                }
            }
        }
        fw.close();
        pw.close();
    }
}
