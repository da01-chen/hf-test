package com.example.demo.mapper;

import com.example.demo.model.UserAccount;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    int insert(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    int insertSelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    UserAccount selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    int updateByPrimaryKeySelective(UserAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_account
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    int updateByPrimaryKey(UserAccount record);

    UserAccount selectByAccount(Long account);

    int transferOut(@Param("account") Integer account, @Param("money")BigDecimal money);
    int transferIn(@Param("account") Integer account, @Param("money")BigDecimal money);

    @Delete("delete from user_account where account= #{account}")
    int deleteByAccount(Long account);

    @Delete("delete from user_account where account>= #{beginIndex} and account<= #{endIndex}")
    int deleteByAccountRange(Integer beginIndex, Integer endIndex);

    int insertBatch(List<UserAccount> records);

}