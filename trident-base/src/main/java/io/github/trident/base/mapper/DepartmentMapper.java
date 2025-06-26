package io.github.trident.base.mapper;

import io.github.trident.common.domain.organization.BizDept;
import io.github.trident.common.domain.organization.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Mapper
public interface DepartmentMapper {
    /**
     * @描述：通过部门标杆编码获取部门信息
     * @参数：deptCode 部门编码
     * @返回值：Department 部门信息
     * */
    Department getDeptByStandardCode(String standardCode);

    /**
     * @作者：罗典
     * @时间：2012-4-24
     * @描述：通过部门编码获取部门信息
     * @参数：deptCode 部门编码
     * @返回值：Department 部门信息
     * */
    public Department getDeptByCode(String deptCode);

    /**
     * 得到当前部门的子部门 getDirectChildDepartments
     *
     * @param dept
     * @return List<Department>
     * @since JDK1.6
     */
    List<Department> getDirectChildDepartments(Department dept);

    /**
     * 通过部门名称模糊查询部门的deptSeq querySeq
     *
     * @param deptName
     * @return List<String>
     * @since JDK1.6
     */
    List<String> querySeq(String deptName);

    /**
     * 通过授权部门名称模糊查询部门对象总条数
     *
     * @param depIds
     *            、deptName、start、limit
     * @return Map<String,Object>
     * @since JDK1.6
     */
    String searchExecuteDepCount(Set<String> depIds, String deptName);

    /**
     * @作者：钟琼
     * @时间：2012-11-03
     * @描述：通过授权部门id 找出部门对象集合
     * @参数：depIds 授权部门集合、deptName 模糊查询名字、start 起始条数、limit 每页条数
     * @返回值：List<Department> 部门对象集合
     * */
    List<Department> searchExecuteDep(Set<String> deptIds, String deptName,
                                      int start, int limit);

    /*---------------------------------------备用方法-------------------------------------------------------*/
    /**
     * 查找符合条件的所有部门，并分页 getAll
     *
     * @param department
     *            ,limit,start
     * @return List<Department>
     * @since JDK1.6
     */
    List<Department> getAll(Department department, int limit, int start);

    /**
     * 更新
     */
    void update(Department department);

    /**
     * 保存对象
     */
    void insertDepartment(Department department);

    /**
     * 删除，通过id
     */
    void deleteById(List<String> idList);

    /**
     * 获得根节点对象
     */
    Department getRoot();

    /**
     * 查找符合条件的个数 count
     *
     * @param department
     * @return Long
     * @since JDK1.6
     */
    Long count(Department department);

    /**
     * 查找符合条件的所有部门 getAll
     *
     * @param department
     * @return List<Department>
     * @since JDK1.6
     */
    List<Department> getAll(Department department);

    /**
     * 通过id获得所有的子节点的ID getAllChildIdById
     *
     * @param id
     * @return List<String>
     * @since JDK1.6
     */
    List<String> getAllChildIdById(String id);

    /**
     * 查询所有
     */
    List<Department> getAll(Integer start, Integer limit);

    /**
     * 通过ID，得到对象
     */
    Department getById(String id);

    /**
     * 通过部门ID，得到当前部门下了叶子部门
     *
     * @return
     */
    List<Department> getAllChildDeptByDeptId(String id);

    /**
     * 通过id获得所有的子节点 getAllChildById
     *
     * @param id
     * @return List<Department>
     * @since JDK1.6
     */
    List<Department> getAllChildById(String id);

    /**
     * 通过用户ID，查询当前授权用户所剩余的可分配的部门
     *
     * @param userId
     *            待授权用户ID
     * @param currentUserId
     *            当前授权用户ID
     * @param deptName
     *            根据部门名称过滤
     * @return
     */
    List<Department> getLeftDepts(String currentUserId, String userId,
                                  String deptName);

    /**
     * 通过用户ID，查询当前授权用户给用户已经分配的部门
     *
     * @param userId
     *            待授权用户ID
     * @param currentUserId
     *            当前授权用户ID
     * @return
     */
    List<Department> getAuthedDepts(String currentUserId, String userId);

    /**
     * 查询当前授权用户可分配的所有部门
     *
     * @param currentUserId
     *            当前授权用户ID
     * @param deptName
     *            根据部门名称过滤
     * @return
     */
    List<Department> getAllAuthDepts(String currentUserId, String deptName);

    /**
     * 通过用户ID，查询非当前用户分配的部门ID集合 getDeptIdsNoCurrentUserAuthDept
     *
     * @param currentUserId
     * @param userId
     * @return
     * @return List<String>
     * @since:
     */
    List<String> getDeptIdsNoCurrentUserAuthDept(String currentUserId,
                                                 String userId);

    /**
     *
     * @Title: getByLikeDeptName
     * @Description: 按名字模糊查询部门
     * @param @param deptName
     * @param @return 设定文件
     * @return List<Department> 返回类型
     * @throws
     */
    public List<Department> getByLikeDeptName(String deptName);

    public List<Department> getByLikeDeptNameRow(String deptName, int rows);

    /**
     *
     * @Title: getByLikeDeptNameByDeptId
     * @Description: 查询父节点的部门
     * @param @param deptName
     * @param @param deptId
     * @param @return 设定文件
     * @return Department 返回类型
     * @throws
     */
    public Department getByLikeDeptNameByDeptId(String deptName, String deptId);

    /**
     *
     * @Title: getByLikeDeptNameByDeptCode
     * @Description: 查询父节点的部门
     * @param @param deptName
     * @param @param deptCode
     * @param @return 设定文件
     * @return Department 返回类型
     * @throws
     */
    public Department getByLikeDeptNameByDeptCode(String deptName,
                                                  String deptCode);

    /**
     *
     * <p>
     * 根据所传ID，获得公司事业部列表<br />
     * </p>
     *
     * @author Administrator
     * @version 0.1 2012-5-8
     * @param id
     * @return List<BizDept>
     */
    public List<BizDept> getAllBizDept(String id);

    /**
     *
     * <p>
     * 通过部门标杆编码，得到部门ID<br />
     * </p>
     *
     * @author Administrator
     * @version 0.1 2012-5-29
     * @param standardCode
     * @return String
     */
    public String getDeptIdByStandardCode(String standardCode);

    Department getBizDeptByDeptId(String parentId, String deptId);

    public List<Department> getAllBizDeptList(String id);

    /**
     *
     * @Title: queryLeftDeptsByEmpCode
     * @Description: 查找所有的课可分配部门权限除掉已分配的
     * @param currentUserId
     * @param empCode
     * @param deptName
     *            部门名称
     * @param @return 设定文件
     * @return List<Department> 返回类型
     */
    List<Department> queryLeftDeptsByEmpCode(String currentUserId,
                                             String empCode, String deptName);

    /**
     * @作者：钟琼
     * @时间：2012-12-4
     * @描述：通过部门名称模糊查询所有部门
     * @参数：deptName 部门名字
     * @返回值：List<Department> 部门集合
     * */
    List<Department> queryDeptListByName(String deptName, int start, int limit);

    /**
     * @作者：钟琼
     * @时间：2012-12-4
     * @描述：通过部门名称模糊查询所有部门 条数
     * @参数：deptName 部门名字
     * @返回值：int 条数
     * */
    int queryDeptListByNameCount(String deptName);

    /**
     * @作者：钟琼
     * @时间：2013-1-31
     * @描述：通过部门名称模糊查询所有外场
     * @参数：deptName 部门名字
     * @返回值：List<Department> 外场集合
     * */
    List<Department> queryOutFieldListByName(String deptName, int start,
                                             int limit);

    /**
     * @作者：钟琼
     * @时间：2013-1-31
     * @描述：通过部门名称模糊查询所有部门 外场
     * @参数：deptName 外场名字
     * @返回值：int 条数
     * */
    int queryOutFieldListByCount(String deptName);

    /**
     *
     * <p>
     * Description:通过部门名称(模糊)与标杆编码查询<br />
     * </p>
     *
     * @author liuHuan
     * @version 0.1 2013-8-5
     * @param deptName
     * @param standardCode
     * @return List<Department>
     */
    List<Department> queryDeptByNameAndStandardCode(String deptName,
                                                    String standardCode);

    /**
     *
     * <p>
     * Description:这里写描述<br />
     * </p>
     *
     * @author roy
     * @version 0.1 2013-8-8
     * @param deptName
     * @param deptCode
     * @return Department
     */
    Department getByLikeDeptNameByStandardCode(String deptName, String deptCode);

    /**
     *
     * <p>
     * Description:通过部门ID查询上级部门信息<br />
     * </p>
     *
     * @author andy
     * @version 0.1 2013-8-14
     * @param deptId
     *            部门Id
     * @return Department
     */
    Department queryDeptByParentDeptId(String deptId);

    /**
     *
     * <p>
     * Description:通过部门ID递归上级部门信息<br />
     * </p>
     *
     * @author andy
     * @version 0.1 2013-8-14
     * @param deptId
     *            部门Id
     * @return Department
     */
    List<Department> getAllParentDeptByDeptId(String deptId);

    List<Department> queryDeptByNameAndStandardCodeForEx(String deptName,
                                                         String standardCode);
    /**
     * 同步UUMS部门数据，进行相应的数据同步，数据权限分配
     * @author ZhangZW
     * @update 2014-1-6
     * @param DEPTSTANDCODE changeType oldParentStandCode parentStandCode
     * @return
     */
    int  syncDep(String DEPTSTANDCODE,String changeType,String oldParentStandCode);
    public Department getDeptByStandCode(String standCode);
    /**
     *
     * <p>
     * Description:通过map.get("standCode")部门ID递归上级部门末尾带map.get("lastWord")字的部门<br />
     * </p>
     *
     * @author 杨伟
     * @version 0.1 2014-2-25
     * @param map
     * @return Department
     */
    Department getAllParentDeptBystandCode(Map<String,String> map);

    /**
     *
     * <p>
     * Description:模糊查找部门列表<br />
     * </p>
     * @author andy
     * @version 0.1 2014-3-28
     * @param deptName 模糊部门名称
     * @return
     * List<Department>
     */
    List<Department> getDepartmentListByLikeDeptName(String deptName);

    Date getLastModifyTime();

    List<Department> getAllDepartments();
}
