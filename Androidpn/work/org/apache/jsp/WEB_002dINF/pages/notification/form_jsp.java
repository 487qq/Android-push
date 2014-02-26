package org.apache.jsp.WEB_002dINF.pages.notification;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class form_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/includes/taglibs.jsp");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"/error.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Admin Console</title>\r\n");
      out.write("\t<meta name=\"menu\" content=\"notification\" />    \r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<h1>Send Notifications</h1>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div style=\"margin:20px 0px;\">\r\n");
      out.write("<form action=\"notification.do?action=send\" method=\"post\" style=\"margin: 0px;\">\r\n");
      out.write("<table width=\"600\" cellpadding=\"4\" cellspacing=\"0\" border=\"0\">\r\n");
      out.write("<tr>\r\n");
      out.write("\t<td width=\"20%\">To:</td>\r\n");
      out.write("\t<td width=\"80%\">\r\n");
      out.write("\t\t<input type=\"radio\" name=\"broadcast\" value=\"Y\" checked=\"checked\" />  在线用户 \r\n");
      out.write("        <input type=\"radio\" name=\"broadcast\" value=\"N\" /> 指定用户(多个用户之间用英文;分隔)\r\n");
      out.write("        <input type=\"radio\" name=\"broadcast\" value=\"A\" /> 所有用户  \r\n");
      out.write("\t</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr id=\"trUsername\" style=\"display:none;\">\r\n");
      out.write("\t<td>Username:</td>\r\n");
      out.write("\t<td><input type=\"text\" id=\"username\" name=\"username\" value=\"\" style=\"width:380px;\" /></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("\t<td>Title:</td>\r\n");
      out.write("\t<td><input type=\"text\" id=\"title\" name=\"title\" value=\"Dokdo Island\" style=\"width:380px;\" /></td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("\t<td>Message:</td>\r\n");
      out.write("\t<td><textarea id=\"message\" name=\"message\" style=\"width:380px; height:80px;\" >Dokdo is a Korean island, the far east of the Korean territory. No doubt! No question! Don't mention it any more!</textarea></td>\r\n");
      out.write("</tr>\r\n");
      out.write("\r\n");
      out.write("<tr>\r\n");
      out.write("\t<td>URI:</td>\r\n");
      out.write("\t<td><input type=\"text\" id=\"uri\" name=\"uri\" value=\"\" style=\"width:380px;\" />\r\n");
      out.write("\t    <br/><span style=\"font-size:0.8em\">ex) http://www.dokdocorea.com, geo:37.24,131.86, tel:111-222-3333</span>\r\n");
      out.write("\t</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr>\r\n");
      out.write("\t<td>&nbsp;</td>\r\n");
      out.write("\t<td><input type=\"submit\" value=\"Submit\" /></td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table> \r\n");
      out.write("</form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\"> \r\n");
      out.write("//<![CDATA[\r\n");
      out.write(" \r\n");
      out.write("$(function() {\r\n");
      out.write("\t$('input[name=broadcast]').click(function() {\r\n");
      out.write("\t\tif ($('input[name=broadcast]')[1].checked) {\r\n");
      out.write("\t\t\t$('#trUsername').show();\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\t$('#trUsername').hide();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tif ($('input[name=broadcast]')[1].checked) {\r\n");
      out.write("\t\t$('#trUsername').show();\r\n");
      out.write("\t} else {\r\n");
      out.write("\t\t$('#trUsername').hide();\r\n");
      out.write("\t}\t\r\n");
      out.write("});\r\n");
      out.write(" \r\n");
      out.write("//]]>\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
