package tech.shali.project.app.web.security.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.util.HtmlUtils;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

	public XSSRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = fitlerXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);

		return fitlerXSS(value);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		return fitlerXSS(value);
	}

	/**
	 * xss过滤方法
	 * 
	 * @param value
	 *            原始值
	 * @return 过滤值
	 */
	private String fitlerXSS(String value) {
		return HtmlUtils.htmlEscape(value, "UTF-8");
	}
}
