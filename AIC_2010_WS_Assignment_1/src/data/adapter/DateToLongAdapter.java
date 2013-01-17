package data.adapter;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * <p>DateToLongAdapter changes the date to a long value, which 
 * corresponds to the milliseconds passed since January, 1st 1970
 * when serialized into XML output format. If a long-value got passed
 * to this adapter from an XML-file this value will be translated into
 * a date reprensentation.</p>
 * <p>This class is based on an example taken from:
 * <code>http://blogs.sun.com/CoreJavaTechTips/entry/exchanging_data_with_xml_and</code></p>
 * 
 * @author roman
 */
public class DateToLongAdapter extends XmlAdapter<Long, Date>
{

	// map Java-object to XML type
	/**
	 * Converts a Java {@link java.util.Date}-instance to a long type 
	 * for the XML output.
	 * @param date {@link Date} which should be converted to an XML type.
	 * @return Returns a Long representation of the {@link Date} in 
	 * 		milliseconds, starting with January, 1st 1970.
	 */
	@Override
	public Long marshal(Date date) throws Exception
	{
		return date.getTime();
	}

	// reverse operation: map XML type to Java
	/**
	 * Maps an XML element of type long to a {@link java.util.Date}-
	 * representation in Java.
	 * @param date Long value which should be converted to {@link Date}.
	 * @return Returns a {@link Date}-representation of the XML type Long.
	 */
	@Override
	public Date unmarshal(Long date) throws Exception
	{
		return new Date(date);
	}	
}
