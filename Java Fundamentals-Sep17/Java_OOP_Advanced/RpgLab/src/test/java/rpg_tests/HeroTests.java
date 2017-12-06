package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import rpg_lab.Hero;
import rpg_lab.interfaces.RandomProvider;
import rpg_lab.interfaces.Target;
import rpg_lab.interfaces.Weapon;

public class HeroTests {

    private static final String HERO_NAME = "Goshko";
    private static final int TARGET_EXPERIENCE = 5;
    private static final String WRONG_RETURN_EXPERIENCE = "Wrong experience";

    private Weapon weaponMock;
    private Target targetMock;
    private RandomProvider rndMock;
    private Hero hero;

    @Before
    public void initTestObjects() {
        this.weaponMock = Mockito.mock(Weapon.class);
        this.targetMock = Mockito.mock(Target.class);
        this.rndMock = Mockito.mock(RandomProvider.class);
        this.hero = new Hero(HERO_NAME, this.weaponMock);
    }

    @Test
    public void attackingGainExperienceIfTargetIsDead(){

        /*Target fakeTarget = new Target() {
            public int getHealth() {
                return 0;
            }

            public void takeAttack(int attackPoints) {

            }

            public int giveExperience() {
                return TARGET_EXPERIENCE;
            }

            public boolean isDead() {
                return true;
            }
        };*/

        Mockito.when(this.targetMock.isDead()).thenReturn(true);
        Mockito.when(this.targetMock.giveExperience()).thenReturn(TARGET_EXPERIENCE);

        /*Weapon fakeWeapon = new Weapon() {
            public void attack(Target target) {

            }

            public int getAttackPoints() {
                return 10;
            }

            public int getDurabilityPoints() {
                return 0;
            }
        };*/

        // act
        hero.attack(this.targetMock, this.rndMock);

        // assert
        Assert.assertEquals(WRONG_RETURN_EXPERIENCE, TARGET_EXPERIENCE, hero.getExperience());

    }

    @Test
    public void attackGrantsLootIfTargetIsDead() {
        // arrange
        Mockito.when(this.targetMock.isDead()).thenReturn(true);
        Mockito.when(this.targetMock.dropLoot(this.rndMock)).thenReturn(this.weaponMock);

        // act
        hero.attack(this.targetMock, this.rndMock);

        // assert
        for (Weapon weapon : this.hero.getInventory()) {
            Assert.assertSame("Wrong loot", this.weaponMock, weapon);
        }
    }
}
