package hell.entities.miscellaneous;

import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HeroInventoryTest {

    private final static int SINGLE_COMMON_ITEM_BONUS = 2_000_000_000;

    private Inventory inventory;

    @Before
    public void initialize() throws Exception {
        this.inventory = new HeroInventory();
        this.seedCommonItems();
    }

    @Test
    public void getTotalStrengthBonusShouldReturnCorrectResult() {
        // Arrange
        long expected =  SINGLE_COMMON_ITEM_BONUS;

        // Act
        long actual = this.inventory.getTotalStrengthBonus();

        // Assert
        Assert.assertEquals("Strength is not calculated correctly", expected, actual);
    }

    @Test
    public void getTotalStrengthBonusShouldTestWithWrongResulWillWorkCorrectly() {
        // Arrange
        long expected = 0;

        // Act
        long actual = this.inventory.getTotalStrengthBonus();

        // Assert
        Assert.assertNotEquals("Strength is not calculated correctly", expected, actual);
    }

    @Test
    public void getTotalAgilityBonusShouldReturnCorrectResult() {
        // Arrange
        long expected = SINGLE_COMMON_ITEM_BONUS;

        // Act
        long actual = this.inventory.getTotalAgilityBonus();

        // Assert
        Assert.assertEquals("Agility is not calculated correctly", expected, actual);
    }

    @Test
    public void getTotalIntelligenceBonusShouldReturnCorrectResult() {
        // Arrange
        long expected = SINGLE_COMMON_ITEM_BONUS;

        // Act
        long actual = this.inventory.getTotalIntelligenceBonus();

        // Assert
        Assert.assertEquals("Intelligence is not calculated correctly", expected, actual);
    }

    @Test
    public void getTotalHitPointsBonusShouldReturnCorrectResult() {
        // Arrange
        long expected = SINGLE_COMMON_ITEM_BONUS;

        // Act
        long actual = this.inventory.getTotalHitPointsBonus();

        // Assert
        Assert.assertEquals("HitPoints is not calculated correctly", expected, actual);
    }

    @Test
    public void getTotalDamageBonus() {
        // Arrange
        long expected = SINGLE_COMMON_ITEM_BONUS;

        // Act
        long actual = this.inventory.getTotalDamageBonus();

        // Assert
        Assert.assertEquals("Damage bonus is not calculated correctly", expected, actual);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void addCommonItemShouldCreateNewItemAndRemoveRecipeComponents() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        this.seedRecipeItems();
        Item mock = mock(Item.class);
        long expectedCommonItemsLength = 2;

        // Act
        this.inventory.addCommonItem(mock);
        Field commonItemsField = this.inventory.getClass().getDeclaredField("commonItems");
        commonItemsField.setAccessible(true);
        Map<String, Item> commonItems = (Map<String, Item>) commonItemsField.get(this.inventory);

        // Assert
        Assert.assertEquals("Common Items length is not correct", expectedCommonItemsLength, commonItems.size());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void addRecipeItemShouldIncreaseRecipeItemsByOne() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        this.seedRecipeItems();
        Recipe mock = mock(Recipe.class);
        long expectedCommonItemsLength = 2;

        // Act
        this.inventory.addRecipeItem(mock);
        Field commonItemsField = this.inventory.getClass().getDeclaredField("recipeItems");
        commonItemsField.setAccessible(true);
        Map<String, Recipe> recipeItems = (Map<String, Recipe>) commonItemsField.get(this.inventory);

        // Assert
        Assert.assertEquals("Recepie Items count is not correct", expectedCommonItemsLength, recipeItems.size());
    }

    @SuppressWarnings("unchecked")
    private void seedCommonItems() throws NoSuchFieldException, IllegalAccessException {
        Field commonItemsField = this.inventory.getClass().getDeclaredField("commonItems");
        commonItemsField.setAccessible(true);
        Map<String, Item> commonItems = (Map<String, Item>) commonItemsField.get(this.inventory);
        Item mock1 = mock(Item.class);
        when(mock1.getStrengthBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock1.getAgilityBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock1.getDamageBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock1.getHitPointsBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock1.getIntelligenceBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock1.getName()).thenReturn("commonItemsMock1");

        Item mockSINGLE_COMMON_ITEM_BONUS = mock(Item.class);
        when(mockSINGLE_COMMON_ITEM_BONUS.getStrengthBonus()).thenReturn(-SINGLE_COMMON_ITEM_BONUS);
        when(mockSINGLE_COMMON_ITEM_BONUS.getAgilityBonus()).thenReturn(-SINGLE_COMMON_ITEM_BONUS);
        when(mockSINGLE_COMMON_ITEM_BONUS.getDamageBonus()).thenReturn(-SINGLE_COMMON_ITEM_BONUS);
        when(mockSINGLE_COMMON_ITEM_BONUS.getHitPointsBonus()).thenReturn(-SINGLE_COMMON_ITEM_BONUS);
        when(mockSINGLE_COMMON_ITEM_BONUS.getIntelligenceBonus()).thenReturn(-SINGLE_COMMON_ITEM_BONUS);
        when(mockSINGLE_COMMON_ITEM_BONUS.getName()).thenReturn("commonItemsMockSINGLE_COMMON_ITEM_BONUS");

        Item mock3 = mock(Item.class);
        when(mock3.getStrengthBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock3.getAgilityBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock3.getDamageBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock3.getHitPointsBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock3.getIntelligenceBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock3.getName()).thenReturn("commonItemsMock3");

        commonItems.put(mock1.getName(), mock1);
        commonItems.put(mockSINGLE_COMMON_ITEM_BONUS.getName(), mockSINGLE_COMMON_ITEM_BONUS);
        commonItems.put(mock3.getName(), mock3);
    }

    @SuppressWarnings("unchecked")
    private void seedRecipeItems() throws NoSuchFieldException, IllegalAccessException {
        Field recipeItemsField = this.inventory.getClass().getDeclaredField("recipeItems");
        recipeItemsField.setAccessible(true);
        Map<String, Recipe> recipeItems = (Map<String, Recipe>) recipeItemsField.get(this.inventory);
        Recipe mock1 = mock(Recipe.class);
        when(mock1.getStrengthBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock1.getAgilityBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock1.getDamageBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock1.getHitPointsBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock1.getIntelligenceBonus()).thenReturn(SINGLE_COMMON_ITEM_BONUS);
        when(mock1.getName()).thenReturn("recipeItemMock1");
        String[] requiredItems = {"commonItemsMock1", "commonItemsMockSINGLE_COMMON_ITEM_BONUS", "commonItemsMock3"};
        when(mock1.getRequiredItems()).thenReturn(Arrays.asList(requiredItems));
        recipeItems.put(mock1.getName(), mock1);
    }
}
