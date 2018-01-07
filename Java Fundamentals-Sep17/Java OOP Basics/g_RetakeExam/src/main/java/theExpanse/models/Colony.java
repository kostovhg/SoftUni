package theExpanse.models;

import theExpanse.models.colonists.Colonist;

import java.util.*;
import java.util.stream.Collectors;

import static theExpanse.utilities.Constants.*;

public class Colony {

    private int maxFamilyCount;
    private int maxFamilyCapacity;
    private Map<String, Colonist> colonists;
    private Map<String, List<String>> register; // key - family id, value - colonists ids

    public Colony(int maxFamilyCount, int maxFamilyCapacity) {
        this.maxFamilyCapacity = maxFamilyCapacity;
        this.maxFamilyCount = maxFamilyCount;
        this.colonists = new HashMap<>();
        this.register = new HashMap<>();
    }

    public void addColonist(Colonist colonist) {
        String familyId = colonist.getFamilyId();
        if (!doesRegisterContainsFamily(familyId)) {
            if (this.register.size() == this.maxFamilyCount) {
                System.out.println(COLONY_IS_FULL_MESSAGE);
                return;
            }
            this.register.put(familyId, new ArrayList<>());
        }
        int familyMembersCount = getFamily(familyId).size();
        if (familyMembersCount < this.maxFamilyCapacity) {
            getFamily(familyId).add(colonist.getId());
            this.colonists.put(colonist.getId(), colonist);
        } else {
            System.out.println(FAMILY_IS_FULL_MESSAGE);
        }
    }

    public void removeColonist(String familyId, String memberId) {
        if (doesRegisterContainsFamily(familyId)) {
            if (getFamily(familyId).contains(memberId)){
                getFamily(familyId).remove(memberId);
                this.colonists.remove(memberId);
            }
            if (getFamily(familyId).size() < 1) {
                this.removeFamily(familyId);
            }
        } else {
            System.out.println(FAMILY_DOES_NOT_EXIST_MESSAGE);
        }
    }

    public void removeFamily(String id) {
        if (doesRegisterContainsFamily(id)) {
            List<String> colonists = this.register.remove(id);
            for (String colonist : colonists) {
                this.colonists.remove(colonist);
            }
        }
    }

    // Public according the task description
    public List<Colonist> getColonistsByFamilyId(String familyId) {
        return getFamily(familyId).stream()
                .sorted(Comparator.naturalOrder())
                .map(c -> this.colonists.get(c))
                .collect(Collectors.toList());
    }

    public void grow(int years) {
        this.colonists.values().forEach(c -> c.grow(years));
    }

    public int getPotential() {
        return this.colonists.values().stream().mapToInt(Colonist::getPotential).sum();
    }

    public String getCapacity() {
        int familiesCount = this.register.size();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FAMILIES_OUTPUT_FORMAT, familiesCount, this.maxFamilyCount));
        for (String familyId : this.register.keySet().stream().sorted().collect(Collectors.toList())) {
            sb.append(System.lineSeparator());
            sb.append(String.format(
                    FAMILY_CAPACITY_FORMAT,
                    familyId,
                    getFamily(familyId).size(),
                    this.maxFamilyCapacity));
        }
        return sb.toString();
    }


    public String getFamilyStatistics(String familyId) {
        if (doesRegisterContainsFamily(familyId)) {
            StringBuilder sb = new StringBuilder(familyId + ":");
            List<Colonist> colonists = this.getColonistsByFamilyId(familyId);
            for (Colonist colonist : colonists) {
                sb.append(System.lineSeparator());
                sb.append(String.format(COLONISTS_FORMAT, colonist.getId(), colonist.getPotential()));
            }
            return sb.toString().trim();
        } else {
            return FAMILY_DOES_NOT_EXIST_MESSAGE;
        }
    }

    private boolean doesRegisterContainsFamily(String familyId) {
        return this.register.containsKey(familyId);
    }

    private List<String> getFamily(String familyId) {
        return this.register.get(familyId);
    }

    public int getMaxFamilyCount() {
        return this.maxFamilyCount;
    }

    public int getMaxFamilyCapacity() {
        return this.maxFamilyCapacity;
    }
}
