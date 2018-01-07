package theExpanse.models;

import org.junit.Before;
import org.junit.Test;
import theExpanse.factories.ColonistFactory;
import theExpanse.models.colonists.Colonist;
import theExpanse.models.colonists.engineers.HardwareEngineer;
import theExpanse.models.colonists.engineers.SoftwareEngineer;
import theExpanse.models.colonists.medics.Surgeon;
import theExpanse.models.colonists.others.Soldier;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static theExpanse.utilities.Constants.*;

public class ColonyTest {

    // Error messages
    private static final String NOT_CORRECT_ERROR_MESSAGE = "Not correct Error Message";
    private static final String DID_NOT_THROW_EXCEPTION = "Method did not throw exception";
    private static final String REMOVING_COLONIST_ERROR_MSG = "Not correct result after removing";
    private static final String WRONG_COUNT_OF_ENTITIES_IN_COLONY = "Output for count for colonists and families is incorrect";
    private static final String WRONG_LIST_OF_COLONISTS = "Returned List<Colonist> is not correct";
    private static final String AGE_WAS_NOT_INCREASED = " age was not increased";

    // Integer constants
    private static final int MAX_FAMILY_COUNT = 2;
    private static final int MAX_FAMILY_CAPACITY = 2;

    // Family constants
    private static final String FIRST_FAMILY_NAME = "F1";
    private static final String SECOND_FAMILY_NAME = "F2";
    private static final String THIRD_FAMILY_NAME = "F3";

    // Colonist constants
    private static final String COLONIST_1_NAME = "Pesho";
    private static final String COLONIST_2_NAME = "Gosho";
    private static final String COLONIST_3_NAME = "Ivan";
    private static final String COLONIST_4_NAME = "Spas";
    private static final String COLONIST_5_NAME = "Metodii";
    private Colony colony;
    private Colonist colonist1;
    private Colonist colonist2;
    private Colonist colonist3;

    @Before
    public void initialize() throws Exception {
        this.colony = new Colony(MAX_FAMILY_COUNT, MAX_FAMILY_CAPACITY);
        this.colonist1 = mock(Soldier.class);
        when(colonist1.getFamilyId()).thenReturn(FIRST_FAMILY_NAME);
        when(colonist1.getId()).thenReturn(COLONIST_1_NAME);
        this.colonist2 = mock(Surgeon.class);
        when(colonist2.getFamilyId()).thenReturn(FIRST_FAMILY_NAME);
        when(colonist2.getId()).thenReturn(COLONIST_2_NAME);
        this.colonist3 = mock(HardwareEngineer.class);
        when(colonist3.getFamilyId()).thenReturn(THIRD_FAMILY_NAME);
        when(colonist3.getId()).thenReturn(COLONIST_3_NAME);
    }

    @Test
    public void addColonist_onEmptyColony_shouldCreateFamilyAndAddColonist() {
        // Arrange
        String expectedOutput = getFamiliesOutput(1, 1);

        // Act
        this.colony.addColonist(this.colonist1);

        // Assert
        assertEquals(WRONG_COUNT_OF_ENTITIES_IN_COLONY, expectedOutput, this.colony.getCapacity());
    }

    @Test//( expected = IllegalStateException.class)
    public void addColonist_inNonExistingFamily_InFullColony_shouldReturnCorrectExceptionMessage() {
        // Arrange
        Colonist colonist4 = mock(HardwareEngineer.class);
        when(colonist4.getFamilyId()).thenReturn(SECOND_FAMILY_NAME);
        when(colonist4.getId()).thenReturn(COLONIST_4_NAME);
        Colonist colonist5 = mock(SoftwareEngineer.class);
        when(colonist5.getFamilyId()).thenReturn(SECOND_FAMILY_NAME);
        when(colonist5.getId()).thenReturn(COLONIST_5_NAME);
        this.colony.addColonist(colonist1);
        this.colony.addColonist(colonist2);
        this.colony.addColonist(colonist4);
        this.colony.addColonist(colonist5);
        String expectedOutput = COLONY_IS_FULL_MESSAGE;
        // Act
        try {
            this.colony.addColonist(colonist3);
            fail(DID_NOT_THROW_EXCEPTION);
        } catch (IllegalStateException ise) {
            assertEquals(NOT_CORRECT_ERROR_MESSAGE, expectedOutput, ise.getMessage());
        }
    }

    @Test
    public void addColonist_onFullFamily_shouldReturnCorrectExceptionMessage() {
        // Arrange
        this.colony.addColonist(colonist1);
        this.colony.addColonist(colonist2);
        this.colonist3 = mock(HardwareEngineer.class);
        when(colonist3.getFamilyId()).thenReturn(FIRST_FAMILY_NAME);
        when(colonist3.getId()).thenReturn("Ivan");

        String expectedOutput = FAMILY_IS_FULL_MESSAGE;
        // Act
        try {
            colony.addColonist(colonist3);
            fail(DID_NOT_THROW_EXCEPTION);
        } catch (IllegalStateException ise) {
            assertEquals(NOT_CORRECT_ERROR_MESSAGE, expectedOutput, ise.getMessage());
        }
    }

    @Test
    public void removeColonist_onExistingFamily_shouldRemoveColonist() {
        // Arrange
        this.colony.addColonist(colonist1);
        this.colony.addColonist(colonist2);
        String expected = getFamiliesOutput(1, 1);
        // Act
        this.colony.removeColonist(FIRST_FAMILY_NAME, COLONIST_1_NAME);
        // Assert
        assertEquals(REMOVING_COLONIST_ERROR_MSG, expected, this.colony.getCapacity());
    }

    @Test
    public void removeColonist_onNonExistingFamily_returnErrorMessage() {
        // Arrange
        String expected = FAMILY_DOES_NOT_EXIST_MESSAGE;

        // Act Assert
        try {
            this.colony.removeColonist(FIRST_FAMILY_NAME, COLONIST_1_NAME);
            fail(DID_NOT_THROW_EXCEPTION);
        } catch (IllegalArgumentException ise) {
            assertEquals(NOT_CORRECT_ERROR_MESSAGE, expected, ise.getMessage());
        }
    }

    @Test
    public void removeColonist_ifItWasLastInFamily_shouldRemoveFamilyToo() {
        // Arrange
        this.colony.addColonist(colonist1);
        String expected = getFamiliesOutput(0, 0);

        // Act
        this.colony.removeColonist(colonist1.getFamilyId(), colonist1.getId());

        // Assert
        assertEquals(WRONG_COUNT_OF_ENTITIES_IN_COLONY, expected, this.colony.getCapacity());
    }

    @Test
    public void removeFamily_ifItExist() {
        // Arrange
        this.colony.addColonist(colonist1);
        String expected = getFamiliesOutput(0, 0);

        // Act
        this.colony.removeFamily(colonist1.getFamilyId());

        // Assert
        assertEquals(WRONG_COUNT_OF_ENTITIES_IN_COLONY, expected, this.colony.getCapacity());
    }

    @Test
    public void removeFamily_ifNotExist_shouldReturnError() {
        // Arrange
        String expected = FAMILY_DOES_NOT_EXIST_MESSAGE;

        // Act Assert
        try {
            this.colony.removeFamily(FIRST_FAMILY_NAME);
            fail(DID_NOT_THROW_EXCEPTION);
        } catch (IllegalArgumentException ise) {
            assertEquals(NOT_CORRECT_ERROR_MESSAGE, expected, ise.getMessage());
        }
    }

    @Test
    public void getColonistsByFamilyId_shouldSortedListOfColonists() {
        // Arrange
        this.colony.addColonist(colonist1);
        this.colony.addColonist(colonist2);
        List<Colonist> expected = Arrays.asList(colonist1, colonist2);
        expected.sort(Comparator.comparing(Colonist::getId));

        // Act
        List<Colonist> actual = this.colony.getColonistsByFamilyId(colonist1.getFamilyId());

        // Assert
        assertEquals(WRONG_LIST_OF_COLONISTS, expected, actual);

    }

    @Test
    public void grow_shouldIncreaseAllColonistsAge() {
        // Arrange
        this.colony.addColonist(ColonistFactory.create(Arrays.asList(SOLDIER_CLASS_NAME, COLONIST_1_NAME, FIRST_FAMILY_NAME, "10", "15")));
        this.colony.addColonist(ColonistFactory.create(Arrays.asList(SURGEON_CLASS_NAME, COLONIST_2_NAME , FIRST_FAMILY_NAME, "10", "30", SURGEON_PRECISE_SIGN)));

        int expected_1 = 30;
        int expected_2 = 45;

        // Act
        this.colony.grow(15);
        Map<String, Colonist> colonists = getColonistsById();
        int actual_1 = colonists.get(COLONIST_1_NAME).getAge();
        int actual_2 = colonists.get(COLONIST_2_NAME).getAge();

        // Assert
        assertEquals(COLONIST_1_NAME + AGE_WAS_NOT_INCREASED, expected_1, actual_1);
        assertEquals(COLONIST_1_NAME + AGE_WAS_NOT_INCREASED, expected_2, actual_2);
    }

    @Test
    public void getPotential_shouldReturnCorrectResult() {

    }

    @Test
    public void getCapacity_shouldReturnCorrectResult() throws Exception {
        // getCapacity already is used in few tests because
        // the tested methods are returning String
        this.addColonist_onEmptyColony_shouldCreateFamilyAndAddColonist();
        this.initialize(); //
        this.removeColonist_ifItWasLastInFamily_shouldRemoveFamilyToo();
        this.initialize();
        this.removeColonist_onExistingFamily_shouldRemoveColonist();
        // Probably bad practice!?
    }

    @Test
    public void getFamilyStatistics_shouldReturnCorrectResult() {

    }

    private Map<String, Colonist> getColonistsById() {
        return this.colony.getColonistsByFamilyId(FIRST_FAMILY_NAME).stream()
                .collect(Collectors.toMap(Colonist::getId, c -> c));
    }

    private String getFamiliesOutput(int countOfFamilies, int countOfColonists) {
        StringBuilder sb = new StringBuilder();
        sb
                .append(String.format(FAMILIES_OUTPUT_FORMAT, countOfFamilies, this.colony.getMaxFamilyCapacity()));
        if (countOfFamilies > 0) {
            sb.append(System.lineSeparator())
                    .append(String.format(FAMILY_CAPACITY_FORMAT, FIRST_FAMILY_NAME, countOfColonists, this.colony.getMaxFamilyCount()));
        }
        return sb.toString();
    }
}