package view;

import business.NationalCodeBusinessImpl;
import model.NationalCode;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        NationalCodeBusinessImpl nationalCodeBusiness = new NationalCodeBusinessImpl(new ArrayList<>());
        String userInput;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            userInput = scanner.nextLine();
            LocalDateTime now = LocalDateTime.now();
            if (!Objects.equals(userInput, "exit")) {
                NationalCode nationalCode = new NationalCode(userInput, now);
                nationalCodeBusiness.validateNationalCode(nationalCode);
            } else {
                scanner.close();
                break;
            }
        }
        List<NationalCode> nationalCodes = nationalCodeBusiness.getNationalCodes();
        nationalCodes.sort(Comparator.comparing(NationalCode::getNationalCode));
        nationalCodes.forEach(nationalCode -> System.out.println(nationalCode.toString()));
    }
}
