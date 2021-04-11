package ir.fanap.test.business;

import ir.fanap.business.NationalCodeBusinessImpl;
import ir.fanap.model.NationalCode;
import org.junit.Assert;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class NationalCodeBusinessImplTest {
    NationalCodeBusinessImpl nationalCodeBusiness;

    @DataProvider
    public static Object[][] getStateCodeDataProvider() {
        NationalCode nationalCode1 = new NationalCode("4310928838", LocalDateTime.now(), true);
        NationalCode nationalCode2 = new NationalCode("4769567852", LocalDateTime.now(), false);
        NationalCode nationalCode3 = new NationalCode("1111111111", LocalDateTime.now(), false);
        NationalCode nationalCode4 = new NationalCode("1234567", LocalDateTime.now(), false);
        NationalCode nationalCode5 = new NationalCode("5889286706", LocalDateTime.now(), true);
        return new Object[][]{{nationalCode1, "431"}, {nationalCode2, "476"}, {nationalCode3, "111"}, {nationalCode4, "123"}, {nationalCode5, "588"}};
    }

    @BeforeClass
    public void setup() {
        nationalCodeBusiness = Mockito.mock(NationalCodeBusinessImpl.class, Mockito.CALLS_REAL_METHODS);
        nationalCodeBusiness.setNationalCodes(new ArrayList<>());
    }

    @DataProvider(name = "validateNationalCodeDataProvider")
    public Object[][] validateNationalCodeDataProvider() {
        NationalCode nationalCode1 = new NationalCode("4310928838", LocalDateTime.now(), true);
        NationalCode nationalCode2 = new NationalCode("588927678512", LocalDateTime.now(), false);
        NationalCode nationalCode3 = new NationalCode("1111111111", LocalDateTime.now(), false);
        NationalCode nationalCode4 = new NationalCode("1234567", LocalDateTime.now(), false);
        NationalCode nationalCode5 = new NationalCode("5889286706", LocalDateTime.now(), true);
        return new Object[][]{{nationalCode1}, {nationalCode2}, {nationalCode3}, {nationalCode4}, {nationalCode5}};
    }

    @DataProvider(name = "calculateNationalCodeLastDigitDataProvider")
    public static Object[][] calculateNationalCodeLastDigitDataProvider() {
        String nationalCodeString1 = "4310928838";
        String nationalCodeString2 = "588927678512";
        String nationalCodeString3 = "1111111111";
        String nationalCodeString5 = "5889286706";
        return new Object[][]{{nationalCodeString1, 8}, {nationalCodeString2, 6}, {nationalCodeString3, 1}, {nationalCodeString5, 6}};
    }

    @Test(dataProvider = "validateNationalCodeDataProvider")
    public void testValidateNationalCode(NationalCode nationalCode) {
        nationalCodeBusiness.validateNationalCode(nationalCode);
        NationalCode actualResult = nationalCodeBusiness.getNationalCodes().get(0);
        nationalCodeBusiness.setNationalCodes(new ArrayList<>());
        Assert.assertEquals(actualResult, nationalCode);
    }

    @Test(dataProvider = "getStateCodeDataProvider")
    public void testGetStateCode(NationalCode nationalCode, String expectedResult) {
        String actualResult = nationalCodeBusiness.getStateCode(nationalCode);
        Assert.assertEquals(actualResult, expectedResult);
    }

   /* @Test(dataProvider = "nationalCodeValidatorDataProvider")
    public void testGetPersonUniqueCode(List<String> expectedResult) {
        List<String> actualResult = new ArrayList<>();
        expectedResult.forEach(nationalCode -> actualResult.add(nationalCodeBusiness.getStateCode(nationalCode)));
        Assert.assertEquals(actualResult, expectedResult);
    }*/

    @Test
    public void testHasInValidLengthOrDigits() {
    }

    @Test(dataProvider = "calculateNationalCodeLastDigitDataProvider")
    public void testCalculateNationalCodeLastDigit(String nationalCodeString, int expectedResult) {
        int actualResult = nationalCodeBusiness.calculateNationalCodeLastDigit(nationalCodeString);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(expectedExceptions = StringIndexOutOfBoundsException.class)
    public void testCalculateNationalCodeLastDigit() {
        nationalCodeBusiness.calculateNationalCodeLastDigit("123456");
    }
}