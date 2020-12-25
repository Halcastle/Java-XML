package org.example.java_xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * SAX 解析
 *
 * @author lifei
 */
public class SaxParse {
    public static void main(String[] args) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = spf.newSAXParser();
            SaxParserHandler handler = new SaxParserHandler();
            saxParser.parse("books.xml", handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    static class SaxParserHandler extends DefaultHandler {
        String bookstoreString = "bookstore";
        String bookString = "book";
        int bookstoreIndex = 0;
        int bookIndex = 0;
        int childIndex = 0;

        /**
         * 用来标识解析开始
         *
         * @throws SAXException SAXException
         */
        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            System.out.println("SAX解析开始");
        }

        /**
         * 用来标识解析结束
         *
         * @throws SAXException SAXException
         */
        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            System.out.println("SAX解析结束");
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
            if (bookstoreString.equals(qName)) {
                bookstoreIndex++;
                bookIndex = 0;
                // System.out.println("==================开始解析第" + bookstoreIndex + "个书店==================");
            } else if (bookString.equals(qName)) {
                bookIndex++;
                childIndex = 0;
                System.out.println("==================开始解析第" + bookIndex + "本书==================");
                /*
                  解析属性1
                 */
                System.out.println("第" + bookIndex + "本书共有" + attributes.getLength() + "个属性");
                for (int i = 0; i < attributes.getLength(); i++) {
                    System.out.println("==================开始解析第" + (i + 1) + "个属性==================");
                    System.out.println("第" + (i + 1) + "个属性名：" + attributes.getQName(i)
                            + "，属性值：" + attributes.getValue(i));
                    System.out.println("==================第" + (i + 1) + "个属性解析结束==================");
                }
                /*
                  解析属性2
                 */
                System.out.println("==================开始按照属性名解析==================");
                System.out.println("属性名：id，属性值：" + attributes.getValue("id"));
                System.out.println("==================按照属性名解析结束==================");
            } else {
                childIndex++;
                System.out.println("==================开始解析第" + childIndex + "个子节点==================");
                System.out.print("第" + childIndex + "个子节点名：" + qName);
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
            if (bookstoreString.equals(qName)) {
                System.out.println("一共有" + bookIndex + "本书");
                // System.out.println("==================第" + bookstoreIndex + "个书店解析结束==================");
            } else if (bookString.equals(qName)) {
                System.out.println("第" + bookIndex + "本书共有" + childIndex + "个子节点");
                System.out.println("==================第" + bookIndex + "本书解析结束==================");
            } else {
                System.out.println("==================第" + childIndex + "个子节点解析结束==================");
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
            String value = new String(ch, start, length);
            if (!"".equals(value.trim())) {
                System.out.println("，节点值：" + value);
            }
        }
    }
}
