package factory;
import dao.IUserDao;
import dao.proxy.UserDaoProxy;
public class DaoFactory {
	public static IUserDao getIUserInstance() throws Exception{
		return new UserDaoProxy();
	}
}
