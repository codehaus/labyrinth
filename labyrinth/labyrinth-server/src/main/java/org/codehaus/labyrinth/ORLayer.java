package org.codehaus.labyrinth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Properties;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.tool.hbm2ddl.SchemaExport;

import org.codehaus.labyrinth.om.Project;

/**
 * @author <a href="bwalding@jakarta.org">Ben Walding</a>
 * @version $Id$
 */
public class ORLayer
{
    /** log4j logger */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ORLayer.class);

    private SessionFactory sessionFactory;
    private Session session;
    private net.sf.hibernate.cfg.Configuration hcfg;
    private Properties dbProperties;

    public void configureORLayer() throws Exception
    {
        Properties props = new Properties();
        props.put("montage.db.driverclassname", "org.hsqldb.jdbcDriver;");
        props.put("montage.db.url", "jdbc:hsqldb:montage-db");
        props.put("montage.db.username", "sa");
        props.put("montage.db.password", "");
        props.put("montage.db.hibernate.dialect", "net.sf.hibernate.dialect.HSQLDialect");
        configureORLayer(props);
    }

    public void configureORLayer(Properties theDbProperties) throws Exception
    {
        this.dbProperties = theDbProperties;
        hcfg = new net.sf.hibernate.cfg.Configuration();
        hcfg.addProperties(getHibernateDBProperties());
        hcfg.addClass(Project.class);

        sessionFactory = hcfg.buildSessionFactory();

        session = sessionFactory.openSession();

        Connection conn = session.connection();
        String types[] = { "TABLE" };
        ResultSet rs = conn.getMetaData().getTables(null, null, "MFolderRole", types);
        if (!rs.next())
        {
            generateSchema();
        }
        else
        {
            LOGGER.info("MRole table found. Not loading schema");
            do
            {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                {
                    LOGGER.info("rs.getObject(" + i + ") = " + rs.getObject(i));
                }
            }
            while (rs.next());
        }

    }

    public Properties getHibernateDBProperties()
    {
        Properties props = new Properties();
        props.setProperty("hibernate.connection.driver_class", dbProperties.getProperty("montage.db.driverclassname"));
        props.setProperty("hibernate.connection.url", dbProperties.getProperty("montage.db.url"));
        props.setProperty("hibernate.connection.username", dbProperties.getProperty("montage.db.username"));
        props.setProperty("hibernate.connection.password", dbProperties.getProperty("montage.db.password"));
        props.setProperty("hibernate.connection.pool_size", "10");
        props.setProperty("hibernate.statement_cache.size", "10");
        props.setProperty("hibernate.dialect", dbProperties.getProperty("montage.db.hibernate.dialect"));

        if (dbProperties.getProperty("montage.db.driverclassname").equals("org.hsqldb.jdbcDriver"))
        {
            props.setProperty("hibernate.connection.isolation", "" + Connection.TRANSACTION_READ_UNCOMMITTED);
        }

        if (dbProperties.getProperty("montage.db.driverclassname").equals("org.postgresql.Driver"))
        {
            props.setProperty("hibernate.connection.isolation", "" + Connection.TRANSACTION_READ_COMMITTED);
        }

        LOGGER.info(props);
        return props;
    }

    public Session getSession()
    {
        return session;
    }

    public void generateSchema() throws HibernateException
    {
        LOGGER.info("Loading schema");
        SchemaExport se = new SchemaExport(hcfg);
        se.create(false, true);
        session.flush();

    }

    public void loadCoreData() throws Exception
    {
        //LOGGER.info("Loading core data");
        //CoreData cd = new CoreData();
        //cd.load(session);
        //session.flush();
    }

    public void save(Object o) throws DatabaseException
    {
        save(session, o);
    }

    public static void save(Session session, Object o) throws DatabaseException
    {
        try
        {
            Transaction t = session.beginTransaction();
            session.save(o);
            t.commit();

            session.flush();
        }
        catch (HibernateException e)
        {
            throw new DatabaseException(e);
        }
    }

    public Object getById(Class clazz, Integer id) throws DatabaseException
    {
        try
        {
            String hsql = "from " + clazz.getName() + " as A where A.id = ?";
            return session.find(hsql, id, Hibernate.INTEGER);
        }
        catch (HibernateException e)
        {
            throw new DatabaseException(e);
        }

    }

    /**
     * 
     */
    public void closeSession() throws DatabaseException
    {
        try
        {
            session.flush();
            session.connection().commit();
            session.close();
            session = null;
            LOGGER.warn("Hibernate session has been closed");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
