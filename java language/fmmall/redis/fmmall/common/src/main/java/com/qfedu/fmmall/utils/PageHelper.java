package com.qfedu.fmmall.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageHelper<T> {
    //Total records.
    private int count;
    //Total pages.
    private int pageCount;
    //Paging data.
    private List<T> list;
}
