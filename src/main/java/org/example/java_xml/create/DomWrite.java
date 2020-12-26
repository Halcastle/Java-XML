package org.example.java_xml.create;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * DOM 生成
 *
 * @author lifei
 */
public class DomWrite {
    public static void main(String[] args) {
        new DomWrite().createXml();
    }

    DocumentBuilder getDocumentBuilder() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return db;
    }

    Transformer getTransformer() {
        TransformerFactory tff = TransformerFactory.newInstance();
        Transformer tf = null;
        try {
            tf = tff.newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        return tf;
    }

    public void createXml() {
        Document document = getDocumentBuilder().newDocument();
        document.setXmlStandalone(true);
        Element bookstore = document.createElement("bookstore");
        Element book = document.createElement("book");
        book.setAttribute("id", "1");
        Element name = document.createElement("name");
        name.setTextContent("小王子");
        book.appendChild(name);
        bookstore.appendChild(book);
        document.appendChild(bookstore);
        Transformer tf = getTransformer();
        try {
            tf.transform(new DOMSource(document), new StreamResult(new File("books3.xml")));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
