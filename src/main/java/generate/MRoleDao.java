package generate;

import generate.MRole;

public interface MRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MRole record);

    int insertSelective(MRole record);

    MRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MRole record);

    int updateByPrimaryKey(MRole record);
}