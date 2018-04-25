package xmlProcessingE.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

@Component
public class XmlParser implements XmlParserApi {

    @Autowired
    private FileParser fileParser;

    public <T> void writeXML(T object, String fileName) throws JAXBException, IOException {
        String path = System.getProperty("user.dir") + File.separator + fileName;
        File f = new File(path);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream outputStream = new FileOutputStream(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        jaxbMarshaller.marshal(object, bufferedWriter);
    }


    @Override
    public <T> String serialize(T t) {

        try (StringWriter writer = new StringWriter()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(t, writer);
            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> T deserialize(String src, Class<T> clazz) {

        try (StringReader reader = new StringReader(src)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return clazz.cast(unmarshaller.unmarshal(reader));
        } catch (JAXBException jaxbe) {
            jaxbe.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> T deserializeXml(String fileName, Class<T> clazz) {

        try (InputStream is = new FileInputStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return clazz.cast(unmarshaller.unmarshal(reader));
        } catch (JAXBException jaxbe) {
            jaxbe.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
