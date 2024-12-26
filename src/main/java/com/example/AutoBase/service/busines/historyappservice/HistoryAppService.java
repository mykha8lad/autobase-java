package com.example.AutoBase.service.busines.historyappservice;

import com.example.AutoBase.model.HistoryApp;
import java.util.List;

public interface HistoryAppService {
    void save(HistoryApp historyApp);
    int[] saveHistoryAppsList(List<HistoryApp> historyApps);
    void update(HistoryApp historyApp);
    void delete(HistoryApp historyApp);
    List<HistoryApp> findAll();
    void deleteAll();
}
