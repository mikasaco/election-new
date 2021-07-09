package com.mou.election.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mou.election.EdataDictionaryManager;
import com.mou.election.model.EdataDictionaryDTO;

public class UploadDataListener extends AnalysisEventListener<EdataDictionaryDTO> {

    public UploadDataListener(EdataDictionaryManager edataDictionaryService){

    }

    @Override
    public void invoke(EdataDictionaryDTO edataDictionaryDTO, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
