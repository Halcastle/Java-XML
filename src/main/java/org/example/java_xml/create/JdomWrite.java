package org.example.java_xml.create;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * DOM 生成
 *
 * @author lifei
 */
public class JdomWrite {
    public static void main(String[] args) {
        new JdomWrite().createXml();
    }

    private void createXml() {
        Element rss = new Element("rss");
        rss.setAttribute("version", "2.0");
        Document document = new Document(rss);
        Element channel = new Element("channel");
        rss.addContent(channel);
        Element title = new Element("title");
        title.setText("国内最新新闻");
        channel.addContent(title);
        Element title2 = new Element("title2");
        title2.setText("<尖括号会自动转义>");
        channel.addContent(title2);
        Format format = Format.getCompactFormat();
        format.setIndent("");
        format.setEncoding("GBK");
        XMLOutputter output = new XMLOutputter(format);
        try {
            output.output(document, new FileOutputStream("rssNews2.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
