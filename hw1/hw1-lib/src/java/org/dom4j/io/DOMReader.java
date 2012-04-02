/*
 * Copyright 2001-2005 (C) MetaStuff, Ltd. All Rights Reserved.
 *
 * This software is open source.
 * See the bottom of this file for the licence.
 */

package org.dom4j.io;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.tree.NamespaceStack;
import org.w3c.dom.Node;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * <p>
 * <code>DOMReader</code> navigates a W3C DOM tree and creates a DOM4J tree from
 * it.
 * </p>
 * 
 * @author <a href="mailto:jstrachan@apache.org">James Strachan </a>
 * @version $Revision: 1.2 $
 */
public class DOMReader {
	/** <code>DocumentFactory</code> used to create new document objects */
	private DocumentFactory factory;

	/** stack of <code>Namespace</code> and <code>QName</code> objects */
	private NamespaceStack namespaceStack;

	public DOMReader() {
		this.factory = DocumentFactory.getInstance();
		this.namespaceStack = new NamespaceStack(factory);
	}

	public DOMReader(DocumentFactory factory) {
		this.factory = factory;
		this.namespaceStack = new NamespaceStack(factory);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return the <code>DocumentFactory</code> used to create document objects
	 */
	public DocumentFactory getDocumentFactory() {
		return factory;
	}

	/**
	 * <p>
	 * This sets the <code>DocumentFactory</code> used to create new documents.
	 * This method allows the building of custom DOM4J tree objects to be
	 * implemented easily using a custom derivation of {@link DocumentFactory}
	 * </p>
	 * 
	 * @param docFactory
	 *            <code>DocumentFactory</code> used to create DOM4J objects
	 */
	public void setDocumentFactory(DocumentFactory docFactory) {
		this.factory = docFactory;
		this.namespaceStack.setDocumentFactory(factory);
	}

	public Document read(org.w3c.dom.Document domDocument) {
		if (domDocument instanceof Document) {
			return (Document) domDocument;
		}

		Document document = createDocument();

		clearNamespaceStack();

		org.w3c.dom.NodeList nodeList = domDocument.getChildNodes();

		for (int i = 0, size = nodeList.getLength(); i < size; i++) {
			readTree(nodeList.item(i), document);
		}

		return document;
	}

	// Implementation methods

	protected void readTree(Node node, Branch current) {
		String name = node.getNodeName(), value = node.getNodeValue();

		switch (node.getNodeType()) {
		case Node.ELEMENT_NODE:
			readElement(node, current);
			break;

		case Node.PROCESSING_INSTRUCTION_NODE:
			invokeMethod(current, "addProcessingInstruction", name, value);
			break;

		case Node.DOCUMENT_TYPE_NODE:
			org.w3c.dom.DocumentType domDocType = (org.w3c.dom.DocumentType) node;
			invokeMethod(current, "addProcessingInstruction",
					domDocType.getName(), domDocType.getPublicId(),
					domDocType.getSystemId());
			break;

		case Node.TEXT_NODE:
			invokeMethod(current, "addText", value);
			break;
		case Node.CDATA_SECTION_NODE:
			invokeMethod(current, "addCDATA", value);
			break;
		case Node.COMMENT_NODE:
			invokeMethod(current, "addComment", value);
			break;

		case Node.ENTITY_REFERENCE_NODE:
			Node firstChild = node.getFirstChild();
			String nodeVal = "";
			if (firstChild != null) {
				nodeVal = firstChild.getNodeValue();
			}
			value = nodeVal;
		case Node.ENTITY_NODE:
			invokeMethod(current, "addEntity", name, value);
			break;

		default:
			System.out.println("WARNING: Unknown DOM node type: "
					+ node.getNodeType());
		}
	}

	private void invokeMethod(Branch current, String methodName, String... args) {
		Class[] argTypes = new Class[args.length];
		Arrays.fill(argTypes, String.class);
		try {
			current.getClass().getMethod(methodName, argTypes)
					.invoke(current, args);
		} catch (Exception e) {

		}
	}

	protected void readElement(Node node, Branch current) {
		int previouslyDeclaredNamespaces = namespaceStack.size(), i;
		Element element = current.addElement(getQName(node));

		org.w3c.dom.NamedNodeMap attributeList = node.getAttributes();

		if (attributeList != null) {
			for (i = 0; i < attributeList.getLength(); i++) {
				Node attribute = attributeList.item(i);
				String name = attribute.getNodeName(), value = attribute
						.getNodeValue();
				if (!name.startsWith("xmlns")) {
					element.addAttribute(getQName(attribute), value);
					continue;
				}
				element.add(namespaceStack.addNamespace(getPrefix(name), value));
			}

		}

		// Recurse on child nodes
		org.w3c.dom.NodeList children = node.getChildNodes();

		for (i = 0; i < children.getLength(); i++)
			readTree(children.item(i), element);

		// pop namespaces from the stack
		while (namespaceStack.size() > previouslyDeclaredNamespaces)
			namespaceStack.pop();

	}

	private QName getQName(Node node) {
		return namespaceStack.getQName(node.getNamespaceURI(),
				node.getLocalName(), node.getNodeName());
	}

	protected Namespace getNamespace(String prefix, String uri) {
		return getDocumentFactory().createNamespace(prefix, uri);
	}

	protected Document createDocument() {
		return getDocumentFactory().createDocument();
	}

	protected void clearNamespaceStack() {
		namespaceStack.clear();

		if (!namespaceStack.contains(Namespace.XML_NAMESPACE)) {
			namespaceStack.push(Namespace.XML_NAMESPACE);
		}
	}

	private String getPrefix(String xmlnsDecl) {
		int index = xmlnsDecl.indexOf(':', 5);

		if (index != -1) {
			return xmlnsDecl.substring(index + 1);
		} else {
			return "";
		}
	}
}

/*
 * Redistribution and use of this software and associated documentation
 * ("Software"), with or without modification, are permitted provided that the
 * following conditions are met:
 * 
 * 1. Redistributions of source code must retain copyright statements and
 * notices. Redistributions must also contain a copy of this document.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * 3. The name "DOM4J" must not be used to endorse or promote products derived
 * from this Software without prior written permission of MetaStuff, Ltd. For
 * written permission, please contact dom4j-info@metastuff.com.
 * 
 * 4. Products derived from this Software may not be called "DOM4J" nor may
 * "DOM4J" appear in their names without prior written permission of MetaStuff,
 * Ltd. DOM4J is a registered trademark of MetaStuff, Ltd.
 * 
 * 5. Due credit should be given to the DOM4J Project - http://www.dom4j.org
 * 
 * THIS SOFTWARE IS PROVIDED BY METASTUFF, LTD. AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL METASTUFF, LTD. OR ITS CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Copyright 2001-2005 (C) MetaStuff, Ltd. All Rights Reserved.
 */
