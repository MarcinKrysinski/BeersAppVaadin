package pl.krysinski.beers.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.krysinski.beers.demo.model.BeersInfo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BeerDaoImpl implements BeerDao{

    private JdbcTemplate jdbcTemplate;
    private BeersInfo[] beers;
    private List<BeersInfo> beersList;

    @Autowired
    public BeerDaoImpl(JdbcTemplate jdbcTemplate) {
        RestTemplate restTemplate = new RestTemplate();
        beers = restTemplate.getForObject("https://api.punkapi.com/v2/beers", BeersInfo[].class);
        beersList = Arrays.stream(beers).collect(Collectors.toList());
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BeersInfo> getBeers() {
        return null;
    }

    @Override
    public void editBeer(BeersInfo beer) {

    }


    @Override
    public void addBeerToDb() {

        String truncateSql = "TRUNCATE TABLE beers";
        jdbcTemplate.execute(truncateSql);

        String sql = "INSERT INTO beers VALUES (?,?,?,?,?,?)";
        for (int i = 0; i < beersList.size(); i++) {
            jdbcTemplate.update(
                    sql,
                    beersList.get(i).getId(),
                    beersList.get(i).getName(),
                    beersList.get(i).getFirstBrewed(),
                    beersList.get(i).getDescription(),
                    beersList.get(i).getImageUrl(),
                    beersList.get(i).getIbu()
            );
        }
    }
}
