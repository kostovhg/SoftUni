package org.softuni.mostwanted.parser.interfaces;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface Parser {

    <T> T read(Class<T> objectClass, String fileContent) throws IOException, JAXBException;

    <T> String write(T object) throws IOException, JAXBException;
}
