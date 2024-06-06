package com.tds.shortener.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tds.shortener.model.DailyAccess;
import com.tds.shortener.model.url;
import com.tds.shortener.repository.UrlRepository;
import com.tds.shortener.service.DailyService;

@Service
public class DailyServiceImpl implements DailyService  {

    @Autowired
    private UrlRepository urlRepository;


    @Override
    public void updateAccessStats(String url) {

        Optional<url> optionalUrl = urlRepository.findByShortUrl(url);

        url urlFetched = optionalUrl.get();

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        boolean isTrue = urlFetched.getAccessStats().stream().filter(access -> LocalDate.parse(access.getDate(),
        formatter).isEqual(currentDate)).peek(access -> access.setCount(access.getCount() + 1)).findAny().isPresent();

        if (!isTrue) {
            DailyAccess dailyAccess = new DailyAccess(currentDate.format(formatter), 1);
            urlFetched.getAccessStats().add(dailyAccess);
        }

        urlRepository.save(urlFetched);
    }


    @Override
    public Map<String, Object> getAccessStats(String url) {

        Optional<url> optionalUrl = urlRepository.findByShortUrl(url);
        url urlFetched = optionalUrl.get();

        int totalSum = urlFetched.getAccessStats().stream().mapToInt(DailyAccess::getCount).sum();

        Map<String, Integer> accessStatsMap = urlFetched.getAccessStats().stream()
        .collect(Collectors.toMap(DailyAccess::getDate, DailyAccess::getCount));

        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("Média por dia", totalSum / optionalUrl.get().getAccessStats().size());
        resultMap.put("Total", totalSum);
        resultMap.put("Total por dia", accessStatsMap);
        resultMap.put("ID", optionalUrl.get().getId());

        
        return resultMap;
    }


}
