
import org.junit.*;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class MultiplicationTest {

    Calculator calculator = new Calculator();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("MultiplicationTest init");
    }

    @AfterClass
    public  static void afterClass() {
        System.out.println("Test complete");
    }

    @Before
    public void initTest() {
        calculator = new Calculator();
    }

    @After
    public void afterTest() {
        calculator = null;
    }

    @Test(groups = "MultAndDivTest")
    public void TestMult1()
    {
        Assert.assertEquals(120,calculator.mult(12,10));
    }

    @Test(groups = "MultAndDivTest")
    public void TestMult2()
    {
        Assert.assertEquals(42,calculator.mult(7,6));
    }

    @Test(groups = "MultAndDivTest")
    public void TestMult3()
    {
        Assert.assertEquals(6534,calculator.mult(99,66));
    }

    @Test(groups = "MultAndDivTest")
    public void TestMult4()
    {
        Assert.assertEquals(1785,calculator.mult(85,21));
    }


}
