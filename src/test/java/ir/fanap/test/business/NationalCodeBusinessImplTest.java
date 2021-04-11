package ir.fanap.test.business;

import ir.fanap.business.NationalCodeBusinessImpl;
import ir.fanap.model.NationalCode;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NationalCodeBusinessImplTest {
    NationalCodeBusinessImpl nationalCodeBusiness;

    @DataProvider
    public static Object[][] nationalCodeValidatorDataProvider() {
        return new Object[0][];
    }

    @BeforeClass
    public void setup() {
        nationalCodeBusiness = Mockito.mock(NationalCodeBusinessImpl.class);
    }

    @DataProvider(name = "repeatedDigitsNationalCodeDataProvider")
    public Object[][] repeatedDigitsNationalCodeDataProvider() {
        NationalCode repeatedDigitsNationalCode = new NationalCode("1111111111", LocalDateTime.now());
        return new Object[][]{{repeatedDigitsNationalCode}};
    }

    @DataProvider(name = "eightDigitNationalCodeDataProvider")
    public Object[][] eightDigitNationalCodeDataProvider() {
        NationalCode eightDigitsNationalCode = new NationalCode("25369685", LocalDateTime.now());
        return new Object[][]{{eightDigitsNationalCode}};
    }

    @DataProvider(name = "nineDigitNationalCodeDataProvider")
    public Object[][] nineDigitNationalCodeDataProvider() {
        NationalCode nineDigitsNationalCode = new NationalCode("253696857", LocalDateTime.now());
        return new Object[][]{{nineDigitsNationalCode}};
    }

    @DataProvider(name = "correctNationalCodeDataProvider")
    public Object[][] correctNationalCodeDataProvider() {
        NationalCode correctNationalCode = new NationalCode("4310928838", LocalDateTime.now());
        return new Object[][]{{correctNationalCode}};
    }

    @DataProvider(name = "calculateLastDigitDataProvider")
    public Object[][] calculateLastDigitDataProvider() {
        String nationalCodeString1 = "4310928838";
        String nationalCodeString2 = "588927678512";
        String nationalCodeString = "1111111111";
        String nationalCodeString = "1234567";
        return new Object[][]{{correctNationalCode}};
    }

    @Test(dataProvider = "correctNationalCodeDataProvider")
    public void testCalculateLastDigit(NationalCode nationalCode, int expectedResult) {
        int actualResult = nationalCodeBusiness.calculateNationalCodeLastDigit(nationalCode.getNationalCode());
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(dataProvider = "nationalCodeValidatorDataProvider")
    public void testValidateNationalCode(List<NationalCode> expectedResult) {
        expectedResult.forEach(nationalCode -> nationalCodeBusiness.validateNationalCode(nationalCode));
        List<NationalCode> actualResult = nationalCodeBusiness.getNationalCodes();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(dataProvider = "nationalCodeValidatorDataProvider")
    public void testGetStateCode(List<NationalCode> expectedResult) {
        List<String> actualResult = new ArrayList<>();
        expectedResult.forEach(nationalCode -> actualResult.add(nationalCodeBusiness.getStateCode(nationalCode)));
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(dataProvider = "nationalCodeValidatorDataProvider")
    public void testGetPersonUniqueCode(List<NationalCode> expectedResult) {
        List<String> actualResult = new ArrayList<>();
        expectedResult.forEach(nationalCode -> actualResult.add(nationalCodeBusiness.getStateCode(nationalCode)));
        Assert.assertEquals(actualResult, expectedResult);
    }

}