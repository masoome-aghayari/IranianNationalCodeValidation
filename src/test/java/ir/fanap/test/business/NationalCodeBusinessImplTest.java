package ir.fanap.test.business;

import ir.fanap.business.NationalCodeBusinessImpl;
import ir.fanap.model.NationalCode;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class NationalCodeBusinessImplTest {
    NationalCodeBusinessImpl nationalCodeBusiness;

    @BeforeClass
    public void setup() {
        nationalCodeBusiness = Mockito.mock(NationalCodeBusinessImpl.class);
    }

    @DataProvider(name = "repeatedDigitsNationalCodeDataProvider")
    public NationalCode repeatedDigitsNationalCodeDataProvider() {
        return new NationalCode("1111111111", LocalDateTime.now());
    }

    @DataProvider(name = "eightDigitNationalCodeDataProvider")
    public NationalCode eightDigitNationalCodeDataProvider() {
        return new NationalCode("25369685", LocalDateTime.now());
    }

    @DataProvider(name = "nineDigitNationalCodeDataProvider")
    public NationalCode nineDigitNationalCodeDataProvider() {
        return new NationalCode("253696857", LocalDateTime.now());
    }

    @DataProvider(name = "correctNationalCodeDataProvider")
    public NationalCode correctNationalCodeDataProvider() {
        return new NationalCode("4310928838", LocalDateTime.now());
    }

    @Test(dataProvider = "correctNationalCodeDataProvider")
    public void testCalculateLastDigit(NationalCode nationalCode) {
        int actualResult = nationalCodeBusiness.calculateLastDigit(nationalCode.getNationalCode());
        int expectedResult = nationalCode.getNationalCode().charAt(9) - 48;
        Assert.assertEquals(actualResult, expectedResult);
    }
}

