package onmen.springboot.dao;

import onmen.springboot.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/****
 * 用户详情界面
 */
@Transactional
@Component
public interface UserInfoMapper extends JpaRepository<UserInfo,Long> {

    UserInfo findByUid(int uid);

    @Modifying
    @Query("update UserInfo set ucommentnum = ?1 where uid=?2")
    void updateUserCommentNum(int ucommentnum,int uid);

    @Query("select ucommentnum from UserInfo where uid=?1")
    Integer getUserCommentNum(int uid);

    @Modifying
    @Query("update UserInfo set uupnum  = ?2 where uid = ?1")
    void updateUpNum(int uid,int upnum);


    @Query("select uupnum from UserInfo where uid=?1")
    Integer getUserUpNum(int uid);
}
