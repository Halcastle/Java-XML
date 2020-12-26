package org.example.java_xml.create;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;

/**
 * DOM4J 生成
 *
 * @author lifei
 */
public class Dom4jWrite {
    public static void main(String[] args) {
        new Dom4jWrite().createXml();
    }

    public void createXml() {
        Document document = DocumentHelper.createDocument();
        Element rss = document.addElement("rss");
        rss.addAttribute("version", "2.0");
        Element channel = rss.addElement("channel");
        Element title = channel.addElement("title");
        title.setText("国内最新新闻");
        Element title2 = channel.addElement("title2");
        title2.setText("<尖括号会自动转义>");
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("GBK");
        File file = new File("rssNews.xml");
        try {
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
