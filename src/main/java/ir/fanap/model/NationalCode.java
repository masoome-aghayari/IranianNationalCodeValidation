package ir.fanap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Setter
@Getter
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

    @Override
    public String toString() {
        return "NationalCode{" +
                "nationalCode='" + nationalCode + '\'' +
                ", inputDateTime=" + (inputTime == null ? null : inputTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))) +
                ", isValid=" + valid +
                '}';
    }
}
