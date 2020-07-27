import common.EndPoint;
import configurate.RestAsurredConfigurate;
import entities.Movie;
import entities.MovieDetail;
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
                        getMovieById("Harry Potter","Harry Potter and the Sorcerer's Stone") //Filmin Idsini alır
                        ,EndPoint.API_KEY), HttpStatus.SC_OK);

        MovieDetail movieDetail= response.as(MovieDetail.class, ObjectMapperType.GSON);//Gelen JSON response objeye çevrilir.

        Assert.assertEquals(movieDetail.getTitle(),"Harry Potter and the Sorcerer's Stone","Filmin Başlığı");
        Assert.assertEquals(movieDetail.getYear(),"2001","Filmin Çıkış Yılı");
        Assert.assertEquals(movieDetail.getType(),"movie","Filmin Tipi");
        Assert.assertEquals(movieDetail.getReleased(),"16 Nov 2001","Filmin Çıkış Tarihi");
        Assert.assertEquals(response.statusCode(),200,"Status Code");


    }

    //Film adına göre apiye istek atıp eğer sonuç (200 OK) dönüyorsa filmin imdbId'sini döndürür
    public String getMovieById(String movieName,String filmName){

        String filmId="";
        RequestSpecification requestSpecification=new RestAsurredConfigurate().getRequestSpecification();
        Response response=new RestAsurredConfigurate()
                .getResponse(requestSpecification,
                        String.format("%s?s=%s&apiKey=%s",
                                EndPoint.BASE_URL, movieName, EndPoint.API_KEY),
                        HttpStatus.SC_OK);

        Movie movies=response.as(Movie.class, ObjectMapperType.GSON);//Gelen JSON response objeye çevrilir.


        for (Search item:movies.getSearch())//Movie classın içindeki search listesi aranır.
        {

            if (filmName.equals(item.getTitle()))//Filmin başlığı ile aranan film karşılaştırılır.
            {
                filmId=item.getImdbID();//bulunan filmin ıdsi alınır.
                Assert.assertEquals("tt0241527",item.getImdbID(),"ImdbID");


            }

        }

        return filmId;

    }
}
