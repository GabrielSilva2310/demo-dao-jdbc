package Application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department obj=new Department(1, "Cars");
		
		
		
		SellerDao sellerDao =DaoFactory.createdSellerDao();
		Seller seller=sellerDao.FindById(3);
		
		System.out.println(seller);
		
		
		

	}

}
