package com.example.AutoBase.service.busines.historyappservice;

import com.example.AutoBase.dao.historyApp.HistoryAppRepository;
import com.example.AutoBase.model.HistoryApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryAppServiceImpl implements HistoryAppService {

    @Autowired
    private HistoryAppRepository historyAppRepository;


    @Override
    public void save(HistoryApp historyApp) {
        historyAppRepository.save(historyApp);
    }

    @Override
    public int[] saveHistoryAppsList(List<HistoryApp> historyApps) {
        historyAppRepository.saveAll(historyApps);
        return new int[0];
    }

    @Override
    public void update(HistoryApp historyApp) {
        historyAppRepository.save(historyApp);
    }

    @Override
    public void delete(HistoryApp historyApp) {
        historyAppRepository.delete(historyApp);
    }

    @Override
    public List<HistoryApp> findAll() {
        return historyAppRepository.findAll();
    }

    @Override
    public void deleteAll() {
        historyAppRepository.deleteAll();
    }
}
