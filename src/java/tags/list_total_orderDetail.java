/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/TagHandler.java to edit this template
 */
package tags;

import dto.OrderDetail;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author RaeKyo
 */
public class list_total_orderDetail extends SimpleTagSupport {

    private ArrayList list;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            ArrayList<OrderDetail> list_detail = (ArrayList<OrderDetail>)list;
            int total = list_detail.stream().collect(Collectors.summingInt(o -> o.getPrice() * o.getQuantity()));
            out.println(total);
        } catch (java.io.IOException ex) {
            throw new JspException("Error in list_total_orderDetail tag", ex);
        }
    }

    public void setList(ArrayList list) {
        this.list = list;
    }
    
}
