package pl.krysinski.beers.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.krysinski.beers.demo.model.BeersInfo;
import pl.krysinski.beers.demo.model.ShortBeersInfo;

import java.util.*;
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
        String sql = "SELECT * FROM beers";
        List<ShortBeersInfo> beerList =new ArrayList<>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(element ->
                beerList.add(new ShortBeersInfo(
                Integer.parseInt(String.valueOf(element.get("id"))),
                String.valueOf(element.get("name")),
                String.valueOf(element.get("firstBrewed")),
                String.valueOf(element.get("description")),
                String.valueOf(element.get("ibu")),
                String.valueOf(element.get("imageUrl"))
        )));
        return beersList;
    }

    @Override
    public void editBeer(BeersInfo newBeer) {
        String sql = "UPDATE beers SET beers.name=?, beers.firstBrewed=?, beers.description=?, beers.imageUrl=?, beers.ibu=? WHERE id=?";
        jdbcTemplate.update(sql, newBeer.getName(), newBeer.getFirstBrewed(), newBeer.getDescription(), newBeer.getImageUrl(), newBeer.getIbu(), newBeer.getId());

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
