package h_1984.contracts;

import h_1984.models.BaseSubject;

import java.util.Map;

public interface Observer extends ConspiracyObjects {

    void attachToSubjects(Map<String, Observable> subjects);

    void update(BaseSubject subject, String property, String oldValue, String newValue);
}
