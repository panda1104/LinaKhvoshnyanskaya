/* TODO
        1. Отуствует package
        2. Для чего присутсвует импорты junit?

 */

import org.junit.*;

import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class SumTest {

    Calculator calculator = new Calculator();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("SumTest init");
    }

    @AfterClass
    public  static void afterClass() {
        System.out.println("Test complete");
    }

    // TODO Эта аннотация из junit
    @Before
    public void initTest() {
        calculator = new Calculator();
    }
    // TODO Эта аннотация из junit
    @After
    public void afterTest() {
        calculator = null;
    }

    @Test(groups = "SubAndSumTest")
    public void TestSum1()
    {
        Assert.assertEquals(7,calculator.sum(3,4));
    }

    @Test(groups = "SubAndSumTest")
    public void TestSum2()
    {
        Assert.assertEquals(2212,calculator.sum(1101,1111));
    }

    @Test(groups = "SubAndSumTest")
    public void TestSum3()
    {
        Assert.assertEquals(112,calculator.sum(99,13));
    }

    @Test(groups = "SubAndSumTest")
    public void TestSum4()
    {
        Assert.assertEquals(2009,calculator.sum(1998,11));
    }


}
