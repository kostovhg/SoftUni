package car_dealer.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

@Component
public class XmlParser {

    @Autowired
    private FileParser fileParser;

    public <T> void writeXML(T object, String fileName) throws JAXBException, IOException {
        String path = System.getProperty("user.dir") + File.separator + fileName;
        File f = new File(path);
        if(!f.exists()) {
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
}
