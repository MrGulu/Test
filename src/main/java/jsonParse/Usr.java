package jsonParse;


import lombok.Data;

import java.io.Serializable;

@Data
public class Usr implements Serializable {
    private static final long serialVersionUID = 2997216291673274672L;
    private String instuCde;

    private String usrCde;

    private String extInd;

    private String runInd;

    private String usrName;

    private String usrIdTyp;

    private String usrIdNo;

    private String usrPassword;

    private String usrTel;

    private String usrEmail;

    private String usrBch;

    private String depCde;

    private String usrSts;

    private String restInd;

    private String usrRepassInd;

    private String passModiDt;

    private String usrRmk;

    private String lastChgDt;

    private String lastChgUsr;

    private String managInd;

    private String usrSuper;

    private String riskGrd;

    private String quGrd;

    private String specialRole;

    private String isRate;

    private String assistToSign;

    private String empDt;

    private String cooprCde;

}