package ir.fanap.business;

import ir.fanap.model.NationalCode;

import java.util.List;
import java.util.stream.IntStream;

public class NationalCodeBusinessImpl implements NationalCodeBusiness {
    private List<NationalCode> nationalCodes;

    public NationalCodeBusinessImpl(List<NationalCode> nationalCodes) {
        this.nationalCodes = nationalCodes;
    }

    public void validateNationalCode(NationalCode nationalCode) {
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
        nationalCodes.add(nationalCode);
    }

    public boolean hasInValidLengthOrDigits(String nationalCodeString) {
        int length = nationalCodeString.length();
        return (length < 8 ||
                length > 10 ||
                (nationalCodeString.matches("0{8,10}|1{8,10}|2{8,10}|3{8,10}|4{8,10}|5{8,10}|6{8,10}|7{8,10}|8{8,10}|9{8,10}")));
    }

    public int calculateNationalCodeLastDigit(String nationalCodeString) {
        int sum = IntStream.range(0, 9)
                .map(i -> Character.getNumericValue(nationalCodeString.charAt(i)) * (10 - i))
                .sum();
        int remainder = sum % 11;
        return remainder < 2 ? remainder : 11 - remainder;
    }

    public String getStateCode(NationalCode nationalCode) {
        return nationalCode.getNationalCode().substring(0, 2);
    }

    public String getPersonUniqueCode(NationalCode nationalCode) {
        return nationalCode.getNationalCode().substring(3, 8);
    }

    public List<NationalCode> getNationalCodes() {
        return nationalCodes;
    }
}
