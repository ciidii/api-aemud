package org.aemudapi.utils;

import lombok.Data;
import org.apache.commons.lang.math.NumberUtils;

@Data
public class RequestPageableVO {
    public static final Integer DEFAULT_PAGE = NumberUtils.INTEGER_ONE;
    public static final Integer DEFAULT_RPP = Integer.valueOf(10);

    private Integer page = DEFAULT_PAGE;
    private Integer rpp = DEFAULT_RPP;

    public RequestPageableVO() {
        //constructor without args
    }

    public RequestPageableVO(Integer page, Integer rpp) {
        this.page = page;
        this.rpp = rpp;
    }

}
