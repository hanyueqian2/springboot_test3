package com.han.test.springboot_test3.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Table(name = "oa_employee")
public class Employee {

    private List<Integer> departmentSelected;

    private String companyOrganizationName;
    private String departmentOrganizationName;
    private String createdByName;
    private String lastModifiedByName;
    private String employeeTypeName;
    private String postName;

    /**
     * 管辖部门
     */
    private List<Integer> manageOrganizationIdList;

    private List<Integer> roleIdList;

    public String getCompanyOrganizationName() {
        return companyOrganizationName;
    }

    public void setCompanyOrganizationName(String companyOrganizationName) {
        this.companyOrganizationName = companyOrganizationName;
    }

    public String getDepartmentOrganizationName() {
        return departmentOrganizationName;
    }

    public void setDepartmentOrganizationName(String departmentOrganizationName) {
        this.departmentOrganizationName = departmentOrganizationName;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getLastModifiedByName() {
        return lastModifiedByName;
    }

    public void setLastModifiedByName(String lastModifiedByName) {
        this.lastModifiedByName = lastModifiedByName;
    }

    @Id
    private Integer id;

    /**
     * 登录名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 对应用户表id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 员工姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 传真
     */
    private String fax;

    /**
     * 公司外线电话
     */
    @Column(name = "outside_telephone")
    private String outsideTelephone;

    /**
     * 公司内线电话
     */
    @Column(name = "inside_telephone")
    private String insideTelephone;

    /**
     * 工号
     */
    @Column(name = "employee_number")
    private String employeeNumber;

    /**
     * 人员所属公司
     */
    @Column(name = "company_organization")
    private Integer companyOrganization;

    /**
     * 人员所属部门
     */
    @Column(name = "department_organization")
    private Integer departmentOrganization;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别 0 男 1 女
     */
    private String sex;

    /**
     * 人员状态 1试用2正式3离职
     */
    @Column(name = "employee_status")
    private String employeeStatus;

    /**
     * 人员分类 管理-管理费用 / 行政-管理费用 / 技术-管理费用 / 操作-销售成本 / 销售-销售费用 / 财务-管理费用 / 其他-销售成本
     */
    @Column(name = "employee_type")
    private String employeeType;

    /**
     * 身份证号
     */
    @Column(name = "identity_card_number")
    private String identityCardNumber;

    /**
     * 出生年月
     */
    @Column(name = "birth_date")
    private Date birthDate;

    /**
     * 民族
     */
    private String nation;

    /**
     * 地址
     */
    private String address;

    /**
     * 发证机构
     */
    @Column(name = "certifying_authority")
    private String certifyingAuthority;

    /**
     * 身份证信息_有效期起始日期
     */
    @Column(name = "card_valid_start_date")
    private Date cardValidStartDate;

    /**
     * 身份证信息_有效期截止日期
     */
    @Column(name = "card_valid_end_date")
    private Date cardValidEndDate;

    /**
     * 家庭住址
     */
    @Column(name = "home_address")
    private String homeAddress;

    /**
     * 家庭电话
     */
    private String telephone;

    /**
     * 紧急联系人
     */
    @Column(name = "emergency_contact")
    private String emergencyContact;

    /**
     * 紧急联系电话
     */
    @Column(name = "emergency_mobile")
    private String emergencyMobile;

    /**
     * 岗位id
     */
    @Column(name = "post_id")
    private Integer postId;

    /**
     * 职位级别行政13级 / 行政20级
     */
    @Column(name = "job_level")
    private Integer jobLevel;

    /**
     * 政治面貌
     */
    @Column(name = "political_type")
    private String politicalType;

    /**
     * 学历
     */
    private String education;

    /**
     * 职称
     */
    @Column(name = "professional_title")
    private String professionalTitle;

    /**
     * 毕业院校
     */
    @Column(name = "graduate_school")
    private String graduateSchool;

    /**
     * 专业
     */
    private String major;

    /**
     * 婚姻状况 1=已婚
     */
    @Column(name = "marital_status")
    private String maritalStatus;

    /**
     * 参加工作时间
     */
    @Column(name = "work_date")
    private Date workDate;

    /**
     * 入本公司时间
     */
    @Column(name = "join_date")
    private Date joinDate;

    /**
     * 转正时间
     */
    @Column(name = "formal_date")
    private Date formalDate;

    /**
     * 离职时间
     */
    @Column(name = "quit_date")
    private Date quitDate;

    /**
     * 劳动性质 合同、试用、临时
     */
    @Column(name = "contract_type")
    private String contractType;

    /**
     * 合同起始时间
     */
    @Column(name = "contract_start_date")
    private Date contractStartDate;

    /**
     * 合同终止时间
     */
    @Column(name = "contract_end_date")
    private Date contractEndDate;

    /**
     * 本次签订合同时间
     */
    @Column(name = "contract_sign_date")
    private Date contractSignDate;

    /**
     * 1=无固定期限合同
     */
    @Column(name = "unfixed_period_contract")
    private String unfixedPeriodContract;

    /**
     * 档案存放地点：可选项 代理、公司、自存、档案中心
     */
    @Column(name = "archives_address")
    private String archivesAddress;

    /**
     * 户籍性质 1=城镇 2=农村
     */
    @Column(name = "household_register_type")
    private String householdRegisterType;

    /**
     * 户籍所在地，是否为公司集体户 1=是
     */
    @Column(name = "collective_household")
    private String collectiveHousehold;

    /**
     * 统筹个人账号
     */
    @Column(name = "pension_number")
    private String pensionNumber;

    /**
     * 统筹所属公司
     */
    @Column(name = "pension_company")
    private String pensionCompany;

    /**
     * 统筹费率号
     */
    @Column(name = "pension_rate")
    private String pensionRate;

    /**
     * 统筹缴费基数
     */
    @Column(name = "pension_base")
    private BigDecimal pensionBase;

    /**
     * 统筹是否扣费 0表示不缴费
     */
    @Column(name = "pension_deductions")
    private Integer pensionDeductions;

    /**
     * 公积金个人账号
     */
    @Column(name = "fund_number")
    private String fundNumber;

    /**
     * 公积金所属公司
     */
    @Column(name = "fund_company")
    private String fundCompany;

    /**
     * 公积金费率号
     */
    @Column(name = "fund_rate")
    private String fundRate;

    /**
     * 公积金缴费基数
     */
    @Column(name = "fund_base")
    private BigDecimal fundBase;

    /**
     * 公积金是否扣费 0表示不缴费
     */
    @Column(name = "fund_deductions")
    private Integer fundDeductions;

    /**
     * 员工门禁卡号
     */
    @Column(name = "entrance_number")
    private String entranceNumber;

    /**
     * 考勤标志 =1表示需要参加考勤
     */
    @Column(name = "check_work")
    private Integer checkWork;

    /**
     * 微信号
     */
    @Column(name = "wechat_number")
    private String wechatNumber;

    /**
     * 微信昵称
     */
    @Column(name = "wechat_name")
    private String wechatName;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 冻结标志 1=冻结
     */
    @Column(name = "freeze_flag")
    private Integer freezeFlag;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新人
     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**
     * 更新时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * 逻辑删除标记
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 身份证信息_照片
     */
    @Column(name = "card_image_path")
    private String cardImagePath;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 考勤机员工工号
     */
    @Column(name="attendance_number")
    private String attendanceNumber;

    /**
     * 续签次数
     */
    @Column(name="renew_num")
    private Integer renewNum;


    /**
     *
     *设定员工年假天数
     */
    @Column(name="year_vacation_num")
    private Integer yearVacationNum;

    /**
     * 人员核算代码
     */
    @Column(name = "person_account_code")
    private String personAccountCode;

    public String getPersonAccountCode() {
        return personAccountCode;
    }

    public void setPersonAccountCode(String personAccountCode) {
        this.personAccountCode = personAccountCode;
    }


    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取登录名
     *
     * @return user_name - 登录名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置登录名
     *
     * @param userName 登录名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取对应用户表id
     *
     * @return user_id - 对应用户表id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置对应用户表id
     *
     * @param userId 对应用户表id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取员工姓名
     *
     * @return real_name - 员工姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置员工姓名
     *
     * @param realName 员工姓名
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取传真
     *
     * @return fax - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    /**
     * 获取公司外线电话
     *
     * @return outside_telephone - 公司外线电话
     */
    public String getOutsideTelephone() {
        return outsideTelephone;
    }

    /**
     * 设置公司外线电话
     *
     * @param outsideTelephone 公司外线电话
     */
    public void setOutsideTelephone(String outsideTelephone) {
        this.outsideTelephone = outsideTelephone == null ? null : outsideTelephone.trim();
    }

    /**
     * 获取公司内线电话
     *
     * @return inside_telephone - 公司内线电话
     */
    public String getInsideTelephone() {
        return insideTelephone;
    }

    /**
     * 设置公司内线电话
     *
     * @param insideTelephone 公司内线电话
     */
    public void setInsideTelephone(String insideTelephone) {
        this.insideTelephone = insideTelephone == null ? null : insideTelephone.trim();
    }

    /**
     * 获取工号
     *
     * @return employee_number - 工号
     */
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    /**
     * 设置工号
     *
     * @param employeeNumber 工号
     */
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber == null ? null : employeeNumber.trim();
    }

    /**
     * 获取人员所属公司
     *
     * @return company_organization - 人员所属公司
     */
    public Integer getCompanyOrganization() {
        return companyOrganization;
    }

    /**
     * 设置人员所属公司
     *
     * @param companyOrganization 人员所属公司
     */
    public void setCompanyOrganization(Integer companyOrganization) {
        this.companyOrganization = companyOrganization;
    }

    /**
     * 获取人员所属部门
     *
     * @return department_organization - 人员所属部门
     */
    public Integer getDepartmentOrganization() {
        return departmentOrganization;
    }

    /**
     * 设置人员所属部门
     *
     * @param departmentOrganization 人员所属部门
     */
    public void setDepartmentOrganization(Integer departmentOrganization) {
        this.departmentOrganization = departmentOrganization;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取性别 0 男 1 女
     *
     * @return sex - 性别 0 男 1 女
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别 0 男 1 女
     *
     * @param sex 性别 0 男 1 女
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 获取人员状态 1试用2正式3离职
     *
     * @return employee_status - 人员状态 1试用2正式3离职
     */
    public String getEmployeeStatus() {
        return employeeStatus;
    }

    /**
     * 设置人员状态 1试用2正式3离职
     *
     * @param employeeStatus 人员状态 1试用2正式3离职
     */
    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus == null ? null : employeeStatus.trim();
    }

    /**
     * 获取人员分类 管理-管理费用 / 行政-管理费用 / 技术-管理费用 / 操作-销售成本 / 销售-销售费用 / 财务-管理费用 / 其他-销售成本
     *
     * @return employee_type - 人员分类 管理-管理费用 / 行政-管理费用 / 技术-管理费用 / 操作-销售成本 / 销售-销售费用 / 财务-管理费用 / 其他-销售成本
     */
    public String getEmployeeType() {
        return employeeType;
    }

    /**
     * 设置人员分类 管理-管理费用 / 行政-管理费用 / 技术-管理费用 / 操作-销售成本 / 销售-销售费用 / 财务-管理费用 / 其他-销售成本
     *
     * @param employeeType 人员分类 管理-管理费用 / 行政-管理费用 / 技术-管理费用 / 操作-销售成本 / 销售-销售费用 / 财务-管理费用 / 其他-销售成本
     */
    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType == null ? null : employeeType.trim();
    }

    /**
     * 获取身份证号
     *
     * @return identity_card_number - 身份证号
     */
    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    /**
     * 设置身份证号
     *
     * @param identityCardNumber 身份证号
     */
    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber == null ? null : identityCardNumber.trim();
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 获取民族
     *
     * @return nation - 民族
     */
    public String getNation() {
        return nation;
    }

    /**
     * 设置民族
     *
     * @param nation 民族
     */
    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取发证机构
     *
     * @return certifying_authority - 发证机构
     */
    public String getCertifyingAuthority() {
        return certifyingAuthority;
    }

    /**
     * 设置发证机构
     *
     * @param certifyingAuthority 发证机构
     */
    public void setCertifyingAuthority(String certifyingAuthority) {
        this.certifyingAuthority = certifyingAuthority == null ? null : certifyingAuthority.trim();
    }

    public Date getCardValidStartDate() {
        return cardValidStartDate;
    }

    public void setCardValidStartDate(Date cardValidStartDate) {
        this.cardValidStartDate = cardValidStartDate;
    }

    public Date getCardValidEndDate() {
        return cardValidEndDate;
    }

    public void setCardValidEndDate(Date cardValidEndDate) {
        this.cardValidEndDate = cardValidEndDate;
    }

    /**
     * 获取家庭住址
     *
     * @return home_address - 家庭住址
     */
    public String getHomeAddress() {
        return homeAddress;
    }

    /**
     * 设置家庭住址
     *
     * @param homeAddress 家庭住址
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress == null ? null : homeAddress.trim();
    }

    /**
     * 获取家庭电话
     *
     * @return telephone - 家庭电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置家庭电话
     *
     * @param telephone 家庭电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 获取紧急联系人
     *
     * @return emergency_contact - 紧急联系人
     */
    public String getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * 设置紧急联系人
     *
     * @param emergencyContact 紧急联系人
     */
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact == null ? null : emergencyContact.trim();
    }

    /**
     * 获取紧急联系电话
     *
     * @return emergency_mobile - 紧急联系电话
     */
    public String getEmergencyMobile() {
        return emergencyMobile;
    }

    /**
     * 设置紧急联系电话
     *
     * @param emergencyMobile 紧急联系电话
     */
    public void setEmergencyMobile(String emergencyMobile) {
        this.emergencyMobile = emergencyMobile == null ? null : emergencyMobile.trim();
    }

    /**
     * 获取岗位id
     *
     * @return post_id - 岗位id
     */
    public Integer getPostId() {
        return postId;
    }

    /**
     * 设置岗位id
     *
     * @param postId 岗位id
     */
    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    /**
     * 获取职位级别行政13级 / 行政20级
     *
     * @return job_level - 职位级别行政13级 / 行政20级
     */
    public Integer getJobLevel() {
        return jobLevel;
    }

    /**
     * 设置职位级别行政13级 / 行政20级
     *
     * @param jobLevel 职位级别行政13级 / 行政20级
     */
    public void setJobLevel(Integer jobLevel) {
        this.jobLevel = jobLevel;
    }

    /**
     * 获取政治面貌
     *
     * @return political_type - 政治面貌
     */
    public String getPoliticalType() {
        return politicalType;
    }

    /**
     * 设置政治面貌
     *
     * @param politicalType 政治面貌
     */
    public void setPoliticalType(String politicalType) {
        this.politicalType = politicalType == null ? null : politicalType.trim();
    }

    /**
     * 获取学历
     *
     * @return education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * 获取职称
     *
     * @return professional_title - 职称
     */
    public String getProfessionalTitle() {
        return professionalTitle;
    }

    /**
     * 设置职称
     *
     * @param professionalTitle 职称
     */
    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle == null ? null : professionalTitle.trim();
    }

    /**
     * 获取毕业院校
     *
     * @return graduate_school - 毕业院校
     */
    public String getGraduateSchool() {
        return graduateSchool;
    }

    /**
     * 设置毕业院校
     *
     * @param graduateSchool 毕业院校
     */
    public void setGraduateSchool(String graduateSchool) {
        this.graduateSchool = graduateSchool == null ? null : graduateSchool.trim();
    }

    /**
     * 获取专业
     *
     * @return major - 专业
     */
    public String getMajor() {
        return major;
    }

    /**
     * 设置专业
     *
     * @param major 专业
     */
    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    /**
     * 获取婚姻状况 1=已婚
     *
     * @return marital_status - 婚姻状况 1=已婚
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * 设置婚姻状况 1=已婚
     *
     * @param maritalStatus 婚姻状况 1=已婚
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus == null ? null : maritalStatus.trim();
    }

    /**
     * 获取参加工作时间
     *
     * @return work_date - 参加工作时间
     */
    public Date getWorkDate() {
        return workDate;
    }

    /**
     * 设置参加工作时间
     *
     * @param workDate 参加工作时间
     */
    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    /**
     * 获取入本公司时间
     *
     * @return join_date - 入本公司时间
     */
    public Date getJoinDate() {
        return joinDate;
    }

    /**
     * 设置入本公司时间
     *
     * @param joinDate 入本公司时间
     */
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * 获取转正时间
     *
     * @return formal_date - 转正时间
     */
    public Date getFormalDate() {
        return formalDate;
    }

    /**
     * 设置转正时间
     *
     * @param formalDate 转正时间
     */
    public void setFormalDate(Date formalDate) {
        this.formalDate = formalDate;
    }

    /**
     * 获取离职时间
     *
     * @return quit_date - 离职时间
     */
    public Date getQuitDate() {
        return quitDate;
    }

    /**
     * 设置离职时间
     *
     * @param quitDate 离职时间
     */
    public void setQuitDate(Date quitDate) {
        this.quitDate = quitDate;
    }

    /**
     * 获取劳动性质 合同、试用、临时
     *
     * @return contract_type - 劳动性质 合同、试用、临时
     */
    public String getContractType() {
        return contractType;
    }

    /**
     * 设置劳动性质 合同、试用、临时
     *
     * @param contractType 劳动性质 合同、试用、临时
     */
    public void setContractType(String contractType) {
        this.contractType = contractType == null ? null : contractType.trim();
    }

    /**
     * 获取合同起始时间
     *
     * @return contract_start_date - 合同起始时间
     */
    public Date getContractStartDate() {
        return contractStartDate;
    }

    /**
     * 设置合同起始时间
     *
     * @param contractStartDate 合同起始时间
     */
    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    /**
     * 获取合同终止时间
     *
     * @return contract_end_date - 合同终止时间
     */
    public Date getContractEndDate() {
        return contractEndDate;
    }

    /**
     * 设置合同终止时间
     *
     * @param contractEndDate 合同终止时间
     */
    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    /**
     * 获取本次签订合同时间
     *
     * @return contract_sign_date - 本次签订合同时间
     */
    public Date getContractSignDate() {
        return contractSignDate;
    }

    /**
     * 设置本次签订合同时间
     *
     * @param contractSignDate 本次签订合同时间
     */
    public void setContractSignDate(Date contractSignDate) {
        this.contractSignDate = contractSignDate;
    }

    /**
     * 获取1=无固定期限合同
     *
     * @return unfixed_period_contract - 1=无固定期限合同
     */
    public String getUnfixedPeriodContract() {
        return unfixedPeriodContract;
    }

    /**
     * 设置1=无固定期限合同
     *
     * @param unfixedPeriodContract 1=无固定期限合同
     */
    public void setUnfixedPeriodContract(String unfixedPeriodContract) {
        this.unfixedPeriodContract = unfixedPeriodContract == null ? null : unfixedPeriodContract.trim();
    }

    /**
     * 获取档案存放地点：可选项 代理、公司、自存、档案中心
     *
     * @return archives_address - 档案存放地点：可选项 代理、公司、自存、档案中心
     */
    public String getArchivesAddress() {
        return archivesAddress;
    }

    /**
     * 设置档案存放地点：可选项 代理、公司、自存、档案中心
     *
     * @param archivesAddress 档案存放地点：可选项 代理、公司、自存、档案中心
     */
    public void setArchivesAddress(String archivesAddress) {
        this.archivesAddress = archivesAddress == null ? null : archivesAddress.trim();
    }

    /**
     * 获取户籍性质 1=城镇 2=农村
     *
     * @return household_register_type - 户籍性质 1=城镇 2=农村
     */
    public String getHouseholdRegisterType() {
        return householdRegisterType;
    }

    /**
     * 设置户籍性质 1=城镇 2=农村
     *
     * @param householdRegisterType 户籍性质 1=城镇 2=农村
     */
    public void setHouseholdRegisterType(String householdRegisterType) {
        this.householdRegisterType = householdRegisterType == null ? null : householdRegisterType.trim();
    }

    /**
     * 获取户籍所在地，是否为公司集体户 1=是
     *
     * @return collective_household - 户籍所在地，是否为公司集体户 1=是
     */
    public String getCollectiveHousehold() {
        return collectiveHousehold;
    }

    /**
     * 设置户籍所在地，是否为公司集体户 1=是
     *
     * @param collectiveHousehold 户籍所在地，是否为公司集体户 1=是
     */
    public void setCollectiveHousehold(String collectiveHousehold) {
        this.collectiveHousehold = collectiveHousehold == null ? null : collectiveHousehold.trim();
    }

    /**
     * 获取统筹个人账号
     *
     * @return pension_number - 统筹个人账号
     */
    public String getPensionNumber() {
        return pensionNumber;
    }

    /**
     * 设置统筹个人账号
     *
     * @param pensionNumber 统筹个人账号
     */
    public void setPensionNumber(String pensionNumber) {
        this.pensionNumber = pensionNumber == null ? null : pensionNumber.trim();
    }

    /**
     * 获取统筹所属公司
     *
     * @return pension_company - 统筹所属公司
     */
    public String getPensionCompany() {
        return pensionCompany;
    }

    /**
     * 设置统筹所属公司
     *
     * @param pensionCompany 统筹所属公司
     */
    public void setPensionCompany(String pensionCompany) {
        this.pensionCompany = pensionCompany == null ? null : pensionCompany.trim();
    }

    /**
     * 获取统筹费率号
     *
     * @return pension_rate - 统筹费率号
     */
    public String getPensionRate() {
        return pensionRate;
    }

    /**
     * 设置统筹费率号
     *
     * @param pensionRate 统筹费率号
     */
    public void setPensionRate(String pensionRate) {
        this.pensionRate = pensionRate == null ? null : pensionRate.trim();
    }

    /**
     * 获取统筹缴费基数
     *
     * @return pension_base - 统筹缴费基数
     */
    public BigDecimal getPensionBase() {
        return pensionBase;
    }

    /**
     * 设置统筹缴费基数
     *
     * @param pensionBase 统筹缴费基数
     */
    public void setPensionBase(BigDecimal pensionBase) {
        this.pensionBase = pensionBase;
    }

    /**
     * 获取统筹是否扣费 0表示不缴费
     *
     * @return pension_deductions - 统筹是否扣费 0表示不缴费
     */
    public Integer getPensionDeductions() {
        return pensionDeductions;
    }

    /**
     * 设置统筹是否扣费 0表示不缴费
     *
     * @param pensionDeductions 统筹是否扣费 0表示不缴费
     */
    public void setPensionDeductions(Integer pensionDeductions) {
        this.pensionDeductions = pensionDeductions ;
    }

    /**
     * 获取公积金个人账号
     *
     * @return fund_number - 公积金个人账号
     */
    public String getFundNumber() {
        return fundNumber;
    }

    /**
     * 设置公积金个人账号
     *
     * @param fundNumber 公积金个人账号
     */
    public void setFundNumber(String fundNumber) {
        this.fundNumber = fundNumber == null ? null : fundNumber.trim();
    }

    /**
     * 获取公积金所属公司
     *
     * @return fund_company - 公积金所属公司
     */
    public String getFundCompany() {
        return fundCompany;
    }

    /**
     * 设置公积金所属公司
     *
     * @param fundCompany 公积金所属公司
     */
    public void setFundCompany(String fundCompany) {
        this.fundCompany = fundCompany == null ? null : fundCompany.trim();
    }

    /**
     * 获取公积金费率号
     *
     * @return fund_rate - 公积金费率号
     */
    public String getFundRate() {
        return fundRate;
    }

    /**
     * 设置公积金费率号
     *
     * @param fundRate 公积金费率号
     */
    public void setFundRate(String fundRate) {
        this.fundRate = fundRate == null ? null : fundRate.trim();
    }

    /**
     * 获取公积金缴费基数
     *
     * @return fund_base - 公积金缴费基数
     */
    public BigDecimal getFundBase() {
        return fundBase;
    }

    /**
     * 设置公积金缴费基数
     *
     * @param fundBase 公积金缴费基数
     */
    public void setFundBase(BigDecimal fundBase) {
        this.fundBase = fundBase;
    }

    /**
     * 获取公积金是否扣费 0表示不缴费
     *
     * @return fund_deductions - 公积金是否扣费 0表示不缴费
     */
    public Integer getFundDeductions() {
        return fundDeductions;
    }

    /**
     * 设置公积金是否扣费 0表示不缴费
     *
     * @param fundDeductions 公积金是否扣费 0表示不缴费
     */
    public void setFundDeductions(Integer fundDeductions) {
        this.fundDeductions = fundDeductions;
    }

    /**
     * 获取员工门禁卡号
     *
     * @return entrance_number - 员工门禁卡号
     */
    public String getEntranceNumber() {
        return entranceNumber;
    }

    /**
     * 设置员工门禁卡号
     *
     * @param entranceNumber 员工门禁卡号
     */
    public void setEntranceNumber(String entranceNumber) {
        this.entranceNumber = entranceNumber == null ? null : entranceNumber.trim();
    }

    /**
     * 获取考勤标志 =1表示需要参加考勤
     *
     * @return check_work - 考勤标志 =1表示需要参加考勤
     */
    public Integer getCheckWork() {
        return checkWork;
    }

    /**
     * 设置考勤标志 =1表示需要参加考勤
     *
     * @param checkWork 考勤标志 =1表示需要参加考勤
     */
    public void setCheckWork(Integer checkWork) {
        this.checkWork = checkWork;
    }

    /**
     * 获取微信号
     *
     * @return wechat_number - 微信号
     */
    public String getWechatNumber() {
        return wechatNumber;
    }

    /**
     * 设置微信号
     *
     * @param wechatNumber 微信号
     */
    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber == null ? null : wechatNumber.trim();
    }

    /**
     * 获取微信昵称
     *
     * @return wechat_name - 微信昵称
     */
    public String getWechatName() {
        return wechatName;
    }

    /**
     * 设置微信昵称
     *
     * @param wechatName 微信昵称
     */
    public void setWechatName(String wechatName) {
        this.wechatName = wechatName == null ? null : wechatName.trim();
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 获取冻结标志 1=冻结
     *
     * @return freeze_flag - 冻结标志 1=冻结
     */
    public Integer getFreezeFlag() {
        return freezeFlag;
    }

    /**
     * 设置冻结标志 1=冻结
     *
     * @param freezeFlag 冻结标志 1=冻结
     */
    public void setFreezeFlag(Integer freezeFlag) {
        this.freezeFlag = freezeFlag;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新人
     *
     * @return last_modified_by - 更新人
     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * 设置更新人
     *
     * @param lastModifiedBy 更新人
     */
    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * 获取更新时间
     *
     * @return last_modified_time - 更新时间
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * 设置更新时间
     *
     * @param lastModifiedTime 更新时间
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * 获取逻辑删除标记
     *
     * @return delete_flag - 逻辑删除标记
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置逻辑删除标记
     *
     * @param deleteFlag 逻辑删除标记
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * 获取身份证信息_照片
     *
     * @return card_image_path - 身份证信息_照片
     */
    public String getCardImagePath() {
        return cardImagePath;
    }

    /**
     * 设置身份证信息_照片
     *
     * @param cardImagePath 身份证信息_照片
     */
    public void setCardImagePath(String cardImagePath) {
        this.cardImagePath = cardImagePath == null ? null : cardImagePath.trim();
    }

    public List<Integer> getDepartmentSelected() {
        return departmentSelected;
    }

    public void setDepartmentSelected(List<Integer> departmentSelected) {
        this.departmentSelected = departmentSelected;
    }

    public List<Integer> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public List<Integer> getManageOrganizationIdList() {
        return manageOrganizationIdList;
    }

    public void setManageOrganizationIdList(List<Integer> manageOrganizationIdList) {
        this.manageOrganizationIdList = manageOrganizationIdList;
    }

    public String getEmployeeTypeName() {
        return employeeTypeName;
    }

    public void setEmployeeTypeName(String employeeTypeName) {
        this.employeeTypeName = employeeTypeName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAttendanceNumber() {
        return attendanceNumber;
    }

    public void setAttendanceNumber(String attendanceNumber) {
        this.attendanceNumber = attendanceNumber;
    }


    public Integer getRenewNum() {
        return renewNum;
    }

    public void setRenewNum(Integer renewNum) {
        this.renewNum = renewNum;
    }

    public Integer getYearVacationNum() {
        return yearVacationNum;
    }

    public void setYearVacationNum(Integer yearVacationNum) {
        this.yearVacationNum = yearVacationNum;
    }
}