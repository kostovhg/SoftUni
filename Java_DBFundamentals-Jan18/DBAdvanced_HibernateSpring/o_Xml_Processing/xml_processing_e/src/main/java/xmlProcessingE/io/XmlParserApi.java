package xmlProcessingE.io;

import java.util.List;

public interface XmlParserApi {

    <T> String serialize(T t);

    <T> T deserialize(String src, Class<T> clazz);

    <T> T deserializeXml(String src, Class<T> clazz);
}
