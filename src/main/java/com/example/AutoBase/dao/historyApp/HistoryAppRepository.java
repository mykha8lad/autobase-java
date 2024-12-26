package com.example.AutoBase.dao.historyApp;

import com.example.AutoBase.model.HistoryApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface HistoryAppRepository extends JpaRepository<HistoryApp, Integer> {

}
