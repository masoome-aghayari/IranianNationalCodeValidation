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
        if (!nationalCodeString.matches("0{10}|1{10}|2{10}|3{10}|4{10}|5{10}|6{10}|7{10}|8{10}|9{10}")) {
            int lastDigit = calculateLastDigit(nationalCodeString);
            int nationalCodeLastDigit = nationalCodeString.charAt(9);
            nationalCode.setValid(nationalCodeLastDigit - 48 == lastDigit);
        } else
            nationalCode.setValid(false);
        nationalCodes.add(nationalCode);
    }

    public int calculateLastDigit(String nationalCodeString) {
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
