package h_1984.models;

import h_1984.annotations.ObservableField;
import h_1984.contracts.Observable;
import h_1984.contracts.Observer;

import java.lang.reflect.Field;
import java.util.*;

public class Institution extends ConspiracyObject implements Observer {

    private String name;
    private StringBuilder log = new StringBuilder();
    private int changesRegistered = 0;
    private Map<String, Class<?>> propertiesToWatch = new LinkedHashMap<>();
    private List<Observable> subjects = new ArrayList<>();

    public Institution(String id, String name, String... propertiesToWatch) {
        super(id);
        this.name = name;
        for (int i = 0; i < propertiesToWatch.length; i++) {
            this.propertiesToWatch.putIfAbsent(propertiesToWatch[i], null);
        }
    }

    @Override
    public void attachToSubjects(Map<String, Observable> subjects) {
        for (Observable observableObject : subjects.values()) {
            Class<?> type = observableObject.getClass();
            List<Field> allFields = new ArrayList<>();
            allFields.addAll(Arrays.asList(type.getDeclaredFields()));
            allFields.addAll(Arrays.asList(type.getSuperclass().getDeclaredFields()));
            for (Field field : allFields) {
                String fieldName = field.getName();
                Class<?> fieldClass = field.getType();
                if (field.isAnnotationPresent(ObservableField.class) && this.propertiesToWatch.containsKey(fieldName)) {
                    this.propertiesToWatch.putIfAbsent(fieldName, fieldClass);
                    if (!this.subjects.contains(observableObject)) {
                        this.subjects.add(observableObject);
                        observableObject.attach(this);
                    }
                }
            }
        }
    }

    @Override
    public void update(BaseSubject subject, String property, String oldValue, String newValue) {
        if (this.propertiesToWatch.containsKey(property)) {
            this.log.append(String.format("--%s(ID:%s) changed %s(%s) from %s to %s",
                    subject.getClass().getSimpleName(),
                    subject.getId(),
                    property,
                    this.propertiesToWatch.get(property).getSimpleName(),
                    oldValue,
                    newValue
            )).append(System.lineSeparator());
            changesRegistered++;
        }

    }
    //"--<entity type>(ID:<id>) changed <field name>(<field type>) from <old value> to <new value>

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public String toString() {
        return String.format("%s: %d changes registered", this.name, this.changesRegistered) + System.lineSeparator() + this.log.toString().trim();
    }
}
