package conexao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	
	private static StandardServiceRegistry registry;
	private static  SessionFactory sessionFactory; // = buildSessionFactory();
	
	public static SessionFactory getSessionFactory() {
		
		if(sessionFactory== null)
		{
			try {
				registry = new StandardServiceRegistryBuilder().configure().build();
				
				MetadataSources sources = new MetadataSources(registry);
				
				Metadata metadata = sources.getMetadataBuilder().build();
				
				sessionFactory = metadata.getSessionFactoryBuilder().build();
				
			}catch(Exception e) {
				e.printStackTrace();
				if(registry != null)
				{
					StandardServiceRegistryBuilder.destroy(registry);
				}
				
			}
			
		} return sessionFactory;
	} 
	
	public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
	
	/*private static SessionFactory buildSessionFactory() {
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			StandardServiceRegistryBuilder registradorServico = new StandardServiceRegistryBuilder();
			registradorServico.applySettings(cfg.getProperties());
			StandardServiceRegistry servico = registradorServico.build();
			
			return cfg.buildSessionFactory(servico);
			
		}
		catch(Throwable e) {
			System.out.println("Criancao inicial do objeto SessionFactory falhou. Erro: " + e);
			throw new ExceptionInInitializerError(e);
		}
		
	}
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}*/

}
