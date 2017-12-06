package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.Axe;
import rpg_lab.Dummy;
import rpg_lab.interfaces.RandomProvider;
import rpg_lab.interfaces.Target;
import rpg_lab.interfaces.Weapon;

import java.util.Arrays;

public class DummyTests {

    private static final int TARGET_EXPERIENCE = 10;
    private static final int TARGET_HEALTH = 100;
    private static final int ATTACK_POINTS = 10;
    private static final String TARGET_HEALTH_DOES_NOT_DECREASE = "Dummy health doesn't decrease";
    private static final String DEAD_TARGET_DOES_NOT_GIVE_XP = "Dead target doesn't give XP";
    private static final Weapon[] WEAPONS = new Weapon[]{
            new Axe(10, 10),
            new Axe(20, 20),
            new Axe(30, 30)
    };
    private static final int INDEX = 0;

    private Target target = null;

    @Before
    public void initializeTestObjects() {
        this.target = new Dummy(TARGET_HEALTH, TARGET_EXPERIENCE, Arrays.asList(WEAPONS));
    }

    @Test
    public void dummyLosesHealthWhenAttacked() {
        this.target.takeAttack(ATTACK_POINTS);
        Assert.assertEquals(TARGET_HEALTH_DOES_NOT_DECREASE, TARGET_HEALTH - ATTACK_POINTS, this.target.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void deadTargetThrowsExceptionOnAttack() {
        while (target.getHealth() >= 0) {
            this.target.takeAttack(ATTACK_POINTS);
        }
    }

    @Test
    public void deadTargetGiveXP() {
        while(!this.target.isDead()) {
            this.target.takeAttack(ATTACK_POINTS);
        }

        Assert.assertEquals(DEAD_TARGET_DOES_NOT_GIVE_XP, TARGET_EXPERIENCE, this.target.giveExperience());
    }

    @Test(expected = IllegalStateException.class)
    public void aliveDummyCantGiveXP() {
        this.target.giveExperience();
    }

    @Test
    public void targetDropsRandomLoot() {
        RandomProvider rndMock = Mockito.mock(RandomProvider.class);

        Mockito.when(rndMock.next(WEAPONS.length)).thenReturn(INDEX);

        while (!this.target.isDead()) {
            this.target.takeAttack(ATTACK_POINTS);
        }

        Weapon loot = this.target.dropLoot(rndMock);

        Assert.assertNotNull("Dropped loot was null", loot);
        Assert.assertSame("Wrong loot", WEAPONS[INDEX], loot);
    }
}
