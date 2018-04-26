package org.softuni.ruk.parser;

import org.softuni.ruk.parser.interfaces.Parser;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component(value = "XMLParser")
public class XMLParser implements Parser {

    private JAXBContext jaxbContext;

    @Override
    public <T> T read(Class<T> objectClass, String fileContent) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader sr = new StringReader(fileContent);
            //unmarshaller.setAdapter(new DateTimeAdapter());
            //XmlAdapter adapter = unmarshaller.getAdapter(DateTimeAdapter.class);
            return (T) unmarshaller.unmarshal(sr);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public <T> String write(T object) throws IOException, JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter writer = new StringWriter();
        marshaller.marshal(object, writer);

        return writer.toString();
    }

    public class DateTimeAdapter extends XmlAdapter<String, Date> {

        private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        public DateTimeAdapter() {
        }

        @Override
        public Date unmarshal(String s) throws Exception {
            return this.format.parse(s);
        }

        @Override
        public String marshal(Date date) throws Exception {
            return this.format.format(date);
        }
    }
}
