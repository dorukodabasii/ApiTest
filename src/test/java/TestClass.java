import common.EndPoint;
import configurate.RestAsurredConfigurate;
import entities.MovieOrGame;
import entities.MovieOrGameDetail;
import entities.Search;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClass {


    @Test
    public void searchFilmTest(){

        //Harry Potter araması yapar ve Harry Potter and the Sorcerer's Stone filminin idsine göre Title Year ve Released a göre test eder.

        RequestSpecification specification =new RestAsurredConfigurate().getRequestSpecification();//apiye istek atar
        Response response=new RestAsurredConfigurate()
                .getResponse(specification,String.format("%s?i=%s&apiKey=%s",
                        EndPoint.BASE_URL,
                        getMovieById("Harry Potter","Harry Potter and the Sorcerer's Stone","movie") //Filmin Idsini alır
                        ,EndPoint.API_KEY), HttpStatus.SC_OK);

        MovieOrGameDetail movieOrGameDetail= response.as(MovieOrGameDetail.class, ObjectMapperType.GSON);//Gelen JSON response objeye çevrilir.

        Assert.assertEquals(movieOrGameDetail.getTitle(),"Harry Potter and the Sorcerer's Stone","Aranan sonucun Başlığı");
        Assert.assertEquals(movieOrGameDetail.getYear(),"2001","Aranan sonucun Çıkış Yılı");
        Assert.assertEquals(movieOrGameDetail.getType(),"movie","Aranan sonucun tipi");
        Assert.assertEquals(movieOrGameDetail.getReleased(),"16 Nov 2001","Aranan sonucun Çıkış Tarihi");
        Assert.assertEquals(response.statusCode(),200,"Status Code");


    }

    //Film adına göre apiye istek atıp eğer sonuç (200 OK) dönüyorsa filmin imdbId'sini döndürür
    public String getMovieById(String movieName,String filmName,String type){

        String filmId="";
        RequestSpecification requestSpecification=new RestAsurredConfigurate().getRequestSpecification();
        Response response=new RestAsurredConfigurate()
                .getResponse(requestSpecification,
                        String.format("%s?s=%s&apiKey=%s",
                                EndPoint.BASE_URL, filmName, EndPoint.API_KEY),
                        HttpStatus.SC_OK);

        MovieOrGame movies=response.as(MovieOrGame.class, ObjectMapperType.GSON);//Gelen JSON response objeye çevrilir.


        for (Search item:movies.getSearch())//Movie classın içindeki search listesi aranır.
        {

            if (filmName.equals(item.getTitle())&&type.equals(item.getType()))//Filmin başlığı ile aranan film karşılaştırılır.
            {
                filmId=item.getImdbID();//bulunan filmin ıdsi alınır.
                Assert.assertEquals("tt0241527",item.getImdbID(),"ImdbID");


            }

        }

        return filmId;

    }
}
