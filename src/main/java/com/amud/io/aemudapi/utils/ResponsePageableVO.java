package com.amud.io.aemudapi.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang.math.NumberUtils;

import java.util.List;

import static java.lang.Math.toIntExact;
import static org.springframework.util.ObjectUtils.*;

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

    public List<T> getItems() {
        return items;
    }
    public void setItems(List<T> items) {
        this.items = items;
    }
    public Integer getPages() {
        return pages;
    }
    public void setPages(Integer pages) {
        this.pages = pages;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getRecordFrom() {
        return recordFrom;
    }
    public void setRecordFrom(Integer recordFrom) {
        this.recordFrom = recordFrom;
    }
    public Integer getRecordTo() {
        return recordTo;
    }
    public void setRecordTo(Integer recordTo) {
        this.recordTo = recordTo;
    }

    public Long getRecords() {
        return records;
    }
    public void setRecords(Long records) {
        this.records = records;
    }
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