package com.amud.io.aemudapi.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang.math.NumberUtils;

import java.util.List;

import static java.lang.Math.toIntExact;
import static org.springframework.util.ObjectUtils.isEmpty;

@Data
public class ResponsePageableVO<T> {
    protected static final long DEFAULT_RECORDS = NumberUtils.LONG_ZERO;
    private Long records = DEFAULT_RECORDS;
    private List<T> items;
    private Integer pages;
    private Integer page;

    @JsonProperty("record_from")
    private Integer recordFrom;
    @JsonProperty("record_to")
    private Integer recordTo;

    public ResponsePageableVO(int records, List<T> items, RequestPageableVO pageable) {
        this((long) records, items, pageable);
    }

    public ResponsePageableVO(long records, List<T> items, RequestPageableVO pageable) {
        this.records = records;
        this.items = items;
        this.pages = (int) Math.ceil((double) this.records / pageable.getRpp());
        this.page = pageable.getPage();
        if (isEmpty(this.items)) {
            this.recordFrom = 1;
            this.recordTo = toIntExact(this.records);
        } else {
            this.recordFrom = (this.page * pageable.getRpp()) - pageable.getRpp() + 1;
            this.recordTo = (int) (this.page.equals(this.pages) ? this.records : this.page * pageable.getRpp());
        }
    }
}