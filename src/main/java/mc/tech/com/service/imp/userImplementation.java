package mc.tech.com.service.imp;

import mc.tech.com.domain.User;
import mc.tech.com.service.IService;

public interface userImplementation extends IService<User,Long > {
    User getUserByEmail(String email);

}
