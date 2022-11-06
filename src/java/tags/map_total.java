/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/TagHandler.java to edit this template
 */
package tags;

import dto.Plant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author RaeKyo
 */
public class map_total extends SimpleTagSupport {

    private ArrayList list;
    private HashMap map;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>)map;
            ArrayList<Plant> list_t = (ArrayList<Plant>)list;
            int total = list_t.stream().filter(p -> cart.containsKey(p.getPlantId())).collect(Collectors.summingInt(p -> p.getPrice() * cart.get(p.getPlantId())));
            out.println(total);
        } catch (java.io.IOException ex) {
            throw new JspException("Error in map_total tag", ex);
        }
    }

    public void setList(ArrayList list) {
        this.list = list;
    }

    public void setMap(HashMap map) {
        this.map = map;
    }
    
}
