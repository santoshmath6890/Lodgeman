/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lodgeman.lalitman.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author svastrad
 */
public class CustomAccRptFactory {

    private ArrayList<CustomAccRpt> custBean = new ArrayList();
    private CustomAccMonthlyRpt accmnthRpt[];
    private CustomAccRpt accRpt[];

    private ArrayList<CustomAccMonthlyRpt> custMnthBean = new ArrayList();

    public void insert(CustomAccRpt cr) {
        custBean.add(cr);
    }

    public void addMonthlyBean(ArrayList<CustomAccMonthlyRpt> temp) {
        this.custMnthBean = temp;
    }

    public void calMonthlyRpt() {
        String exmptedTot = "";

        Integer taxableTot = 0;
        Integer taxCollectedTot = 0;
        Integer grossTot = 0;
        Date billDate = null;

        if (custBean.size() != 0) {
            billDate = custBean.get(0).getBillDate();
        }

        for (int i = 0; i < custBean.size(); i++) {
            exmptedTot += custBean.get(i).getExampted();
            taxableTot += custBean.get(i).getTaxable();
            taxCollectedTot += custBean.get(i).getTaxCollected();
            grossTot += custBean.get(i).getGrossTotal();
        }

        custMnthBean.add(new CustomAccMonthlyRpt(exmptedTot, billDate, taxableTot, taxCollectedTot, grossTot));
        custBean.clear();
    }

    public void display() {
        System.out.println("SIZE: " + custMnthBean.size());
        for (int i = 0; i < custMnthBean.size(); i++) {
            System.out.println("Date: " + custMnthBean.get(i).getBillDate());
        }
    }

    public void clear1() {
        custBean.clear();
    }

    public Object[] getMonthlyBeanArry() {
        if (custMnthBean.size() != 0) {
            accmnthRpt = new CustomAccMonthlyRpt[custMnthBean.size()];
            for (int i = 0; i < custMnthBean.size(); i++) {
                accmnthRpt[i] = custMnthBean.get(i);
            }

            return accmnthRpt;
        }
        return null;

        //return accmnthRpt;
    }

    public Object[] getBeanArray() {
        if (custBean.size() != 0) {
            accRpt = new CustomAccRpt[custBean.size()];
            for (int i = 0; i < custBean.size(); i++) {
                accRpt[i] = custBean.get(i);
            }

            return accRpt;
        }
        return null;
    }

    public Collection getBeanCollection() {
        return custBean;

    }

}
