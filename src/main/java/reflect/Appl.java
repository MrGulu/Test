package reflect;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Appl implements Serializable {

    private static final long serialVersionUID = -5438322980763491334L;

    private BigDecimal applSeq;

    private BigDecimal applSeq2;

    private Long typSeq;

    private Long typSeq2;

    private Integer typVer;

    private Integer typVer2;

    private Integer typVer3;

    private String instuCde;

    private String applCde;
}