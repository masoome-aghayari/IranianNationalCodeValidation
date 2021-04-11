package ir.fanap.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NationalCode {
    private String nationalCode;
    private boolean isValid;
    private LocalDateTime inputTime;

    public NationalCode(String userInput, LocalDateTime inputTime) {
        setNationalCode(userInput);
        setInputTime(inputTime);
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public LocalDateTime getInputTime() {
        return inputTime;
    }

    public void setInputTime(LocalDateTime inputTime) {
        this.inputTime = inputTime;
    }

    @Override
    public String toString() {
        return "NationalCode{" +
                "nationalCode='" + nationalCode + '\'' +
                ", inputDateTime=" + inputTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) +
                ", isValid=" + isValid +
                '}';
    }
}