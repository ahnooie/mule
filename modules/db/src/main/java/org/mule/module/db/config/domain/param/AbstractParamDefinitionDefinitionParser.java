/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.db.config.domain.param;

import org.mule.config.spring.parsers.AbstractMuleBeanDefinitionParser;
import org.mule.config.spring.parsers.generic.AutoIdUtils;
import org.mule.module.db.domain.type.DbType;
import org.mule.module.db.domain.type.JdbcTypes;
import org.mule.module.db.domain.type.UnknownDbType;

import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class AbstractParamDefinitionDefinitionParser extends AbstractMuleBeanDefinitionParser
{

    @Override
    protected String resolveId(Element element, AbstractBeanDefinition definition, ParserContext context) throws BeanDefinitionStoreException
    {
        return getBeanName(element);
    }

    @Override
    public String getBeanName(Element element)
    {
        return AutoIdUtils.uniqueValue("param." + element.getAttribute(ATTRIBUTE_NAME));
    }

    @Override
    protected void checkElementNameUnique(Element element)
    {
        // Don't care about this
    }

    @Override
    protected boolean isSingleton()
    {
        return true;
    }

    protected int getListElementIndex(Element element)
    {
        int index = 1;

        Node parentNode = element.getParentNode();
        NodeList childNodes = parentNode.getChildNodes();
        int itemIndex = 0;
        while (itemIndex < childNodes.getLength())
        {
            Node item = childNodes.item(itemIndex++);
            if (item == element)
            {
                break;
            }

            if (item instanceof Element)
            {
                index++;
            }
        }
        return index;
    }

    protected String getName(Element element)
    {
        return element.getAttribute("name");
    }

    protected DbType getType(Element element)
    {
        Element typeElement = DomUtils.getChildElementByTagName(element, "jdbc-type");

        if (typeElement != null)
        {
            String type = typeElement.getAttribute("type");
            element.removeChild(typeElement);

            DbType dbType = JdbcTypes.lookup(type);

            if (dbType == null)
            {
                throw new IllegalArgumentException("Undefined type:" + type);
            }

            return dbType;
        }
        else
        {
            return UnknownDbType.getInstance();
        }
    }

    protected String getValue(Element element)
    {
        if (element.hasAttribute("value"))
        {
            return element.getAttribute("value");
        }
        else
        {
            return element.getAttribute("defaultValue");
        }
    }
}
