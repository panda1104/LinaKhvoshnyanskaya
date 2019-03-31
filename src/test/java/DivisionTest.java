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

public class DivisionTest {

    Calculator calculator = new Calculator();



    @BeforeClass
    public static void beforeClass() {
        System.out.println("DivisionTest init");
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

    @Test(groups = "MultAndDivTest")
    public void TestDiv1()
    {
        Assert.assertEquals(9,calculator.div(27,3));
    }


    @Test(groups = "MultAndDivTest")
    public void TestDiv2()
    {
        Assert.assertEquals(10,calculator.div(100,10));
    }

    @Test(groups = "MultAndDivTest")
    public void TestDiv3()
    {
        Assert.assertEquals(6,calculator.div(66,11));
    }

    @Test(groups = "MultAndDivTest")
    public void TestDiv4()
    {
        Assert.assertEquals(75,calculator.div(1500,20));
    }


}
