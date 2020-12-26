package org.example.java_xml.create;

import org.example.java_xml.Book;
import org.example.java_xml.parse.SaxParse2.SaxParserHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * SAX 生成
 *
 * @author lifei
 */
public class SaxWrite {
    public static void main(String[] args) {
        new SaxWrite().createXml();
    }

    TransformerHandler getTransformerHandler() {
        SAXTransformerFactory tff = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        TransformerHandler handler = null;
        try {
            handler = tff.newTransformerHandler();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        return handler;
    }

    public void createXml() {
        List<Book> bookList = parseXml();
        TransformerHandler handler = getTransformerHandler();
        Transformer tf = handler.getTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        try {
            File f = new File("books4.xml");
            if (!f.exists()) {
                f.createNewFile();
            }
            Result result = new StreamResult(new FileOutputStream(f));
            handler.setResult(result);
            handler.startDocument();
            AttributesImpl attr = new AttributesImpl();
            handler.startElement("", "", "bookstore", attr);
            for (Book book : bookList) {
                attr.clear();
                attr.addAttribute("", "", "id", "", "1");
                handler.startElement("", "", "book", attr);
                // name
                if (book.getName() != null && !"".equals(book.getName().trim())) {
                    attr.clear();
                    handler.startElement("", "", "name", attr);
                    handler.characters(book.getName().toCharArray(), 0, book.getName().length());
                    handler.endElement("", "", "name");
                }
                // author
                if (book.getAuthor() != null && !"".equals(book.getAuthor().trim())) {
                    attr.clear();
                    handler.startElement("", "", "author", attr);
                    handler.characters(book.getAuthor().toCharArray(), 0, book.getAuthor().length());
                    handler.endElement("", "", "author");
                }
                // year
                if (book.getYear() != null && !"".equals(book.getYear().trim())) {
                    attr.clear();
                    handler.startElement("", "", "year", attr);
                    handler.characters(book.getYear().toCharArray(), 0, book.getYear().length());
                    handler.endElement("", "", "year");
                }
                // price
                if (book.getPrice() != null && !"".equals(book.getPrice().trim())) {
                    attr.clear();
                    handler.startElement("", "", "price", attr);
                    handler.characters(book.getPrice().toCharArray(), 0, book.getPrice().length());
                    handler.endElement("", "", "price");
                }
                // language
                if (book.getLanguage() != null && !"".equals(book.getLanguage().trim())) {
                    attr.clear();
                    handler.startElement("", "", "language", attr);
                    handler.characters(book.getLanguage().toCharArray(), 0, book.getLanguage().length());
                    handler.endElement("", "", "language");
                }
                handler.endElement("", "", "book");
            }
            handler.endElement("", "", "bookstore");
            handler.endDocument();
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> parseXml() {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SaxParserHandler handler = new SaxParserHandler();
        try {
            SAXParser saxParser = spf.newSAXParser();
            saxParser.parse("books.xml", handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return handler.getBookList();
    }
}
