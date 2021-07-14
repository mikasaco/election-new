package com.mou.election.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mou.election.EdataDictionaryManager;
import com.mou.election.dal.domian.EdataDictionaryDO;
import com.mou.election.model.EdataDictionaryDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UploadDataListener extends AnalysisEventListener<EdataDictionaryDTO> {

    private EdataDictionaryManager edataDictionaryManager;

    public UploadDataListener(EdataDictionaryManager edataDictionaryService){
        this.edataDictionaryManager = edataDictionaryService;
    }

    /**
     * 每隔5条存储数据库,实际使用中可以3000条,方便回收
     */
    private static final int BATCH_COUNT = 5;
    List<EdataDictionaryDO> list = new ArrayList<>();

    @Override
    public void invoke(EdataDictionaryDTO edataDictionaryDTO, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", edataDictionaryDTO);
        edataDictionaryManager.add(edataDictionaryDTO);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }


}
