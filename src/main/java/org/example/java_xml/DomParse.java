package org.example.java_xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * DOM 解析
 *
 * @author lifei
 */
public class DomParse {
    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("books.xml");
            NodeList bookList = document.getElementsByTagName("book");
            System.out.println("DOM解析开始");
            System.out.println("一共有" + bookList.getLength() + "本书");
            for (int i = 0; i < bookList.getLength(); i++) {
                System.out.println("==================开始解析第" + (i + 1) + "本书==================");
                Node bookNode = bookList.item(i);
                /*
                  解析属性1
                 */
                NamedNodeMap attrs = bookNode.getAttributes();
                System.out.println("第" + (i + 1) + "本书共有" + attrs.getLength() + "个属性");
                for (int j = 0; j < attrs.getLength(); j++) {
                    System.out.println("==================开始解析第" + (j + 1) + "个属性==================");
                    Node attrNode = attrs.item(j);
                    System.out.println("第" + (j + 1) + "个属性名：" + attrNode.getNodeName()
                            + "，属性值：" + attrNode.getNodeValue());
                    System.out.println("==================第" + (j + 1) + "个属性解析结束==================");
                }
                /*
                  解析属性2
                 */
                System.out.println("==================开始按照属性名解析==================");
                Element bookElement = (Element) bookList.item(i);
                System.out.println("属性名：id，属性值：" + bookElement.getAttribute("id"));
                System.out.println("==================按照属性名解析结束==================");
                /*
                  解析子节点
                 */
                NodeList childList = bookNode.getChildNodes();
                System.out.println("第" + (i + 1) + "本书共有" + childList.getLength() + "个子节点");
                for (int j = 0; j < childList.getLength(); j++) {
                    System.out.println("==================开始解析第" + (j + 1) + "个子节点==================");
                    /*
                      区分text类型的Node和element类型的Node
                     */
                    Node childNode = childList.item(j);
                    if (childNode != null && childNode.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println("第" + (j + 1) + "个子节点名：" + childNode.getNodeName()
                                + "，子节点值：" + childNode.getFirstChild().getNodeValue());
                    }
                    System.out.println("==================第" + (j + 1) + "个子节点解析结束==================");
                }
                System.out.println("==================第" + (i + 1) + "本书解析结束==================");
            }
            System.out.println("DOM解析结束");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
