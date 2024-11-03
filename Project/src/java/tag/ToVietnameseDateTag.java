/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/TagHandler.java to edit this template
 */
package tag;

import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.JspFragment;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;
/**
 *
 * @author lam
 */
public class ToVietnameseDateTag extends SimpleTagSupport {
    private Date value;

    public void setValue(Date value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if (value != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd 'tháng' MM 'năm' yyyy");
            String vietnameseDate = "Ngày " + sdf.format(value);
            out.print(vietnameseDate); 
        }
    }
}