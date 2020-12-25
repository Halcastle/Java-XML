package org.example.java_xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * DOM4J 解析
 *
 * @author lifei
 */
public class Dom4jParse {
    public static void main(String[] args) {
        SAXReader reader = new SAXReader();
        int bookIndex = 0;
        int attributeIndex = 0;
        int childIndex = 0;
        try {
            Document document = reader.read(new File("books.xml"));
            Element bookStore = document.getRootElement();
            Iterator iterator = bookStore.elementIterator();
            while (iterator.hasNext()) {
                bookIndex++;
                attributeIndex = 0;
                childIndex = 0;
                System.out.println("==================开始解析第" + bookIndex + "本书==================");
                Element book = (Element) iterator.next();
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs) {
                    attributeIndex++;
                    System.out.println("==================开始解析第" + attributeIndex + "个属性==================");
                    System.out.println("属性名：" + attr.getName() + "，属性值：" + attr.getValue());
                    System.out.println("==================第" + attributeIndex + "个属性解析结束==================");
                }
                Iterator iterator2 = book.elementIterator();
                while (iterator2.hasNext()) {
                    childIndex++;
                    Element bookChild = (Element) iterator2.next();
                    System.out.println("==================开始解析第" + childIndex + "个子节点==================");
                    System.out.println("节点名：" + bookChild.getName() + "，节点值：" + bookChild.getStringValue());
                    System.out.println("==================第" + childIndex + "个子节点解析结束==================");
                }
                System.out.println("==================第" + bookIndex + "本书解析结束==================");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
