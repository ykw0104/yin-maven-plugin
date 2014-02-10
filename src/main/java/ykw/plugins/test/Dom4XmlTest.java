package ykw.plugins.test;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Dom4XmlTest  {

	public static void main(String[] args) throws Exception {
		new Dom4XmlTest().execute2();
	}

	public void execute() throws Exception {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		BufferedInputStream in = new BufferedInputStream(
				Files.newInputStream(Paths.get("d:\\pom.xml")));
		Document xmlDoc = builder.parse(in);
		Element root = xmlDoc.getDocumentElement();
		DocumentType doctype = xmlDoc.getDoctype();
		System.out.println(doctype);
		
		NodeList children = root.getChildNodes();
		Node[] nodes = new Node[children.getLength()];
		for (int i = 0; i < nodes.length; ++i) {
			nodes[i] = children.item(i);
		}
		System.out.println(nodes);

		Vector<Element> elements = new Vector<Element>();
		Node node = null;
		for (int i = 0; i < nodes.length; ++i) {
			node = children.item(i);
			if (node instanceof Element) {
				elements.add((Element)node);
			}
		}
		System.out.println(elements);
	}

	public void execute2() throws Exception {
		Path pom = Paths.get("d:\\pom.xml");
		
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document pomDoc = builder.parse(pom.toFile());
		//,获取param的key值
//		List<String> nodeValue = new ArrayList<String>();
//		NodeList paramNodeList = pomDoc.getElementsByTagName("param");
//		for(int i=0;i<paramNodeList.getLength();i++){
//			String value = paramNodeList.item(i).getFirstChild().getNodeValue();
//			System.out.println(value);
//			nodeValue.add(value);
//		}
	  
		NodeList envNodeList  = pomDoc.getElementsByTagName("envArray");
		NodeList paramNodeList = envNodeList.item(0).getChildNodes();
		for(int i=0;i<paramNodeList.getLength();i++){
			if(paramNodeList.item(i).getNodeType()==Node.ELEMENT_NODE){
				System.out.println(paramNodeList.item(i).getTextContent());
			}
		}
	}
	
	
}
