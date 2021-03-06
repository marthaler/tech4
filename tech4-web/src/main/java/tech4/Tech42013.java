package tech4;

import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.SessionScope;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

/**
 *  Backing Bean fuer Demo App
 */
@Component
@Scope("session")
public class Tech42013 implements Serializable{

    private boolean admin = false;

    static int counter = 0;

    static int sessionCounter = 0;

    private boolean weniger = true;

    private String username = "";

    private String pruefziffer = "  softwareentwicklungswerkzeuge   ";

    private static Map<String,Integer> users = new HashMap<String,Integer>();

    private static Set<String> agents = new HashSet<String>();

    public static LinkedList<Statement> kommentare = new LinkedList<Statement>();

    public static LinkedList<Statement> kommentareAll = new LinkedList<Statement>();

    private String kommentar = "";

    public Tech42013(){
        sessionCounter = sessionCounter + 1;
    }

    public int getSessionCounter(){
        return sessionCounter;
    }

    public void toggleWeniger(){
        weniger = !weniger;
    }

    public synchronized int getCounter() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String names = request.getHeader("user-agent");
        String sh = "andere";

        agents.add(names);

        if(names.toLowerCase().contains("Android")){
            sh = "Android";
        }
        else
        if(names.contains("iPhone")){
            sh = "IPhone";
        }
        else

        if(names.contains("iPad")){
            sh = "IPad";
        }
          else
        if(names.contains("Mac OS X")){

            if(names.contains("Chrome")){
                sh = "Mac OS X: Chrome";
            } else
            if(names.contains("Safari")){
                sh = "Mac OS X: Safari";
            } else
            if(names.contains("Firefox")){
                sh = "Mac OS X: Firefox";
            }  else{
                sh = "Mac OS X: Unbekannt";
            }
        }         else  if(names.contains("Windows")){

            if(names.contains("Chrome")){
                sh = "Windows: Chrome";
            } else
            if(names.contains("Safari")){
                sh = "Windows: Safari";
            } else
            if(names.contains("MSIE 8.0")){
                sh = "Windows: IE 8.0";
            } else
            if(names.contains("MSIE")){
                sh = "Windows: IE";
            } else
            if(names.contains("Firefox")){
                sh = "Windows: Firefox";
            }  else{
                sh = "Windows: Unbekannt";
            }
        }

        Integer inte = users.get(sh);

        if(inte == null){
            users.put(sh,Integer.valueOf(1));
        } else{
            inte = inte +1;
            users.put(sh,inte);
        }

        // Mac OS + Safari
        // Mac OS + Chrome
      Tech42013.counter = Tech42013.counter + 1;
        return Tech42013.counter;
    }

    public void updateList(String komm) {



        if(komm != null && komm.contains("trick77reset")){
            this.admin = false;
            kommentare.clear();
            kommentareAll.clear();
            users.clear();
            agents.clear();
            counter = 0;
            sessionCounter = 0;
            weniger= true;
            kommentar = null;
            return;
        }

        if(komm != null && komm.contains("trick77")){
            this.admin = true;
            kommentar = null;
            return;
        }

        if (komm != null && !komm.equals("")) {
            Statement st = new Statement();
            st.setText(komm);
            st.setUser(this.getUsername());

            kommentare.add(0,st);
            kommentareAll.add(0,st);
            if (kommentare.size() > 10) {
                kommentare.removeLast();
            }
            kommentar = null;
        }
    }

    public synchronized void save() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String txtProperty = request.getParameter("form:text");
        String name = request.getParameter("form:name");
        if(name != null && name.length()> 0) {this.username = name;}
        updateList(txtProperty);
    }

    public List<Statement> getKommentare() {
        return filterList();
    }

    private  List<Statement> filterList(){
        List<Statement> in;
        if(this.weniger){
              in =  kommentare;
        }     else{
            in =  kommentareAll;
        }

        List<Statement> out = new ArrayList<Statement>();
        for(Statement st : in){
               if(st.getUser().equals(this.getUsername())){
                     out.add(st);
               }  else{
                       if(this.isAdmin()){
                           out.add(st);
                       }
            }
        }
        return out;
    }

    public synchronized String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public String getPruefziffer() {
        return pruefziffer;
    }

    public PieChartModel getPieModel() {
        PieChartModel pieModel = new PieChartModel();

        for(String name : this.users.keySet()){
                pieModel.set(name, users.get(name));
        }
          return pieModel;
    }

    public List<String> getAgents(){
        List<String> list = new ArrayList<String>();
        for(String agent : agents){
            list.add(agent);
        }
        return list;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isWeniger() {
        return weniger;
    }

    public void setWeniger(boolean weniger) {
        this.weniger = weniger;
    }
}