/* TODO
        1. Отуствует package
        2. Для чего присутсвует импорты junit?

 */
import org.junit.*;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.*;
import static org.testng.Assert.assertNotEquals;

public class ParametrizedTest {

    private final Calculator calculator = new Calculator();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Parametrized test init");
    }

    @AfterClass
    public  static void afterClass() {
        System.out.println("Test complete");
    }



    @Test(groups = "SubAndSum")
    @Parameters({"value1", "value2"})
    public void TestSum(long a, long b)
    {
        Assert.assertEquals(a + b,calculator.sum(a,b));
    }

    @Test(groups = "SubAndSum")
    @Parameters({"value1", "value2"})
    public void TestSub(double a, double b)
    {
        Assert.assertEquals(a - b,calculator.sub(a,b));
    }

}
