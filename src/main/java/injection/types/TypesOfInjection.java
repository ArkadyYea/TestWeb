package injection.types;

import javax.annotation.Resource;
import javax.annotation.Resource.AuthenticationType;
import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceProperty;
import javax.persistence.SynchronizationType;
import javax.transaction.TransactionSynchronizationRegistry;

import abc.User;


@SuppressWarnings("unused")
public class TypesOfInjection {
	
//Essentially you inject your business logic
	//@Inject
	//ExampleEJB e;
	
//All resources declared on Application Server, usually available via JNDI: DSs, EMs, queues, topics, everything administered by AS
	//@Resource(authenticationType=AuthenticationType.APPLICATION, description="", lookup="",
	//		mappedName="", name="", shareable=true, type=TransactionSynchronizationRegistry.class)
	//TransactionSynchronizationRegistry tsr;
	
//It is like @Inject but with more attributes
	//@PersistenceContext(name="", unitName="", properties={@PersistenceProperty(name="abc", value="abc"), @PersistenceProperty(name="def", value="def")},
	//		synchronization=SynchronizationType.SYNCHRONIZED, type=PersistenceContextType.TRANSACTION)
	//EntityManager em;
	
//It is legacy, like @Inject but with more attributes
	//@EJB(beanInterface=User.class, beanName="", description="", lookup="", mappedName="", name="")
	//ExampleEJB e2;
	
	
//You can also produce resources with it and make them injectable everywhere.
//You can concentrate everything in 1 class and use @Inect for everything outside.
	//@Produces
	//@PersistenceContext
	//EntityManager em2;
	
	
//Use case	
	//@PersistenceUnit(unitName = "myPU")
	//EntityManagerFactory emf;
	//...
	//EntityManager entityManager = emf.createEntityManager();
}
