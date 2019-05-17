package org.springblade.common.entity;

public enum FinancePriceType {
    HF_CL("hf_CL","NYMEX原油"),
    HF_OIL("hf_OIL","布伦特原油"),
    NF_SC0("nf_SC0","原油"),
    DINIW("DINIW","美元指数");

    String code;
    String description;

    FinancePriceType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String genTotalCode(){
        StringBuilder stringBuilder = new StringBuilder();
        for(FinancePriceType type : FinancePriceType.values()){
            stringBuilder.append(type.getCode());
            stringBuilder.append(",");
        }

        if(stringBuilder.length() > 0){
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        }
        return stringBuilder.toString();
    }
}
