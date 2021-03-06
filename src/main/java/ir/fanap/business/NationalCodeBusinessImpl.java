package ir.fanap.business;

import ir.fanap.model.NationalCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Setter
@Getter
public class NationalCodeBusinessImpl implements NationalCodeBusiness {
    private List<NationalCode> nationalCodes;

    public NationalCodeBusinessImpl() {
        setNationalCodes(new ArrayList<>());
    }

    public void validateNationalCode(NationalCode nationalCode) {
        try {
            String nationalCodeString = nationalCode.getNationalCode();
            int length = nationalCodeString.length();
            if (hasInValidLengthOrDigits(nationalCodeString))
                nationalCode.setValid(false);
            else {
                nationalCodeString = length == 8 ? "00" + nationalCodeString :
                        length == 9 ? "0" + nationalCodeString : nationalCodeString;
                int lastDigit = calculateNationalCodeLastDigit(nationalCodeString);
                int nationalCodeLastDigit = nationalCodeString.charAt(9) - 48;
                nationalCode.setValid(nationalCodeLastDigit == lastDigit);
            }
        } catch (StringIndexOutOfBoundsException | NullPointerException e) {
            nationalCode.setValid(false);
        }
        nationalCodes.add(nationalCode);
    }

    public boolean hasInValidLengthOrDigits(String nationalCodeString) throws NullPointerException {
        return (!nationalCodeString.matches("[0-9]{8,10}") ||
                (nationalCodeString.matches("0{8,10}|1{8,10}|2{8,10}|3{8,10}|4{8,10}|5{8,10}|6{8,10}|7{8,10}|8{8,10}|9{8,10}")));
    }

    public int calculateNationalCodeLastDigit(String nationalCodeString) throws StringIndexOutOfBoundsException {
        int sum = IntStream.range(0, 9)
                .map(i -> Character.getNumericValue(nationalCodeString.charAt(i)) * (10 - i))
                .sum();
        int remainder = sum % 11;
        return remainder < 2 ? remainder : 11 - remainder;
    }

    public String getStateCode(NationalCode nationalCode) throws NullPointerException {
        return nationalCode.getNationalCode().substring(0, 3);
    }

    public String getPersonUniqueCode(NationalCode nationalCode) throws NullPointerException {
        return nationalCode.getNationalCode().substring(3, 9);
    }
}