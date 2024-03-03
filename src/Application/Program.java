package Application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDao sellerDao =DaoFactory.createdSellerDao();
		
		System.out.println("==== TEST 1: seller fyndById =====");
		Seller seller=sellerDao.FindById(3);
		System.out.println(seller);
		
		System.out.println("\n==== TEST 2: seller fyndByDepartment =====");
		Department department=new Department(2, null);
		List<Seller> list=sellerDao.FindyByDepartment(department);
		for(Seller obj:list) {
			System.out.println(obj);
		}
		
		System.out.println("\n==== TEST 3: seller fyndAll =====");
		list=sellerDao.FindyAll();
		for(Seller obj:list) {
			System.out.println(obj);
		}
		
		System.out.println("\n==== TEST 4: seller Insert =====");
		Seller newSeller=new Seller(null,"Gabriel", "gabriel@gmai.com",new Date(),5000.00, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New id = " + newSeller.getId());
	
		
		

	}

}
