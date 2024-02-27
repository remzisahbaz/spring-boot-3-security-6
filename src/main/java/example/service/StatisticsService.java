package example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StatisticsService {

    private Integer subscribers_count;
    private String title;
    public Integer getSubscribers_count() {
        return subscribers_count;
    }
    public void setSubscribers_count(Integer subscribers_count) {
        this.subscribers_count = subscribers_count;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getSubs() {
        String uri = "https://public-api.wordpress.com/rest/v1.1/sites/aanlegplaats.blog";
        RestTemplate restTemplate= new RestTemplate();
        StatisticsService result = restTemplate.getForObject(uri, StatisticsService.class);

        return result.getSubscribers_count();
    }
    public String getPostCount() {
        String uri = "https://public-api.wordpress.com/rest/v1.1/sites/aanlegplaats.blog";
        RestTemplate restTemplate= new RestTemplate();
        StatisticsService result = restTemplate.getForObject(uri, StatisticsService.class);

        System.out.println(result.getTitle());
        return result.getTitle();
    }

}