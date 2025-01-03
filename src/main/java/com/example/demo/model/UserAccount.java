package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;

public class UserAccount {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_account.id
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_account.user_name
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_account.account
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    private Integer account;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_account.money
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    private BigDecimal money;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_account.create_time
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_account.update_time
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_account.id
     *
     * @return the value of user_account.id
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_account.id
     *
     * @param id the value for user_account.id
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_account.user_name
     *
     * @return the value of user_account.user_name
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_account.user_name
     *
     * @param userName the value for user_account.user_name
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_account.account
     *
     * @return the value of user_account.account
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    public Integer getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_account.account
     *
     * @param account the value for user_account.account
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    public void setAccount(Integer account) {
        this.account = account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_account.money
     *
     * @return the value of user_account.money
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_account.money
     *
     * @param money the value for user_account.money
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_account.create_time
     *
     * @return the value of user_account.create_time
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_account.create_time
     *
     * @param createTime the value for user_account.create_time
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_account.update_time
     *
     * @return the value of user_account.update_time
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_account.update_time
     *
     * @param updateTime the value for user_account.update_time
     *
     * @mbg.generated Tue Dec 10 17:31:52 CST 2024
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}