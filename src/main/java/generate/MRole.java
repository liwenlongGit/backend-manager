package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * m_role
 * @author 
 */
@Data
public class MRole implements Serializable {
    private Integer id;

    private String roleId;

    private String roleName;

    private Date createTime;

    private String creater;

    /**
     * 角色状态： 0：可用  1：禁用
     */
    private Boolean status;

    private static final long serialVersionUID = 1L;
}