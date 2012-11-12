package ru.retbansk.jdbc.ivanTest;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

/**
 * Application Lifecycle Listener implementation class Initialiser
 *
 */
@WebListener
public class Initialiser implements ServletContextListener {
	
	public static final String poolNameJndi = "ivanTest";
	public static final String prefix = "java:/comp/env";
	public static final String DATA_SOURCE = "dataSource";
    /**
     * Default constructor. 
     */
    public Initialiser() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {
        try {
        	Context context = new InitialContext();
        	Context envContext = (Context)context.lookup(prefix);
        	DataSource dataSource = (DataSource)envContext.lookup(poolNameJndi);
        	// next two lines do almost the same
        	sce.getServletContext().setAttribute("dataSource",dataSource);
        	BaseServlet.setDataSource(dataSource);
        	 }
        catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub
    }
	
}
