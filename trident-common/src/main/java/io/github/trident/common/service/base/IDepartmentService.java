package io.github.trident.common.service.base;

import io.github.trident.common.domain.organization.BizDept;
import io.github.trident.common.domain.organization.Department;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @projectName: trident
 * @package: io.github.trident.common.service.base
 * @className: IDepartmentService
 * @author: frank.wu
 * @description: TODO
 * @date: 2025/3/30 14:23
 * @version: 1.0
 */
public interface IDepartmentService {
    /**
     * @描述：通过部门标杆编码获取部门信息
     * @参数：deptCode 部门编码
     * @返回值：Department 部门信息
     */
    Department getDeptByStandardCode(String standardCode);

    /**
     * @描述：通过部门编码获取部门信息
     * @参数：deptCode 部门编码
     * @返回值：Department 部门信息
     */
    Department getDeptByCode(String deptCode);

    /**
     * 得到当前部门的子部门 getDirectChildDepartments
     *
     * @param dept
     * @return List<Department>
     */
    List<Department> queryDirectChildDepts(Department dept);

    /**
     * 通过部门名称模糊查询部门的deptSeq querySeq
     *
     * @param deptName
     * @return List<String>
     * @since JDK1.6
     */
    List<String> querySeq(String deptName);

    /*---------------------------------------备用方法-------------------------------------------------------*/

    /**
     * 查询所有部门，条件department 分页查询
     */
    List<Department> queryAll(Department department, int limit, int start);

    /**
     * 增加
     */
    void add(Department department);

    /**
     * 修改
     */
    void update(Department department);

    /**
     * 删除
     */
    void remove(String id);

    /**
     * 查询有几条，条件department
     */
    Long count(Department department);

    /**
     * 查询所有部门，条件department
     */
    List<Department> queryAll(Department department);

    /**
     * 得到指定ID的对象
     */
    Department queryById(String id);

    /**
     * 得到所有,并生成树
     *
     * @return
     */
    List<Department> queryAll(Integer start, Integer limit);

    /**
     * 通过部门ID，得到当前部门下所有部门
     *
     * @return
     */
    List<Department> queryAllChildDeptByDeptId(String id);

    /**
     * @描述：通过部门名称模糊查询所有部门
     * @参数：deptName 部门名字
     * @返回值：List<Department> 部门集合
     */
    public List<Department> queryDeptListByName(String deptName, int start, int limit);

    /**
     * 通过用户ID，查询当前授权用户所剩余的可分配的部门
     *
     * @param userId        待分配的用户ID
     * @param currentUserId 当前授权用户ID
     * @param deptName      根据部门名称过滤
     * @return
     */
    List<Department> queryLeftDepts(String currentUserId, String userId, String deptName);

    /**
     * 通过用户ID，查询当前授权用户给用户已经分配的部门
     *
     * @param userId        待分配的用户ID
     * @param currentUserId 当前授权用户ID
     * @return
     */
    List<Department> queryAuthedDepts(String currentUserId, String userId);

    /**
     * 通过ID，得到当前节点下的所有节点
     *
     * @param id
     * @return
     */
    List<Department> queryAllChild(String id);

    /**
     * 查询所有大区
     */
    public List<Department> getBigAreaList();

    /**
     * 模糊查询所有大区
     */
    public List<Department> getBigAreaListByName(String deptName);

    /**
     * 查询所在大区
     */
    public Department getBigAreaByDeptId(String deptId);

    public Department getBizDeptByDeptId(String parentId, String deptId);

    /**
     * 查询所在大区
     */
    public Department getBigAreaByDeptCode(String deptCode);

    /**
     * <p>
     * 获得公司所有事业部<br />
     * </p>
     *
     * @param id
     * @return List<BizDept>
     * @author Administrator
     * @version 0.1 2012-5-8
     */
    public List<BizDept> getAllBizDept(String id);

    public List<Department> getAllBizDeptList(String id);

    /**
     * 根据部门名称模糊查找部门对象
     *
     * @return
     */
    public List<Department> getDepartmentByDeptName(String deptName);

    public List<Department> getDepartmentByDeptNameRow(String deptName, int rows);

    /**
     * 根据部门ID查找部门对象
     *
     * @param id
     * @return
     */
    public Department getDepartmentById(String id);

    /**
     * @param currentUserId
     * @param deptName      部门名称
     * @param @return       设定文件
     * @return List<Department> 返回类型
     * @Title: queryLeftDeptsByEmpCode
     * @Description: 查找所有的课可分配部门权限除掉已分配的
     */
    List<Department> queryLeftDeptsByEmpCode(String currentUserId,
                                             String userId, String deptName);

    /**
     * 通过授权部门名称模糊查询部门对象集合
     *
     * @param depIds、deptName、start、limit
     * @return Map<String, Object>
     * @since JDK1.6
     */
    Map<String, Object> getExecuteDep(Set<String> depIds, String deptName, int start,
                                      int limit);

    /**
     * @return
     * @Description: 查询所有事业部
     * @author huangzhanming
     * @date 2012-11-28 上午10:45:42
     * @version V1.0
     */
    public List<Department> getBizDeptList();

    /**
     * @param deptId
     * @return
     * @Description: 查询所在事业部
     * @author huangzhanming
     * @date 2012-11-28 上午10:46:28
     * @version V1.0
     */
    public Department getBizDeptByDeptId(String deptId);

    /**
     * @描述：通过部门名称模糊查询所有部门 条数
     * @参数：deptName 部门名字
     * @返回值：int 条数
     */
    int queryDeptListByNameCount(String deptName);

    /**
     * @描述：通过部门名称模糊查询所有外场
     * @参数：deptName 部门名字
     * @返回值：List<Department> 外场集合
     */
    List<Department> queryOutFieldListByName(String deptName, int start,
                                             int limit);

    /**
     * @描述：通过部门名称模糊查询所有部门 外场
     * @参数：deptName 外场名字
     * @返回值：int 条数
     */
    int queryOutFieldListByCount(String deptName);

    /**
     * <p>
     * Description:查询事业部与枢纽中心<br />
     * </p>
     *
     * @return List<Department>
     * @author liuHuan
     * @version 0.1 2013-8-5
     */
    List<Department> getBizAndHubCenterDeptList();

    /**
     * <p>
     * Description:通过标杆编码查询事业部<br />
     * </p>
     *
     * @param standardCode
     * @return List<Department>
     * @author liuHuan
     * @version 0.1 2013-8-5
     */
    List<Department> queryDeptByNameAndStandardCode(String standardCode);

    /**
     * <p>
     * Description:通过部门ID查询上级部门信息<br />
     * </p>
     *
     * @param deptId 部门Id
     * @return Department
     * @author andy
     * @version 0.1 2013-8-14
     */
    Department queryDeptByParentDeptId(String deptId);

    /**
     * <p>
     * Description:通过部门ID递归上级部门信息<br />
     * </p>
     *
     * @param deptId 部门Id
     * @return Department
     * @author andy
     * @version 0.1 2013-8-14
     */
    List<Department> getAllParentDeptByDeptId(String deptId);

    /**
     * 同步UUMS部门数据，进行相应的数据同步，数据权限分配
     *
     * @param DEPTSTANDCODE changeType oldParentStandCode parentStandCode
     * @return
     * @author ZhangZW
     * @update 2014-1-6
     */
    void syncDep(String DEPTSTANDCODE, String changeType, String oldParentStandCode);

    public Department getDeptByStandCode(String standCode);

    /**
     * <p>
     * Description:通过map.get("standCode")部门ID递归上级部门末尾带map.get("lastWord")字的部门<br />
     * </p>
     *
     * @param map
     * @return Department
     * @author 杨伟
     * @version 0.1 2014-2-25
     */
    public Department getAllParentDeptBystandCode(Map<String, String> map);

    /**
     * <p>
     * Description:模糊查找部门列表<br />
     * </p>
     *
     * @param deptName 模糊部门名称
     * @return List<Department>
     * @author andy
     * @version 0.1 2014-3-28
     */
    List<Department> getDepartmentListByLikeDeptName(String deptName);

    List<Department> getAllDepartments();

    Date getLastModifyTime();
}
