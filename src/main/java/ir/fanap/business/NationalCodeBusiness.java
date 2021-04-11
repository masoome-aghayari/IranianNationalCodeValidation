package ir.fanap.business;

import ir.fanap.model.NationalCode;

public interface NationalCodeBusiness {
    /**
     * validate national code and throw exceptions
     * @param nationalCode input national code
     */
    void validateNationalCode(NationalCode nationalCode);

    /**
     * this method return state code part of national code (3 digits)
     * @param nationalCode input national code
     * @return state code
     */
    String getStateCode(NationalCode nationalCode);

    /**
     * return unique part of national code (6 digits)
     * this part is unique for each person in state
     * @param nationalCode input national code
     * @return unique code part
     */
    String getPersonUniqueCode(NationalCode nationalCode);
}