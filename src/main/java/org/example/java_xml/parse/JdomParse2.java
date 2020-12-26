package org.example.java_xml.parse;

import org.example.java_xml.Book;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * JDOM 解析
 *
 * @author lifei
 */
public class JdomParse2 {
    public static void main(String[] args) {
        SAXBuilder saxBuilder = new SAXBuilder();
        InputStream in;
        String idString = "id";
        String nameString = "name";
        String authorString = "author";
        String yearString = "year";
        String priceString = "price";
        String languageString = "language";
        List<Book> bookEntityList = new ArrayList<>();
        Book bookEntity = null;
        try {
            in = new FileInputStream("books.xml");
            Document document = saxBuilder.build(in);
            Element rootElement = document.getRootElement();
            List<Element> bookList = rootElement.getChildren();
            for (Element book : bookList) {
                bookEntity = new Book();
                List<Attribute> attributeList = book.getAttributes();
                for (Attribute attr : attributeList) {
                    if (idString.equals(attr.getName())) {
                        bookEntity.setId(attr.getValue());
                    }
                }
                List<Element> childList = book.getChildren();
                for (Element child : childList) {
                    if (nameString.equals(child.getName())) {
                        bookEntity.setName(child.getValue());
                    } else if (authorString.equals(child.getName())) {
                        bookEntity.setAuthor(child.getValue());
                    } else if (yearString.equals(child.getName())) {
                        bookEntity.setYear(child.getValue());
                    } else if (priceString.equals(child.getName())) {
                        bookEntity.setPrice(child.getValue());
                    } else if (languageString.equals(child.getName())) {
                        bookEntity.setLanguage(child.getValue());
                    }
                }
                bookEntityList.add(bookEntity);
            }
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        for (Book book : bookEntityList) {
            System.out.println(book);
        }
    }
}
