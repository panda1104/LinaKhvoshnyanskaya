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


public class SubTest {

    Calculator calculator = new Calculator();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("SubTest init");
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
    public void TestSub1()
    {
        Assert.assertEquals(70,calculator.sub(100,30));
    }

    @Test(groups = "SubAndSumTest")
    public void TestSub2()
    {
        Assert.assertEquals(1922,calculator.sub(1988,66));
    }

    @Test(groups = "SubAndSumTest")
    public void TestSub3()
    {
        Assert.assertEquals(7884,calculator.sub(8546,662));
    }

    @Test(groups = "SubAndSumTest")
    public void TestSub4()
    {
        Assert.assertEquals(1877,calculator.sub(2543,666));
    }


}
