package domain;

import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by User on 5/1/15.
 */
public class SolutionTest extends TestCase {

    private Solution s;
    @Override
    public void setUp() throws Exception {
        super.setUp();
        s = new Solution();

    }

    @SmallTest
    public void testSolutionNotNull(){
        Assert.assertNotNull(s);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @LargeTest
    public void testCompute() throws Exception {
        s.compute(5);
        testGetSolMol();
        testGetSolMass();
        Assert.assertEquals(10, s.getSolMol());
        Assert.assertEquals(20, s.getSolMass());

    }

    @SmallTest
    public void testCalcMol() throws Exception {
        s.calcMol(5, 4);
        double mol = s.getSolMol();
        Assert.assertEquals(20, mol);
    }

    @SmallTest
    public void testCalcMass() throws Exception {
        s.calcMass(5, 4);
        double mass = s.getSolMass();
        Assert.assertEquals(20, mass);
    }


    @MediumTest
    public void testGetVolFlask() throws Exception {
        testSetVolFlask();
        double flask = s.getVolFlask();
        Assert.assertEquals(10, flask);
    }

    @SmallTest
    public void testSetVolFlask() throws Exception {
        s.setVolFlask(10);
        Assert.assertNotNull(s.getVolFlask());
    }

    @MediumTest
    public void testGetSolvent() throws Exception {
        testSetSolvent();
        Assert.assertNotNull(s.getSolvent());

    }

    @SmallTest
    public void testSetSolvent() throws Exception {
        s.setSolvent("Salt");
        Assert.assertNotNull(s.getSolvent());
    }

    @MediumTest
    public void testGetSolute() throws Exception {
        testSetSolute();
        Assert.assertNotNull(s.getSolute());
    }

    @SmallTest
    public void testSetSolute() throws Exception {
        s.setSolute("Salt");
        Assert.assertNotNull(s.getSolute());
    }

    @MediumTest
    public void testGetSoluteMolWeight() throws Exception {
        testSetSoluteMolWeight();
        Assert.assertEquals(20, s.getSoluteMolWeight());
    }

    @SmallTest
    public void testSetSoluteMolWeight() throws Exception {
        s.setSoluteMolWeight(20);
        Assert.assertEquals(0.0, s.getSoluteMolWeight());
    }

    @MediumTest
    public void testGetSolMolarity() throws Exception {
        testSetSolMolarity();
        Assert.assertEquals(15, s.getSolMolarity());
    }

    @MediumTest
    public void testSetSolMolarity() throws Exception {
        s.setSoluteMolWeight(15);
        Assert.assertEquals(0, s.getSolMolarity());
    }

    @MediumTest
    public void testGetSolMass() throws Exception {
        testSetSolMass();
        Assert.assertEquals(20, s.getSolMass());
    }

    @SmallTest
    public void testSetSolMass() throws Exception {
        s.setSolMass(10.0);
        Assert.assertEquals(0, s.getSolMass());
    }

    @MediumTest
    public void testGetSolMol() throws Exception {
        testSetSolMol();
        Assert.assertEquals(10, s.getSolMol());
    }

    @SmallTest
    public void testSetSolMol() throws Exception {
        s.setSolMol(10);
        Assert.assertEquals(0, s.getSolMol());
    }
}