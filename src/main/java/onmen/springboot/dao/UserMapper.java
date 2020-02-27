package onmen.springboot.dao;

import onmen.springboot.domain.User;
import onmen.springboot.domain.UserMin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component
public interface UserMapper extends JpaRepository<User,Long>{
    //根据用户名和密码查询用户
    User findUserByUnameAndUpassword(String uname,String password);
    //根据用户id查询用户
    User findUserByUid(int uid);
    //判断是否存在这个用户名
    boolean existsAllByUname(String username);

    //根据用户id查询用户姓名和用户头像
    @Query("select new onmen.springboot.domain.UserMin(uname,img) from User where uid=?1")
    UserMin findUserNameImgs(int uid);

    //查询用户数量
    @Query("select count(u.uid) from User u")
    int getUserCount();

    //根据分页参查询用户
    @Query(nativeQuery = true,value="select * from user limit ?1,?2")
    List<Object[]> getAllUserByPage(int current, int size);

   //保存用户
    User save(User user);

}
