package org.example.java_xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SAX 解析
 *
 * @author lifei
 */
public class SaxParse2 {
    public static void main(String[] args) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = spf.newSAXParser();
            SaxParserHandler handler = new SaxParserHandler();
            saxParser.parse("books.xml", handler);
            for (Book book : handler.bookList) {
                System.out.println(book);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    static class SaxParserHandler extends DefaultHandler {
        String bookString = "book";
        String idString = "id";
        String nameString = "name";
        String authorString = "author";
        String yearString = "year";
        String priceString = "price";
        String languageString = "language";
        List<Book> bookList = new ArrayList<>();
        int bookIndex = 0;
        Book book = null;
        String currentValue = null;

        /**
         * 用来标识解析开始
         *
         * @throws SAXException SAXException
         */
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
        }

        /**
         * 用来标识解析结束
         *
         * @throws SAXException SAXException
         */
        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        /**
         * 用来遍历xml文件的开始标签
         *
         * @param uri        uri
         * @param localName  localName
         * @param qName      qName
         * @param attributes attributes
         * @throws SAXException SAXException
         */
        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes)
                throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (bookString.equals(qName)) {
                bookIndex++;
                book = new Book();
                for (int i = 0; i < attributes.getLength(); i++) {
                    if (idString.equals(attributes.getQName(i))) {
                        book.setId(attributes.getValue(i));
                    }
                }
            }
        }

        /**
         * 用来遍历xml文件的结束标签
         *
         * @param uri       uri
         * @param localName localName
         * @param qName     qName
         * @throws SAXException SAXException
         */
        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            super.endElement(uri, localName, qName);
            if (bookString.equals(qName)) {
                bookList.add(book);
                book = null;
            } else if (nameString.equals(qName)) {
                book.setName(currentValue);
            } else if (authorString.equals(qName)) {
                book.setAuthor(currentValue);
            } else if (yearString.equals(qName)) {
                book.setYear(currentValue);
            } else if (priceString.equals(qName)) {
                book.setPrice(currentValue);
            } else if (languageString.equals(qName)) {
                book.setLanguage(currentValue);
            }
        }

        /**
         * 解析节点值
         *
         * @param ch     ch
         * @param start  start
         * @param length length
         * @throws SAXException SAXException
         */
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            currentValue = new String(ch, start, length);
        }
    }
}
