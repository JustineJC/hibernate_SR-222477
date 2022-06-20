package service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mysql.cj.exceptions.ExceptionFactory;

import entity.Teacher;

public class Test {

	public static void main(String[] arg) {
		System.out.println("connecting to DataBase");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		System.out.println("Created");
		Session session = null;
		org.hibernate.Transaction tx=null;
		List<Teacher> teacherQuery0 = null;
		List<Teacher> teacherQuery1 = null;
		List<Teacher> teacherQuery2 = null;
		//1.dummy teacher objects created.
//		Teacher t1 = new Teacher("Justine", "Falls", "jusitnefalls@okaxis.com");
//		Teacher t2 = new Teacher("Wednesday", "Crow", "wednesdaycrow@gmail.com");
//		Teacher t3 = new Teacher("Jack", "Flipper", "jackflippers@yahoo.com");
//		Teacher t4 = new Teacher("jimmy", "Saul", "jimmysaul@greatlearning.com");
//		
			
		
		try {
			session = factory.getCurrentSession();
			tx = session.beginTransaction();
			//2.Persist the data in teacher
//			session.save(t1);
//			session.save(t2);
//			session.save(t3);
//			session.save(t4);
			//3. Retrieve data from table teacher
			Teacher temp = session.get(Teacher.class, 1);
			System.out.println("Teacher"+temp);
			
			//4.createquery to display/query object class
			teacherQuery0 = session.createQuery("from Teacher t where t.f_name='wednesday' ").list();
			teacherQuery1 = session.createQuery("from Teacher where email like %greatlearning.com ").list();
			teacherQuery2 = session.createQuery("from Teacher where max(id)").list();
			for (Teacher t : teacherQuery0) {
				System.out.println(t);
			}
			
			//5.Update Java object w/ hibernate
			Teacher teacherUpdate = session.get(Teacher.class, 3);
			teacherUpdate.setF_name("Amul");
			teacherUpdate.setL_name("Buter");
			teacherUpdate.setEmail("amulbutter@gmail.com");
			session.update(teacherUpdate);
			 //5.2 using createQuery and excecuteUpdate
					session.createQuery(" update Teacher set email='europe@gmail.com' where email like %yahoo.com ").executeUpdate(); 
			//6.1 Delete query for teacher table
//			Teacher teacherDelete = session.get(Teacher.class, 1);
//			session.delete(teacherDelete);
//			
			//6.2 Delete query for multiple records
			//session.createQuery("Delete from Teacher where id= 2 ").executeUpdate();
					
					tx.commit();
		}
		catch (Exception e){
			System.out.println("Error while posting");
			e.printStackTrace();
			tx.rollback();
		}
		finally {
			session.close();
			factory.close();		}
}
}
