package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.Axe;
import rpg_lab.Dummy;
import rpg_lab.interfaces.Target;
import rpg_lab.interfaces.Weapon;

import java.util.Arrays;


public class AxeTests {

    private static final int AXE_DURABILITY = 10;
    private static final int AXE_ATTACK = 10;

    private Weapon weapon;
    private Target target;

    @Before
    public void initializeTestObjects() {
        this.weapon = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.target = Mockito.mock(Target.class);
    }

    @Test
    public void weaponLosesDurabilityAfterAttack() {
        // Arrange
        // arranged in initializeTestObjects method.
        // Act
        this.weapon.attack(this.target);

        // Assert
        Assert.assertEquals("Wrong durability",AXE_DURABILITY - 1, this.weapon.getDurabilityPoints());
    }

    @Test(expected = IllegalStateException.class)
    public void brokenWeaponCannotAttack() {
        for (int hitCount = 0; hitCount < AXE_DURABILITY + 1; hitCount++) {
            weapon.attack(this.target);
        }
    }

    /*
    @Test
    public void testUnitTestAxeTestsFirstUnitTest() throws NoSuchMethodException {
        AxeTests axeTests = new AxeTests();
        Class axeTestsClass = AxeTests.class;
        Method someMethod = axeTestsClass.getDeclaredMethod("weaponAttackLosesDurability");

        Assert.assertTrue("No such method!", someMethod != null);

    }
    */
}
