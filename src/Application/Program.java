package Application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj=new Department(1, "Cars");
		
		Seller seller=new Seller(10, "Gabriel", "gabriel@gmail.com", new Date(), 5000.00, obj);
		
		SellerDao sd=DaoFactory.createdSellerDao();
		
		System.out.println(seller);
		

	}

}
