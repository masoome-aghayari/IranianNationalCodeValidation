package ir.fanap.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NationalCode {
    private String nationalCode;
    private boolean valid;
    private LocalDateTime inputTime;

    public NationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public NationalCode(String nationalCode, LocalDateTime inputTime) {
        setNationalCode(nationalCode);
        setInputTime(inputTime);
    }

    public NationalCode(String nationalCode, LocalDateTime inputTime, boolean valid) {
        setNationalCode(nationalCode);
        setInputTime(inputTime);
        setValid(valid);
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
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
                ", inputDateTime=" + (inputTime == null ? null : inputTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))) +
                ", isValid=" + valid +
                '}';
    }
}