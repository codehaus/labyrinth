package org.codehaus.labyrinth.components;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.tool.hbm2ddl.SchemaExport;

import org.apache.avalon.framework.activity.Disposable;
import org.apache.avalon.framework.configuration.Configurable;
import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.service.ServiceException;
import org.apache.avalon.framework.service.ServiceManager;
import org.apache.avalon.framework.service.Serviceable;
import org.codehaus.labyrinth.DatabaseException;
import org.codehaus.labyrinth.om.Block;
import org.codehaus.labyrinth.om.Project;
import org.codehaus.labyrinth.om.ProjectBlock;
import org.codehaus.labyrinth.om.ProjectBlockProperty;
import org.codehaus.labyrinth.om.peers.BlockPeer;

/**
 * @author  Ben Walding
 * @version $Id$
 */
public class DefaultPersistenceComponent implements PersistenceComponent, Serviceable, Configurable, Disposable
{
    /** log4j logger */
    private static final org.apache.log4j.Logger LOGGER =
        org.apache.log4j.Logger.getLogger(DefaultPersistenceComponent.class);

    private SessionFactory sessionFactory;
    private Session session;
    private net.sf.hibernate.cfg.Configuration hcfg;
    private Properties dbProperties;

    /* (non-Javadoc)
     * @see org.codehaus.labyrinth.components.PersistenceComponent#load(java.lang.Class, java.lang.Integer)
     */
    public Object load(Class clazz, Integer id) throws DatabaseException
    {
        try
        {
            return session.load(clazz, id);
        }
        catch (HibernateException e)
        {
            throw new DatabaseException(e);
        }
    }

    public void configureORLayer() throws Exception
    {
        Properties props = new Properties();
        props.put("labyrinth.db.driverclassname", "org.hsqldb.jdbcDriver");
        props.put("labyrinth.db.url", "jdbc:hsqldb:labyrinth-db");
        props.put("labyrinth.db.username", "sa");
        props.put("labyrinth.db.password", "");
        props.put("labyrinth.db.hibernate.dialect", "net.sf.hibernate.dialect.HSQLDialect");
        configureORLayer(props);
    }

    public void configureORLayer(Properties theDbProperties) throws Exception
    {
        this.dbProperties = theDbProperties;
        hcfg = new net.sf.hibernate.cfg.Configuration();
        hcfg.addProperties(getHibernateDBProperties());
        hcfg.addClass(Project.class);
        hcfg.addClass(Block.class);
        hcfg.addClass(ProjectBlock.class);
        hcfg.addClass(ProjectBlockProperty.class);

        sessionFactory = hcfg.buildSessionFactory();

        session = sessionFactory.openSession();
        generateSchema(false, false);

    }

    private Properties getHibernateDBProperties()
    {
        Properties props = new Properties();
        props.setProperty(
            "hibernate.connection.driver_class",
            dbProperties.getProperty("labyrinth.db.driverclassname"));
        props.setProperty("hibernate.connection.url", dbProperties.getProperty("labyrinth.db.url"));
        props.setProperty("hibernate.connection.username", dbProperties.getProperty("labyrinth.db.username"));
        props.setProperty("hibernate.connection.password", dbProperties.getProperty("labyrinth.db.password"));
        props.setProperty("hibernate.connection.pool_size", "10");
        props.setProperty("hibernate.statement_cache.size", "10");
        props.setProperty("hibernate.dialect", dbProperties.getProperty("labyrinth.db.hibernate.dialect"));

        if (dbProperties.getProperty("labyrinth.db.driverclassname").equals("org.hsqldb.jdbcDriver"))
        {
            props.setProperty("hibernate.connection.isolation", "" + Connection.TRANSACTION_READ_UNCOMMITTED);
        }

        if (dbProperties.getProperty("labyrinth.db.driverclassname").equals("org.postgresql.Driver"))
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

    public void generateSchema(boolean drop,boolean create) throws HibernateException
    {
        LOGGER.info("Loading schema");
        SchemaExport se = new SchemaExport(hcfg);
        se.setOutputFile("labyrinth.ddl");
        se.create(drop, create);
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
            session.connection().createStatement().execute("SHUTDOWN");
            session.close();
            session = null;
            LOGGER.warn("Hibernate session has been closed");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /* (non-Javadoc)
     * @see org.apache.avalon.framework.service.Serviceable#service(org.apache.avalon.framework.service.ServiceManager)
     */
    private ServiceManager serviceManager = null;
    public void service(ServiceManager sm) throws ServiceException
    {
        this.serviceManager = sm;
    }

    /* (non-Javadoc)
     * @see org.apache.avalon.framework.configuration.Configurable#configure(org.apache.avalon.framework.configuration.Configuration)
     */
    public void configure(Configuration arg0) throws ConfigurationException
    {
        try
        {
            LOGGER.info("Configuring the DefaultPersistenceComponent");
            Class c = Class.forName("org.hsqldb.jdbcDriver");
            Driver d = (Driver) c.newInstance();
            LOGGER.info("Driver: " + d);
            DriverManager.registerDriver(d);
            LOGGER.info("Found: " + c);
            configureORLayer();
        }
        catch (Exception e)
        {
            throw new ConfigurationException(e.getLocalizedMessage(), e);
        }
    }

    /* (non-Javadoc)
     * @see org.apache.avalon.framework.activity.Disposable#dispose()
     */
    public void dispose()
    {
        try
        {
            closeSession();
        }
        catch (DatabaseException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void checkBlocks() throws DatabaseException, ServiceException
    {
        BlockPeer bp = (BlockPeer) serviceManager.lookup(BlockPeer.ROLE);
        Block block = bp.getByRole(JiraBlockProvider.ROLE);
        if (block == null)
        {
            block = new Block();
            block.setRole(JiraBlockProvider.ROLE);
            bp.save(block);
        }
    }

    public void loadTest() throws DatabaseException
    {
        Project pDF = new Project();
        pDF.setProjectCode("dataforge");
        pDF.setUrl("http://dataforge.codehaus.org/");
        save(pDF);

        Project pMaven = new Project();
        pMaven.setProjectCode("maven");
        pMaven.setUrl("http://maven.apache.org/");
        save(pMaven);

        Block bJira = new Block();
        bJira.setRole(JiraBlockProvider.ROLE);
        save(bJira);

        ProjectBlock pbDFJIRA = new ProjectBlock();
        pbDFJIRA.setBlockId(bJira.getId());
        pbDFJIRA.setProjectId(pDF.getId());
        save(pbDFJIRA);

        ProjectBlock pbMAVENJIRA = new ProjectBlock();
        pbMAVENJIRA.setBlockId(bJira.getId());
        pbMAVENJIRA.setProjectId(pMaven.getId());
        save(pbMAVENJIRA);

        ProjectBlockProperty pbpDFJIRA = new ProjectBlockProperty();
        pbpDFJIRA.setProjectBlockId(pbMAVENJIRA.getId());
        pbpDFJIRA.setName("all.url");
        pbpDFJIRA.setValue(
            "http://jira.codehaus.org/secure/IssueNavigator.jspa?pid=10190&resolutionIds=-1&sorter/field=lastupdated&sorter/order=DESC&tempMax=500&view=rss&reset=true");
        save(pbpDFJIRA);
        
        ProjectBlockProperty pbpMAVENJIRA = new ProjectBlockProperty();
        pbpMAVENJIRA.setProjectBlockId(pbDFJIRA.getId());
        pbpMAVENJIRA.setName("all.url");
        pbpMAVENJIRA.setValue(
            "http://jira.codehaus.org/secure/IssueNavigator.jspa?pid=10030&resolutionIds=-1&sorter/field=lastupdated&sorter/order=DESC&tempMax=500&view=rss&reset=true");
        save(pbpMAVENJIRA);

    }

}
